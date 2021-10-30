package me.majeek.template.configs;

import me.majeek.template.Template;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class MessagesConfig {
    private String path = null;

    private FileConfiguration config = null;
    private File file = null;

    public MessagesConfig(@NotNull String path) {
        this.path = path;
    }

    public void setup() {
        this.file = new File(Template.getInstance().getDataFolder(), this.path);

        if(!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException exception) {
                Template.getInstance().getLogger().log(Level.SEVERE, this.path + " could not be successfully created!", exception);
            }
        }

        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public void setDefaults() {
        // Prefix
        this.config.addDefault("prefix", "&7[&aTown of Salem&7] ");

        // Error
        this.config.addDefault("error.no-permission", "&cYou do not have permission to perform this command.");
        this.config.addDefault("error.console-sender", "&cYou must be a player to perform this command.");

        // Help
        this.config.addDefault("help.header", "&7------------------ [&a Town of Salem &7] ------------------");
        this.config.addDefault("help.help", "&7- &a/tos help &f- &7Displays this.");
        this.config.addDefault("help.reload", "&7- &a/tos reload &f- &7Reloads the config files.");

        // Reload
        this.config.addDefault("reload.reload", "&9Config files have been reloaded.");
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public void saveConfig() {
        try {
            this.config.save(this.file);
        } catch (IOException exception) {
            Template.getInstance().getLogger().log(Level.SEVERE, this.path + " could not successfully save!", exception);
        }
    }

    public void reloadConfig() {
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }
}