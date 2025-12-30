package me.OscarKoala.GlitchTalePlugin;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Level;

/**
 * PluginFile.java
 * Manages YAML configuration files for the GlitchTale plugin.
 * Provides methods to load, save, and manage configuration files.
 */
public class PluginFile {
    
    private final JavaPlugin plugin;
    private final String fileName;
    private File file;
    private FileConfiguration configuration;
    
    /**
     * Constructs a PluginFile instance.
     *
     * @param plugin The JavaPlugin instance
     * @param fileName The name of the configuration file (e.g., "config.yml")
     */
    public PluginFile(JavaPlugin plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        this.file = new File(plugin.getDataFolder(), fileName);
    }
    
    /**
     * Loads the configuration file from disk.
     * Creates the file from the plugin resources if it doesn't exist.
     *
     * @return true if the configuration was loaded successfully, false otherwise
     */
    public boolean loadConfiguration() {
        try {
            // Create data folder if it doesn't exist
            if (!plugin.getDataFolder().exists()) {
                if (!plugin.getDataFolder().mkdirs()) {
                    plugin.getLogger().log(Level.WARNING, "Could not create data folder for plugin");
                    return false;
                }
            }
            
            // Create file from plugin resources if it doesn't exist
            if (!file.exists()) {
                createFileFromResource();
            }
            
            // Load the configuration file
            configuration = YamlConfiguration.loadConfiguration(file);
            plugin.getLogger().log(Level.INFO, "Configuration file '" + fileName + "' loaded successfully");
            return true;
            
        } catch (Exception e) {
            plugin.getLogger().log(Level.SEVERE, "Failed to load configuration file '" + fileName + "'", e);
            return false;
        }
    }
    
    /**
     * Creates the configuration file from the plugin's resource folder.
     * If no resource exists, creates an empty file.
     */
    private void createFileFromResource() {
        try {
            InputStream inputStream = plugin.getResource(fileName);
            
            if (inputStream != null) {
                // Copy resource file to the data folder
                Files.copy(inputStream, file.toPath());
                inputStream.close();
                plugin.getLogger().log(Level.INFO, "Created '" + fileName + "' from plugin resources");
            } else {
                // Create an empty file if no resource exists
                if (file.createNewFile()) {
                    plugin.getLogger().log(Level.INFO, "Created new empty configuration file '" + fileName + "'");
                }
            }
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Failed to create configuration file '" + fileName + "'", e);
        }
    }
    
    /**
     * Saves the current configuration to disk.
     *
     * @return true if the configuration was saved successfully, false otherwise
     */
    public boolean saveConfig() {
        if (configuration == null) {
            plugin.getLogger().log(Level.WARNING, "Configuration not loaded. Cannot save '" + fileName + "'");
            return false;
        }
        
        try {
            configuration.save(file);
            plugin.getLogger().log(Level.INFO, "Configuration file '" + fileName + "' saved successfully");
            return true;
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Failed to save configuration file '" + fileName + "'", e);
            return false;
        }
    }
    
    /**
     * Reloads the configuration from disk, discarding any unsaved changes.
     *
     * @return true if the configuration was reloaded successfully, false otherwise
     */
    public boolean reloadConfiguration() {
        return loadConfiguration();
    }
    
    /**
     * Gets the FileConfiguration object.
     *
     * @return the FileConfiguration, or null if not loaded
     */
    public FileConfiguration getConfiguration() {
        return configuration;
    }
    
    /**
     * Gets the File object.
     *
     * @return the File object
     */
    public File getFile() {
        return file;
    }
    
    /**
     * Checks if the configuration is loaded.
     *
     * @return true if the configuration is loaded, false otherwise
     */
    public boolean isLoaded() {
        return configuration != null;
    }
    
    /**
     * Gets the name of the configuration file.
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }
}
