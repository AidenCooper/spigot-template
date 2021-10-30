package me.majeek.template.managers;

import me.majeek.template.Template;
import me.majeek.template.subcommands.HelpCommand;
import me.majeek.template.subcommands.ReloadCommand;
import me.majeek.template.subcommands.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandManager implements CommandExecutor {
    private final String COMMAND_NAME = "template";
    private final List<SubCommand> SUB_COMMANDS = new ArrayList<>();

    public void register() {
        this.SUB_COMMANDS.add(new HelpCommand());
        this.SUB_COMMANDS.add(new ReloadCommand());

        Objects.requireNonNull(Template.getInstance().getCommand(this.COMMAND_NAME)).setExecutor(this);
    }

    public List<SubCommand> getSubCommands() {
        return this.SUB_COMMANDS;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        boolean isPlayer = sender instanceof Player;

        if(args.length == 0) {
            for(SubCommand subCommand : this.SUB_COMMANDS) {
                if(subCommand.getName().equalsIgnoreCase("help")) {
                    if(isPlayer || subCommand.allowConsole()) {
                        if(sender.hasPermission(subCommand.getPermission())) {
                            subCommand.execute(sender, args);

                            return true;
                        } else {
                            String prefix = Objects.requireNonNull(Template.getInstance().getMessagesConfig().getConfig().getString("prefix"));
                            String content = Objects.requireNonNull(Template.getInstance().getMessagesConfig().getConfig().getString("error.no-permission"));

                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + content));

                            return false;
                        }
                    } else {
                        String prefix = Objects.requireNonNull(Template.getInstance().getMessagesConfig().getConfig().getString("prefix"));
                        String content = Objects.requireNonNull(Template.getInstance().getMessagesConfig().getConfig().getString("error.console-sender"));

                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + content));

                        return false;
                    }
                }
            }
        } else {
            for(SubCommand subCommand : this.SUB_COMMANDS) {
                if(subCommand.getName().equalsIgnoreCase(args[0])) {
                    if(isPlayer || subCommand.allowConsole()) {
                        if(sender.hasPermission(subCommand.getPermission())) {
                            subCommand.execute(sender, args);

                            return true;
                        } else {
                            String prefix = Objects.requireNonNull(Template.getInstance().getMessagesConfig().getConfig().getString("prefix"));
                            String content = Objects.requireNonNull(Template.getInstance().getMessagesConfig().getConfig().getString("error.no-permission"));

                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + content));

                            return false;
                        }
                    } else {
                        String prefix = Objects.requireNonNull(Template.getInstance().getMessagesConfig().getConfig().getString("prefix"));
                        String content = Objects.requireNonNull(Template.getInstance().getMessagesConfig().getConfig().getString("error.console-sender"));

                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + content));

                        return false;
                    }
                }
            }
        }

        return false;
    }
}
