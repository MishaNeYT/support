package ru.mishaneyt.support;

import gg.voided.api.menu.Menu;
import gg.voided.api.menu.MenuHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

import ru.mishaneyt.support.api.IssueManager;
import ru.mishaneyt.support.base.BaseIssueManager;
import ru.mishaneyt.support.commands.CommandManager;
import ru.mishaneyt.support.commands.subcommands.IssueCreateCommand;
import ru.mishaneyt.support.commands.subcommands.IssueMenuCommand;

@DefaultQualifier(NonNull.class)
public final class SupportPlugin extends JavaPlugin {
  private static @MonotonicNonNull SupportPlugin pluginInstance;

  private @MonotonicNonNull IssueManager issueManager;
  private @MonotonicNonNull MenuHandler menuHandler;

  @Override
  public void onEnable() {
    pluginInstance = this;

    this.issueManager = new BaseIssueManager();
    this.menuHandler = new MenuHandler(this);

    CommandManager.registerCommand(pluginInstance, "support",
      IssueCreateCommand.class,
      IssueMenuCommand.class
    );
  }

  @Override
  public void onDisable() {
    HandlerList.unregisterAll(pluginInstance);
    menuHandler.getOpenMenus().values().forEach(Menu::close);
  }

  /**
   * Returns the singleton instance of the plugin.
   *
   * @return the instance of SupportPlugin
   */
  public static SupportPlugin getPlugin() {
    return pluginInstance;
  }

  /**
   * Returns the IssueManager instance.
   *
   * @return the IssueManager instance
   */
  public IssueManager getIssueManager() {
    return issueManager;
  }
}
