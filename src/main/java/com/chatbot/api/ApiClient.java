package com.chatbot.api;

import com.chatbot.model.AllBlogsResponse;
import com.chatbot.model.NewBlogPostRequest;
import com.chatbot.model.Statistics;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Client class for communicating with the REST API.
 * Utilizes Java 11's {@link HttpClient} for making HTTP requests
 * and Jackson for JSON serialization and deserialization.
 */
public class ApiClient {
    private final HttpClient httpClient;   // HTTP client for making requests
    private final ObjectMapper objectMapper; // Jackson object mapper for JSON processing
    private final String baseUrl;          // Base URL of the REST API

    /**
     * Constructor to initialize the ApiClient.
     * @param baseUrl The base URL of the REST API.
     */
    public ApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
        // Initialize HttpClient with common configurations
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2) // Use HTTP/2 protocol
                .followRedirects(HttpClient.Redirect.NORMAL) // Automatically follow redirects
                .connectTimeout(Duration.ofSeconds(10)) // Set a 10-second connection timeout
                .build();
        this.objectMapper = new ObjectMapper(); // Initialize Jackson ObjectMapper
    }

    /**
     * Fetches all blog posts from the API (corresponds to GET ?api=blogs).
     *
     * @return An {@link AllBlogsResponse} object containing the list of blogs and meta information.
     * Returns null if the API call is unsuccessful or deserialization fails.
     * @throws IOException If an I/O error occurs during the HTTP request or response processing.
     * @throws InterruptedException If the operation is interrupted.
     */
    public AllBlogsResponse getAllBlogPosts() throws IOException, InterruptedException {
        String url = baseUrl + "?api=blogs"; // Construct the full URL for the API endpoint
        HttpRequest request = HttpRequest.newBuilder()
                .GET() // Specify GET HTTP method
                .uri(URI.create(url)) // Set the request URI
                .setHeader("Accept", "application/json") // Request JSON response
                .build();

        // Send the HTTP request and receive a string response body
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Check the HTTP status code from the response
        if (response.statusCode() == 200) { // HTTP 200 OK indicates success
            // Successfully received response, deserialize JSON into AllBlogsResponse object
            return objectMapper.readValue(response.body(), AllBlogsResponse.class);
        } else {
            // Log error details if the status code is not 200
            System.err.println("Error fetching blog posts. Status code: " + response.statusCode() + ", Response: " + response.body());
            return null; // Return null to indicate failure
        }
    }

    /**
     * Creates a new blog post via the API (corresponds to POST ?api=blogs).
     *
     * @param request The data for the new blog post as a {@link NewBlogPostRequest} object.
     * @return true if the post was successfully created (HTTP 201 Created), false otherwise.
     * @throws IOException If an I/O error occurs during the HTTP request or response processing.
     * @throws InterruptedException If the operation is interrupted.
     */
    public boolean createNewBlogPost(NewBlogPostRequest request) throws IOException, InterruptedException {
        String url = baseUrl + "?api=blogs"; // Construct the full URL for the API endpoint
        // Convert the request object to a JSON string for the request body
        String requestBody = objectMapper.writeValueAsString(request);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(requestBody)) // Specify POST method with JSON body
                .uri(URI.create(url)) // Set the request URI
                .setHeader("Content-Type", "application/json") // Set Content-Type header to JSON
                .setHeader("Accept", "application/json") // Request JSON response
                .build();

        // Send the HTTP request and receive a string response body
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        // Check the HTTP status code from the response
        if (response.statusCode() == 201) { // HTTP 201 Created indicates successful creation
            System.out.println("Blog post created successfully!");
            return true; // Indicate success
        } else {
            // Log error details if the status code is not 201
            System.err.println("Error creating blog post. Status code: " + response.statusCode() + ", Response: " + response.body());
            return false; // Indicate failure
        }
    }

    /**
     * Fetches system statistics from the API (corresponds to GET ?api=stats).
     *
     * @return A {@link Statistics} object containing the system statistics.
     * Returns null if the API call is unsuccessful or deserialization fails.
     * @throws IOException If an I/O error occurs during the HTTP request or response processing.
     * @throws InterruptedException If the operation is interrupted.
     */
    public Statistics getStatistics() throws IOException, InterruptedException {
        String url = baseUrl + "?api=stats"; // Construct the full URL for the API endpoint
        HttpRequest request = HttpRequest.newBuilder()
                .GET() // Specify GET HTTP method
                .uri(URI.create(url)) // Set the request URI
                .setHeader("Accept", "application/json") // Request JSON response
                .build();

        // Send the HTTP request and receive a string response body
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Check the HTTP status code from the response
        if (response.statusCode() == 200) { // HTTP 200 OK indicates success
            // Successfully received response, deserialize JSON into Statistics object
            return objectMapper.readValue(response.body(), Statistics.class);
        } else {
            // Log error details if the status code is not 200
            System.err.println("Error fetching statistics. Status code: " + response.statusCode() + ", Response: " + response.body());
            return null; // Return null to indicate failure
        }
    }
}
