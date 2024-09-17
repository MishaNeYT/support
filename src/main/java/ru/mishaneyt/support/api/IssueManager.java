package ru.mishaneyt.support.api;

import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IssueManager {

  /**
   * Creates a new support issue for the player with the given reason.
   *
   * This method is asynchronous and returns a {@link CompletableFuture} that will
   * complete with the newly created {@link Issue}. This allows for non-blocking
   * execution and handling of the result once the issue has been created.
   *
   * @param player the player for whom the issue is being created.
   * @param reason a string representing the reason for create the issue.
   * @return a {@link CompletableFuture} that will complete with the created {@link Issue}
   */
  CompletableFuture<Issue> createIssue(final @NotNull OfflinePlayer player, final String reason);

  /**
   * Closes an existing support issue for the player with the provided reason.
   *
   * This method is asynchronous and returns a {@link CompletableFuture} that will
   * complete with the closed {@link Issue}. This allows for non-blocking execution
   * and handling of the result once the issue has been closed.
   *
   * @param player the player for whom the issue is being closed.
   * @param reason a string representing the reason for closing the issue.
   * @return a {@link CompletableFuture} that will complete with the closed {@link Issue}
   */
  CompletableFuture<Issue> closeIssue(final @NotNull OfflinePlayer player, final String reason);


  /**
   * Retrieves the support issue associated with the player.
   *
   * @param player the player whose issue is being retrieved.
   * @return an {@code Optional} containing the issue if found, otherwise an empty {@code Optional}.
   */
  Optional<Issue> getIssueByPlayer(final @NotNull OfflinePlayer player);

  /**
   * Retrieves the support issue associated with the UUID.
   *
   * @param uuid the UUID of the issue being retrieved.
   * @return an {@code Optional} containing the issue if found, otherwise an empty {@code Optional}.
   */
  Optional<Issue> getIssueByUUID(final @NotNull UUID uuid);

  /**
   * Returns all current support issues.
   *
   * @return a map containing all support issues, where the key is the player's UUID
   *         and the value is the associated {@code Issue}.
   */
  Map<UUID, Issue> getIssues();
}

