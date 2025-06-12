package com.chatbot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Data model for meta information returned by the API,
 * typically including total count, limit, and a flag indicating if more items can be added.
 * Ignores any unknown JSON properties.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Meta {
    private int total;          // Total number of items
    private int limit;          // Current limit of items
    private boolean can_add_more; // Flag if more items can be added

    /**
     * Default constructor for Jackson deserialization.
     */
    public Meta() {}

    /**
     * Constructor to initialize a Meta object.
     * @param total The total number of posts.
     * @param limit The current limit for posts.
     * @param can_add_more A boolean indicating if more posts can be added.
     */
    public Meta(int total, int limit, boolean can_add_more) {
        this.total = total;
        this.limit = limit;
        this.can_add_more = can_add_more;
    }

    // --- Getter and Setter Methods for each property ---

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public boolean isCan_add_more() {
        return can_add_more;
    }

    public void setCan_add_more(boolean can_add_more) {
        this.can_add_more = can_add_more;
    }

    /**
     * Provides a formatted string representation of the Meta object.
     * @return A string containing the meta information.
     */
    @Override
    public String toString() {
        return "Total posts: " + total + ", Limit: " + limit + ", Can add more: " + (can_add_more ? "Yes" : "No");
    }
}

