package ru.mishaneyt.support;

import org.bukkit.plugin.java.JavaPlugin;

import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

import ru.mishaneyt.support.api.IssueManager;
import ru.mishaneyt.support.base.BaseIssueManager;
import ru.mishaneyt.support.commands.CommandManager;
import ru.mishaneyt.support.commands.subcommands.IssueCreateCommand;

@DefaultQualifier(NonNull.class)
public final class SupportPlugin extends JavaPlugin {
  private static @MonotonicNonNull SupportPlugin pluginInstance;

  private @MonotonicNonNull IssueManager issueManager;

  @Override
  public void onEnable() {
    pluginInstance = this;

    this.issueManager = new BaseIssueManager();

    CommandManager.registerCommand(pluginInstance, "support", IssueCreateCommand.class);
  }

  @Override
  public void onDisable() {
    // pon ?
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
