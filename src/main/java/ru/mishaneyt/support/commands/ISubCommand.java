package ru.mishaneyt.support.commands;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface ISubCommand {

  /**
   * Returns the argument associated with this subcommand.
   *
   * @return String representation of the subcommand argument
   */
  String argument();

  /**
   * Returns the permission required to execute this subcommand.
   *
   * @return String representation of the permission
   */
  String permission();

  /**
   * Executes the subcommand.
   *
   * @param sender Sender of the command (could be a player, console, etc.)
   * @param args Arguments of the command
   */
  void execute(final CommandSender sender, final String[] args);

  /**
   * Provides a list of possible argument completions for command auto-completion.
   *
   * @param sender Sender of the command (could be a player, console, etc.)
   * @param args Arguments of the command
   * @return List of strings with possible completions
   */
  List<String> complete(final CommandSender sender, final String[] args);
}
