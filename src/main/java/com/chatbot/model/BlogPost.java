
package com.chatbot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Data model representing a single blog post.
 * Ignores any unknown JSON properties during deserialization to prevent errors
 * if the API returns additional fields not defined in this model.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlogPost {
    private String id;
    private String title;
    private String author;
    private String content;
    private String created_at; // Represents the creation timestamp as a string from the API

    /**
     * Default constructor for Jackson deserialization.
     * Jackson requires a no-argument constructor to create instances from JSON.
     */
    public BlogPost() {}

    /**
     * Constructor to initialize a BlogPost object with all its properties.
     * @param id The unique identifier of the blog post.
     * @param title The title of the blog post.
     * @param author The author of the blog post.
     * @param content The main content of the blog post.
     * @param created_at The timestamp when the blog post was created.
     */
    public BlogPost(String id, String title, String author, String content, String created_at) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.created_at = created_at;
    }

    // --- Getter and Setter Methods for each property ---

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    /**
     * Provides a formatted string representation of the BlogPost object.
     * This is useful for printing blog post details to the console.
     * @return A string containing the blog post's ID, title, author, content, and creation date.
     */
    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Title: " + title + "\n" +
                "Author: " + author + "\n" +
                "Content: " + content + "\n" +
                "Created at: " + created_at + "\n" +
                "------------------------------------";
    }
}

