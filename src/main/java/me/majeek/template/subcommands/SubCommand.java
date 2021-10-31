package me.majeek.template.subcommands;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public interface SubCommand {
    @NotNull String[] getName();

    @NotNull String getPermission();

    boolean allowConsole();

    int requiredArgs();

    void execute(CommandSender player, String[] args);
}
