package com.chatbot.model;

/**
 * Represents the JSON request body for creating a new blog post (POST ?api=blogs).
 * This class holds the title, content, and author of the new post.
 */
public class NewBlogPostRequest {
    private String title;   // The title of the new blog post
    private String content; // The content of the new blog post
    private String author;  // The author of the new blog post

    /**
     * Constructor to initialize a NewBlogPostRequest object.
     * @param title The title for the new post.
     * @param content The content for the new post.
     * @param author The author of the new post.
     */
    public NewBlogPostRequest(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // --- Getter Methods for properties (setters are not needed for a request object) ---

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }
}
