package me.majeek.template.subcommands;

import me.majeek.template.Template;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;

public class HelpCommand implements SubCommand {
    @Override
    public @NotNull String getName() {
        return "help";
    }

    @Override
    public @NotNull String getPermission() {
        return "template.help";
    }

    @Override
    public boolean allowConsole() {
        return true;
    }

    @Override
    public void execute(CommandSender player, String[] args) {
        Map<String, Object> content = Objects.requireNonNull(Template.getInstance().getMessagesConfig().getConfig().getConfigurationSection("help")).getValues(false);

        for(Object item : content.values())
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', (String) item));
    }
}
