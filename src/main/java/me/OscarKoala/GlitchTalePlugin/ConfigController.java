package me.OscarKoala.GlitchTalePlugin;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

/**
 * ConfigController - Manages plugin configuration and settings
 * Handles loading, saving, and accessing configuration values
 */
public class ConfigController {

    private final JavaPlugin plugin;
    private FileConfiguration config;
    private File configFile;

    /**
     * NextLifeMode Enum - Defines different game mode settings
     */
    public enum NextLifeMode {
        PEACEFUL("Peaceful", 0),
        EASY("Easy", 1),
        NORMAL("Normal", 2),
        HARD("Hard", 3);

        private final String displayName;
        private final int level;

        NextLifeMode(String displayName, int level) {
            this.displayName = displayName;
            this.level = level;
        }

        /**
         * Get the display name of the mode
         * @return The display name
         */
        public String getDisplayName() {
            return displayName;
        }

        /**
         * Get the difficulty level
         * @return The numeric level
         */
        public int getLevel() {
            return level;
        }

        /**
         * Get NextLifeMode from string
         * @param name The name of the mode
         * @return The NextLifeMode or NORMAL as default
         */
        public static NextLifeMode fromString(String name) {
            try {
                return NextLifeMode.valueOf(name.toUpperCase());
            } catch (IllegalArgumentException e) {
                return NORMAL;
            }
        }
    }

    /**
     * Constructor for ConfigController
     * @param plugin The JavaPlugin instance
     */
    public ConfigController(JavaPlugin plugin) {
        this.plugin = plugin;
        this.configFile = new File(plugin.getDataFolder(), "config.yml");
    }

    /**
     * Load the configuration file
     */
    public void loadConfig() {
        try {
            if (!configFile.exists()) {
                createDefaultConfig();
            }
            config = YamlConfiguration.loadConfiguration(configFile);
            plugin.getLogger().log(Level.INFO, "Configuration loaded successfully");
        } catch (Exception e) {
            plugin.getLogger().log(Level.SEVERE, "Failed to load configuration", e);
        }
    }

    /**
     * Save the current configuration to file
     */
    public void saveConfig() {
        try {
            if (config != null) {
                config.save(configFile);
                plugin.getLogger().log(Level.INFO, "Configuration saved successfully");
            }
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Failed to save configuration", e);
        }
    }

    /**
     * Create default configuration with initial settings
     */
    private void createDefaultConfig() {
        try {
            if (!plugin.getDataFolder().exists()) {
                plugin.getDataFolder().mkdirs();
            }
            configFile.createNewFile();
            config = new YamlConfiguration();
            setDefaults();
            config.save(configFile);
            plugin.getLogger().log(Level.INFO, "Default configuration created");
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Failed to create default configuration", e);
        }
    }

    /**
     * Set default configuration values
     */
    private void setDefaults() {
        // Server settings
        config.set("server.name", "Glitchtale Server");
        config.set("server.description", "A Glitchtale-themed Minecraft server");
        config.set("server.debug", false);

        // Game settings
        config.set("game.default-mode", "NORMAL");
        config.set("game.max-players", 20);
        config.set("game.pvp-enabled", true);
        config.set("game.respawn-delay", 5);

        // World settings
        config.set("world.difficulty", "NORMAL");
        config.set("world.auto-save-interval", 6000);
        config.set("world.spawn-protection", 16);

        // Plugin features
        config.set("features.enabled-modules", Arrays.asList("core", "gameplay", "events"));
        config.set("features.auto-update", true);
        config.set("features.metrics-enabled", true);

        // Chat settings
        config.set("chat.color-codes-enabled", true);
        config.set("chat.format", "&f[&b%player%&f]: &r%message%");

        // Commands
        config.set("commands.cooldown-enabled", true);
        config.set("commands.cooldown-duration", 1);
    }

    /**
     * Get the current FileConfiguration
     * @return The FileConfiguration object
     */
    public FileConfiguration getConfig() {
        return config;
    }

    /**
     * Get the NextLifeMode setting
     * @return The NextLifeMode enum value
     */
    public NextLifeMode getNextLifeMode() {
        String modeString = config.getString("game.default-mode", "NORMAL");
        return NextLifeMode.fromString(modeString);
    }

    /**
     * Set the NextLifeMode setting
     * @param mode The NextLifeMode to set
     */
    public void setNextLifeMode(NextLifeMode mode) {
        config.set("game.default-mode", mode.name());
        saveConfig();
    }

    /**
     * Get a string configuration value
     * @param path The configuration path
     * @param defaultValue The default value if not found
     * @return The configuration value
     */
    public String getString(String path, String defaultValue) {
        return config.getString(path, defaultValue);
    }

    /**
     * Get an integer configuration value
     * @param path The configuration path
     * @param defaultValue The default value if not found
     * @return The configuration value
     */
    public int getInt(String path, int defaultValue) {
        return config.getInt(path, defaultValue);
    }

    /**
     * Get a boolean configuration value
     * @param path The configuration path
     * @param defaultValue The default value if not found
     * @return The configuration value
     */
    public boolean getBoolean(String path, boolean defaultValue) {
        return config.getBoolean(path, defaultValue);
    }

    /**
     * Get a double configuration value
     * @param path The configuration path
     * @param defaultValue The default value if not found
     * @return The configuration value
     */
    public double getDouble(String path, double defaultValue) {
        return config.getDouble(path, defaultValue);
    }

    /**
     * Get a list configuration value
     * @param path The configuration path
     * @return The list of values
     */
    public List<?> getList(String path) {
        return config.getList(path);
    }

    /**
     * Get a list of strings
     * @param path The configuration path
     * @return The list of strings
     */
    public List<String> getStringList(String path) {
        return config.getStringList(path);
    }

    /**
     * Set a configuration value
     * @param path The configuration path
     * @param value The value to set
     */
    public void set(String path, Object value) {
        config.set(path, value);
        saveConfig();
    }

    /**
     * Check if a configuration path exists
     * @param path The configuration path
     * @return True if the path exists, false otherwise
     */
    public boolean contains(String path) {
        return config.contains(path);
    }

    /**
     * Reload the configuration from file
     */
    public void reloadConfig() {
        loadConfig();
        plugin.getLogger().log(Level.INFO, "Configuration reloaded");
    }

    /**
     * Reset configuration to defaults
     */
    public void resetToDefaults() {
        config = new YamlConfiguration();
        setDefaults();
        saveConfig();
        plugin.getLogger().log(Level.INFO, "Configuration reset to defaults");
    }

    /**
     * Get the difficulty level from NextLifeMode
     * @return The difficulty level as integer
     */
    public int getDifficultyLevel() {
        return getNextLifeMode().getLevel();
    }

    /**
     * Check if debug mode is enabled
     * @return True if debug mode is enabled
     */
    public boolean isDebugEnabled() {
        return getBoolean("server.debug", false);
    }

    /**
     * Get the maximum number of players
     * @return The maximum player count
     */
    public int getMaxPlayers() {
        return getInt("game.max-players", 20);
    }

    /**
     * Check if PVP is enabled
     * @return True if PVP is enabled
     */
    public boolean isPvpEnabled() {
        return getBoolean("game.pvp-enabled", true);
    }

    /**
     * Get the respawn delay in seconds
     * @return The respawn delay
     */
    public int getRespawnDelay() {
        return getInt("game.respawn-delay", 5);
    }
}
