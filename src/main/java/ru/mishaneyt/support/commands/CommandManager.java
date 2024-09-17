package ru.mishaneyt.support.commands;

import com.google.common.collect.Lists;

import org.apache.commons.lang.Validate;

import org.bukkit.command.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class CommandManager implements CommandExecutor, TabCompleter {
  private final List<ISubCommand> subCommands;

  public CommandManager() {
    this.subCommands = Lists.newCopyOnWriteArrayList();
  }

  /**
   * Registers a command with the given plugin and command label using the provided subcommand classes.
   *
   * @param plugin       the plugin registering the command
   * @param command      the command label
   * @param subCommands  varargs of subcommand classes to register
   */
  @SafeVarargs
  public static void registerCommand(final @NotNull JavaPlugin plugin, final @NotNull String command,
                                     final Class<? extends ISubCommand>... subCommands) {
    registerCommand(plugin, command, Arrays.asList(subCommands));
  }

  /**
   * Registers a command with the given plugin and command label using the provided list of subcommand classes.
   *
   * @param plugin       the plugin registering the command
   * @param label        the command label
   * @param subCommands  list of subcommand classes to register
   */
  public static void registerCommand(@NotNull final JavaPlugin plugin, @NotNull final String label,
                                     @NotNull final List<Class<? extends ISubCommand>> subCommands) {
    var commandManager = new CommandManager();
    var command = plugin.getCommand(label);
    Validate.notNull(command, "Could not find the command in `plugins.yml`.");

    command.setExecutor(commandManager);
    command.setTabCompleter(commandManager);

    for (var subCommandClass : subCommands) {
      try {
        var subCommand = subCommandClass.getDeclaredConstructor().newInstance();
        commandManager.subCommands.add(subCommand);
      } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException exception) {
        exception.printStackTrace();
      }
    }
  }

  @Override
  public boolean onCommand(@NotNull final CommandSender sender, @NotNull final Command command,
                           @NotNull final String label, final String[] args) {
    synchronized (subCommands) {
      for (var subCommand : subCommands) {
        if (args.length > 0 && args[0].equalsIgnoreCase(subCommand.argument())) {
          if (sender.hasPermission(subCommand.permission())) {
            subCommand.execute(sender, args);
          } else {
            sender.sendMessage("You do not have permission to use this command.");
          }
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public List<String> onTabComplete(@NotNull final CommandSender sender, @NotNull final Command command,
                                    @NotNull final String alias, final String @NotNull [] args) {
    synchronized (subCommands) {
      if (args.length == 1) {
        return subCommands.stream()
          .map(ISubCommand::argument)
          .filter(arg -> arg.toLowerCase().startsWith(args[0].toLowerCase()))
          .collect(Collectors.toList());
      } else if (args.length > 1) {
        return subCommands.stream()
          .filter(subCommand -> subCommand.argument().equalsIgnoreCase(args[0].toLowerCase()))
          .findFirst()
          .map(subCommand -> subCommand.complete(sender, args))
          .orElseGet(ArrayList::new);
      }
      return Lists.newArrayList();
    }
  }
}
