package com.chatbot;

import com.chatbot.api.ApiClient;
import com.chatbot.config.AppConfig;
import com.chatbot.model.AllBlogsResponse;
import com.chatbot.model.BlogPost;
import com.chatbot.model.NewBlogPostRequest;
import com.chatbot.model.Statistics;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Main application class for the chatbot.
 * Handles user interaction via the console and integrates with the {@link ApiClient}.
 */
public class ChatBotApp {
    private final ApiClient apiClient; // API client for interacting with the blog platform
    private final String botName;      // The name of the chatbot, loaded from configuration
    private final Scanner scanner;     // Scanner to read user input from the console

    /**
     * Constructor for ChatBotApp.
     * Loads configuration using {@link AppConfig} and initializes the API client.
     */
    public ChatBotApp() {
        // Load configurations from config.properties
        AppConfig config = new AppConfig();
        this.apiClient = new ApiClient(config.getApiBaseUrl()); // ApiClient-ის ინიციალიზაცია საბაზისო URL-ით
        this.botName = config.getBotName(); // Get the bot's name from the config
        this.scanner = new Scanner(System.in); // Initialize Scanner for console input
    }

    /**
     * Entry point for the application.
     * Greets the user and displays the main menu.
     */
    public void start() {
        System.out.println("Hello! I am " + botName + ", your blog assistant.");
        displayMenu(); // Display the main menu and start interaction loop
    }

    /**
     * Displays the main menu to the user and processes their choices.
     * The loop continues until the user chooses to exit.
     */
    private void displayMenu() {
        int choice; // Variable to store user's menu choice
        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Create new blog");
            System.out.println("2. View all blogs");
            System.out.println("3. Statistics of the site");
            System.out.println("4. Back");
            System.out.print("Please choose: ");

            try {
                // Read user input and parse it as an integer
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        createNewBlogPost(); // Call method to create a new post
                        break;
                    case 2:
                        viewAllBlogPosts();  // Call method to view all posts
                        break;
                    case 3:
                        viewSiteStatistics(); // Call method to view site statistics
                        break;
                    case 4:
                        System.out.println("Thanks for using " + botName + ". Bye!");
                        break;
                    default:
                        System.out.println("Incorrect choice, please try again.");
                }
            } catch (NumberFormatException e) {
                // Handle cases where user input is not a valid number
                System.out.println("Incorrect character, please enter valid number");
                choice = 0; // Set choice to 0 to keep the loop running
            } catch (IOException | InterruptedException e) {
                // Handle I/O errors or interruptions during API calls
                System.err.println("Mistake: " + e.getMessage());
                // Uncomment e.printStackTrace() for detailed debugging information:
                // e.printStackTrace();
                choice = 0; // Set choice to 0 to keep the loop running
            }
        } while (choice != 4); // Continue loop until user chooses option 4 (Exit)

        scanner.close(); // Close the Scanner to release system resources
    }

    /**
     * Prompts the user for details for a new blog post and attempts to create it via the API.
     *
     * @throws IOException If an I/O error occurs during the API call.
     * @throws InterruptedException If the operation is interrupted during the API call.
     */
    private void createNewBlogPost() throws IOException, InterruptedException {
        System.out.println("\n--- Create new blog ---");
        System.out.print("Please enter the name: ");
        String title = scanner.nextLine(); // Read post title from user

        System.out.print("Please enter the author: ");
        String author = scanner.nextLine(); // Read post author from user

        System.out.print("Please enter the content: ");
        String content = scanner.nextLine(); // Read post content from user

        // Validate user input: ensure title, author, and content are not empty
        if (title.trim().isEmpty() || author.trim().isEmpty() || content.trim().isEmpty()) {
            System.out.println("Error: name, author and content can not be empty..");
            return; // Exit method if validation fails
        }

        // Create a NewBlogPostRequest object with the collected data
        NewBlogPostRequest newPost = new NewBlogPostRequest(title, content, author);
        // Call the API client to create the new blog post
        apiClient.createNewBlogPost(newPost);
    }

    /**
     * Fetches all blog posts from the API and displays them on the console.
     * Includes meta information if available.
     *
     * @throws IOException If an I/O error occurs during the API call.
     * @throws InterruptedException If the operation is interrupted during the API call.
     */
    private void viewAllBlogPosts() throws IOException, InterruptedException {
        System.out.println("\n--- See all blogs ---");
        AllBlogsResponse response = apiClient.getAllBlogPosts(); // Fetch all blog posts from the API
        // Check if response is valid, has data, and data is not empty
        if (response != null && response.getData() != null && !response.getData().isEmpty()) {
            List<BlogPost> posts = response.getData();
            for (BlogPost post : posts) {
                System.out.println(post); // Print each blog post (uses BlogPost's toString method)
            }
            if (response.getMeta() != null) {
                System.out.println("Meta information: " + response.getMeta()); // Print meta information
            }
        } else {
            System.out.println("No blogs for now."); // Inform if no posts are found
        }
    }

    /**
     * Fetches site statistics from the API and displays them on the console.
     *
     * @throws IOException If an I/O error occurs during the API call.
     * @throws InterruptedException If the operation is interrupted during the API call.
     */
    private void viewSiteStatistics() throws IOException, InterruptedException {
        System.out.println("\n--- Statistics ---");
        Statistics stats = apiClient.getStatistics(); // Fetch statistics from the API
        if (stats != null) {
            System.out.println(stats); // Print statistics (uses Statistics' toString method)
        } else {
            System.out.println("Error loading statistics."); // Inform if statistics cannot be fetched
        }
    }

    /**
     * The main method to run the application.
     * Creates an instance of ChatBotApp and starts it.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        ChatBotApp app = new ChatBotApp();
        app.start(); // Start the chatbot application
    }
}
