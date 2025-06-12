package com.chatbot.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration loader class for the chatbot application.
 * Reads the API URL and bot name from the 'config.properties' file.
 */
public class AppConfig {
    // The name of the configuration file
    private static final String CONFIG_FILE = "config.properties";
    // Properties object to hold the loaded configurations
    private Properties properties;

    /**
     * Constructor for AppConfig.
     * Initializes properties and attempts to load them from the config file.
     * If the file is not found or an error occurs during loading,
     * default values for API URL and bot name are used.
     */
    public AppConfig() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            // Check if the input stream for the config file is null (file not found)
            if (input == null) {
                System.err.println("Error: Configuration file '" + CONFIG_FILE + "' not found.");
                // Set default values if the file is missing
                properties.setProperty("api.base.url", "http://max.ge/q45/84916273/index.php");
                properties.setProperty("bot.name", "DefaultBlogBot");
                return; // Exit the constructor
            }
            properties.load(input); // Load properties from the input stream
        } catch (IOException ex) {
            // Handle any I/O errors during file loading
            System.err.println("Error loading configuration file: " + ex.getMessage());
            // Set default values in case of an I/O error
            properties.setProperty("api.base.url", "http://max.ge/q45/84916273/index.php");
            properties.setProperty("bot.name", "DefaultBlogBot");
        }
    }

    /**
     * Returns the base URL for the REST API.
     *
     * @return The API base URL read from config or default.
     */
    public String getApiBaseUrl() {
        return properties.getProperty("api.base.url");
    }

    /**
     * Returns the name of the chat bot.
     *
     * @return The bot's name read from config or default.
     */
    public String getBotName() {
        return properties.getProperty("bot.name");
    }
}

