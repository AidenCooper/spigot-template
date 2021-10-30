package me.majeek.template.subcommands;

import me.majeek.template.Template;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ReloadCommand implements SubCommand {
    @Override
    public @NotNull String getName() {
        return "reload";
    }

    @Override
    public @NotNull String getPermission() {
        return "template.reload";
    }

    @Override
    public boolean allowConsole() {
        return true;
    }

    @Override
    public void execute(CommandSender player, String[] args) {
        Template.getInstance().reloadConfig();
        Template.getInstance().getMessagesConfig().reloadConfig();

        String prefix = Objects.requireNonNull(Template.getInstance().getMessagesConfig().getConfig().getString("prefix"));
        String content = Objects.requireNonNull(Template.getInstance().getMessagesConfig().getConfig().getString("reload.reload"));

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + content));
    }
}
