package me.majeek.template;

import me.majeek.template.configs.MessagesConfig;
import me.majeek.template.managers.CommandManager;
import me.majeek.template.managers.EventManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Template extends JavaPlugin {
    private static Template INSTANCE = null;

    private MessagesConfig messagesConfig = null;

    private CommandManager commandManager = null;
    private EventManager eventManager = null;

    @Override
    public void onEnable() {
        INSTANCE = this;

        // Create Configs
        this.messagesConfig = new MessagesConfig("messages.yml");

        // Save Configs
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        messagesConfig.setup();
        messagesConfig.setDefaults();
        messagesConfig.getConfig().options().copyDefaults(true);
        messagesConfig.saveConfig();

        // Create Managers
        this.commandManager = new CommandManager();
        this.eventManager = new EventManager();

        // Register Managers
        this.commandManager.register();
        this.eventManager.register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Template getInstance() {
        return INSTANCE;
    }

    public MessagesConfig getMessagesConfig() {
        return this.messagesConfig;
    }

    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    public EventManager getEventManager() {
        return this.eventManager;
    }
}
