# GlitchTalePlugin

A powerful and feature-rich Spigot plugin for Minecraft 1.21 that brings immersive gameplay enhancements and advanced server management capabilities.

## Project Information

- **Plugin Name:** GlitchTalePlugin
- **Version:** 1.5.0
- **Minecraft Version:** 1.21
- **Server Software:** Spigot
- **Language:** Java

## Features

- **Advanced Event System** - Comprehensive event handling and custom event creation
- **Player Management** - Enhanced player tracking and management tools
- **World Interactions** - Dynamic world manipulation and custom biome support
- **Performance Optimization** - Lightweight codebase with minimal server impact
- **Configuration System** - Flexible YAML-based configuration files
- **Command API** - Intuitive command framework for easy command creation
- **Database Support** - MySQL and SQLite integration for data persistence
- **Permissions Integration** - Full support for Permission plugins
- **Logging System** - Comprehensive debug and error logging
- **Custom Items & Effects** - Create custom items with special effects
- **Party System** - Player grouping and team management
- **Economy Integration** - Vault API integration for economy support

## Dependencies

- **Java:** Java 17 or higher
- **Spigot API:** 1.21+
- **Maven:** 3.6.0 or higher (for building)
- **Vault API:** 1.7+ (for economy integration) - Optional
- **WorldEdit:** 7.2.5+ (for world manipulation features) - Optional

## Requirements

- Spigot server version 1.21 or higher
- Java 17+ runtime environment
- At least 512MB RAM allocated to the server (recommended 1GB+)
- File write permissions for the plugins directory

## Build Instructions

### Prerequisites

Ensure you have the following installed on your system:

1. **Java Development Kit (JDK) 17 or higher**
   ```bash
   java -version
   ```

2. **Maven 3.6.0 or higher**
   ```bash
   mvn -version
   ```

### Building the Plugin

1. **Clone the repository:**
   ```bash
   git clone https://github.com/redsm1the/Glitchtale.git
   cd Glitchtale
   ```

2. **Build using Maven:**
   ```bash
   mvn clean package
   ```

3. **Locate the built JAR file:**
   - The compiled plugin JAR will be located in the `target/` directory
   - File name format: `GlitchTalePlugin-1.5.0.jar`

### Build Options

- **Skip tests during build:**
  ```bash
  mvn clean package -DskipTests
  ```

- **Create a shaded JAR (with dependencies):**
  ```bash
  mvn clean package shade:shade
  ```

- **Development build with debugging:**
  ```bash
  mvn clean package -X
  ```

## Installation Guide

### Step 1: Prepare Your Server

1. Ensure you have a Spigot server running version 1.21 or higher
2. Verify that the `plugins/` directory exists in your server folder
3. Make sure your server is stopped before installing the plugin

### Step 2: Install the Plugin

1. **Build the plugin** (see Build Instructions section above) or download a pre-built JAR file
2. **Copy the JAR file** to your server's `plugins/` directory:
   ```bash
   cp target/GlitchTalePlugin-1.5.0.jar /path/to/server/plugins/
   ```

### Step 3: Configuration

1. **Start the server** to generate default configuration files:
   ```bash
   ./start.sh
   ```
   
2. **Stop the server** once the plugin has generated its config files

3. **Edit configuration files** in the `plugins/GlitchTalePlugin/` directory:
   - `config.yml` - Main plugin configuration
   - `messages.yml` - Customizable messages and strings
   - `features.yml` - Feature-specific settings

### Step 4: Install Optional Dependencies

- **For economy features:** Install Vault and an economy plugin (e.g., EssentialsX)
- **For world manipulation:** Install WorldEdit plugin
- **For permissions management:** Install a permissions plugin (e.g., LuckPerms)

### Step 5: Verify Installation

1. **Start your server**
2. **Check the console** for messages confirming GlitchTalePlugin has loaded
3. **In-game:** Type `/gtp help` or `/glitchtale help` to verify the plugin is active
4. **Check permissions:** Ensure your user group has the required permissions to use plugin commands

### Step 6: Set Permissions

Configure permissions in your permissions plugin:

```yaml
permissions:
  glitchtale.admin: true
  glitchtale.user: true
  glitchtale.commands.*: true
```

## Configuration

### Basic Configuration (config.yml)

```yaml
# GlitchTalePlugin Configuration

plugin:
  version: 1.5.0
  debug: false
  enabled: true

database:
  enabled: false
  type: "sqlite"  # or "mysql"
  # MySQL Configuration
  # host: localhost
  # port: 3306
  # username: root
  # password: password
  # database: glitchtale

features:
  player-tracking: true
  custom-events: true
  world-interactions: true
  logging: true
```

## Commands

### Admin Commands

- `/gtp reload` - Reload plugin configuration
- `/gtp status` - Display plugin status
- `/gtp help` - Show help information
- `/gtp debug [on/off]` - Toggle debug mode

### User Commands

- `/glitchtale [subcommand]` - Main plugin command
- `/gt info` - Display plugin information
- `/gt version` - Show plugin version

## Troubleshooting

### Plugin Not Loading

- **Check Java version:** Ensure you're using Java 17 or higher
- **Check Spigot version:** Plugin requires Spigot 1.21+
- **Check console logs:** Look for error messages in `latest.log`
- **Verify JAR file:** Ensure the JAR file is not corrupted

### Performance Issues

- Enable debug mode to identify bottlenecks
- Disable unused features in `config.yml`
- Increase server RAM allocation
- Check for plugin conflicts with other plugins

### Database Connection Issues

- Verify database credentials in `config.yml`
- Ensure database server is running and accessible
- Check network connectivity
- Review plugin logs for detailed error messages

## Support and Documentation

- **GitHub Repository:** https://github.com/redsm1the/Glitchtale
- **Issue Tracker:** https://github.com/redsm1the/Glitchtale/issues
- **Wiki:** https://github.com/redsm1the/Glitchtale/wiki

## Contributing

We welcome contributions! To contribute:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Changelog

### Version 1.5.0 (2025-12-29)

- Initial release
- Full Minecraft 1.21 support
- Core plugin framework
- Player management system
- World interaction features
- Configuration system
- Database support (SQLite/MySQL)
- Permission integration
- Comprehensive logging

## Support

If you encounter any issues or have questions, please:

1. Check the [troubleshooting section](#troubleshooting)
2. Search existing [GitHub Issues](https://github.com/redsm1the/Glitchtale/issues)
3. Open a new issue with detailed information

---

**Last Updated:** 2025-12-29
**Maintained by:** redsm1the
