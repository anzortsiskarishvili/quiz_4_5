package com.chatbot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Data model for system statistics returned by the API (GET ?api=stats).
 * Ignores any unknown JSON properties.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Statistics {
    private int total_posts;        // Total number of posts on the system
    private int max_posts;          // Maximum allowed posts on the system
    private int remaining_posts;    // Number of remaining posts that can be added
    private double percentage_used; // Percentage of post capacity used
    private boolean can_add_more;   // Flag indicating if more posts can be added

    /**
     * Default constructor for Jackson deserialization.
     */
    public Statistics() {}

    /**
     * Constructor to initialize a Statistics object.
     * @param total_posts The total number of blog posts.
     * @param max_posts The maximum number of posts allowed.
     * @param remaining_posts The number of posts still available to be added.
     * @param percentage_used The percentage of post capacity that has been used.
     * @param can_add_more A boolean indicating if new posts can still be added.
     */
    public Statistics(int total_posts, int max_posts, int remaining_posts, double percentage_used, boolean can_add_more) {
        this.total_posts = total_posts;
        this.max_posts = max_posts;
        this.remaining_posts = remaining_posts;
        this.percentage_used = percentage_used;
        this.can_add_more = can_add_more;
    }

    // --- Getter and Setter Methods for each property ---

    public int getTotal_posts() {
        return total_posts;
    }

    public void setTotal_posts(int total_posts) {
        this.total_posts = total_posts;
    }

    public int getMax_posts() {
        return max_posts;
    }

    public void setMax_posts(int max_posts) {
        this.max_posts = max_posts;
    }

    public int getRemaining_posts() {
        return remaining_posts;
    }

    public void setRemaining_posts(int remaining_posts) {
        this.remaining_posts = remaining_posts;
    }

    public double getPercentage_used() {
        return percentage_used;
    }

    public void setPercentage_used(double percentage_used) {
        this.percentage_used = percentage_used;
    }

    public boolean isCan_add_more() {
        return can_add_more;
    }

    public void setCan_add_more(boolean can_add_more) {
        this.can_add_more = can_add_more;
    }

    /**
     * Provides a formatted string representation of the Statistics object.
     * This is useful for printing system statistics to the console.
     * @return A string containing the system statistics.
     */
    @Override
    public String toString() {
        return "System Statistics:\n" +
                "  Total posts: " + total_posts + "\n" +
                "  Max posts: " + max_posts + "\n" +
                "  Remaining posts: " + remaining_posts + "\n" +
                "  Percentage used: " + String.format("%.2f", percentage_used) + "%\n" +
                "  Can add more: " + (can_add_more ? "Yes" : "No");
    }
}
