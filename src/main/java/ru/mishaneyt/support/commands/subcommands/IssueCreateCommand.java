package ru.mishaneyt.support.commands.subcommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.mishaneyt.support.SupportPlugin;
import ru.mishaneyt.support.commands.ISubCommand;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public class IssueCreateCommand implements ISubCommand {

  @Override
  public String argument() {
    return "create";
  }

  @Override
  public String permission() {
    return "support.command.create";
  }

  @Override
  public void execute(final CommandSender sender, final String[] args) {
    if (!(sender instanceof Player player)) {
      sender.sendMessage("§cA command can only be entered by a player.");
      return;
    }
    if (args.length < 1) {
      player.sendMessage("§cNot enough arguments.");
      return;
    }

    var message = String.join(" ", args)
      .substring(args[1].length() + 1);

    SupportPlugin.getPlugin().getIssueManager().createIssue(player, message)
      .thenAccept(issue -> {
        if (issue == null) {
          player.sendMessage("§cYou already have an issue.");
          return;
        }
        player.sendMessage("§eIssue successfully created. Wait for support, it may take a few minutes. Stay in the game!");
      });
  }

  @Override
  public @Unmodifiable List<String> complete(final CommandSender sender, final String[] args) {
    return List.of();
  }
}
