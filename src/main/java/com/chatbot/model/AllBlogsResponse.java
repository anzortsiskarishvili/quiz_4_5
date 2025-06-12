package com.chatbot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 * Data model for the API response when fetching all blog posts (GET ?api=blogs).
 * Contains a list of {@link BlogPost} objects and {@link Meta} information.
 * Ignores any unknown JSON properties.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AllBlogsResponse {
    private List<BlogPost> data; // List of blog posts
    private Meta meta;           // Meta information about the blog posts

    /**
     * Default constructor for Jackson deserialization.
     */
    public AllBlogsResponse() {}

    /**
     * Constructor to initialize an AllBlogsResponse object.
     * @param data A list of BlogPost objects.
     * @param meta Meta information related to the blog posts.
     */
    public AllBlogsResponse(List<BlogPost> data, Meta meta) {
        this.data = data;
        this.meta = meta;
    }

    // --- Getter and Setter Methods for each property ---

    public List<BlogPost> getData() {
        return data;
    }

    public void setData(List<BlogPost> data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
