package ru.mishaneyt.support.commands.subcommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.Unmodifiable;

import ru.mishaneyt.support.base.menu.BaseIssuesPaginatedMenu;
import ru.mishaneyt.support.commands.ISubCommand;

import java.util.List;

public class IssueMenuCommand implements ISubCommand {

  @Override
  public String argument() {
    return "menu";
  }

  @Override
  public String permission() {
    return "support.command.menu";
  }

  @Override
  public void execute(final CommandSender sender, final String[] args) {
    if (!(sender instanceof Player player)) {
      sender.sendMessage("§cA command can only be entered by a player.");
      return;
    }

    new BaseIssuesPaginatedMenu(player).open();
  }

  @Override
  public @Unmodifiable List<String> complete(final CommandSender sender, final String[] args) {
    return List.of();
  }
}
