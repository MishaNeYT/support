package ru.mishaneyt.support.base;

import com.google.common.collect.Maps;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import ru.mishaneyt.support.SupportPlugin;
import ru.mishaneyt.support.api.Issue;
import ru.mishaneyt.support.api.IssueManager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public final class BaseIssueManager implements IssueManager {
  private final Map<UUID, Issue> issues;

  public BaseIssueManager() {
    this.issues = Maps.newConcurrentMap();

    // Testing
    for (int i = 0; i <= 32; i++) {
      var bukkitPlayer = Bukkit.getOfflinePlayer(UUID.fromString("8e562d87-c1ee-3fb1-b084-230f13c38e20"));
      this.issues.put(UUID.randomUUID(), new BaseIssue(bukkitPlayer, "Test " + i));
    }

    SupportPlugin.getPlugin().getLogger().info("Issues count: " + this.issues.size());
  }

  @Override
  public @NotNull CompletableFuture<Issue> createIssue(final @NotNull OfflinePlayer player, final @NotNull String reason) {
    final CompletableFuture<Issue> completableFuture = new CompletableFuture<>();

    var currentIssue = getIssueByPlayer(player);
    if (currentIssue.isPresent()) {
      completableFuture.complete(null);
      return completableFuture;
    }

    var issue = new BaseIssue(player, reason);
    issues.put(player.getUniqueId(), issue);
    completableFuture.complete(issue);

    return completableFuture;
  }

  @Override
  public @NotNull CompletableFuture<Issue> closeIssue(final @NotNull OfflinePlayer player, final String reason) {
    final CompletableFuture<Issue> completableFuture = new CompletableFuture<>();
    if (reason == null) {
      // A
    }
    var issue = issues.remove(player.getUniqueId());
    // issue.setCloseReason(reason);

    return completableFuture;
  }

  @Override
  public Optional<Issue> getIssueByPlayer(final @NotNull OfflinePlayer player) {
    return getIssueByUUID(player.getUniqueId());
  }

  @Override
  public Optional<Issue> getIssueByUUID(final @NotNull UUID uuid) {
    return Optional.ofNullable(issues.get(uuid));
  }

  @Override
  public @Unmodifiable Map<UUID, Issue> getIssues() {
    return Map.copyOf(issues);
  }
}
