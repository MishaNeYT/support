package ru.mishaneyt.support.api;

import org.bukkit.OfflinePlayer;

import java.time.LocalDateTime;
import java.util.Optional;

public interface Issue {

  /**
   * Retrieves the player who created the issue.
   *
   * @return the {@link OfflinePlayer} who authored the issue.
   */
  OfflinePlayer getAuthor();

  /**
   * Retrieves the date and time when the issue was created.
   *
   * @return a {@link LocalDateTime} representing the creation time of the issue.
   */
  LocalDateTime getDateTime();

  /**
   * Retrieves the reason for the creation of this issue.
   *
   * @return a string representing the reason for the issue.
   */
  String getReason();

  /**
   * Retrieves the employee (support staff) assigned to handle this issue, if any.
   *
   * @return an {@link Optional} containing the {@link OfflinePlayer} assigned to the issue,
   *         or an empty {@link Optional} if no employee has been assigned yet.
   */
  Optional<OfflinePlayer> getEmployee();

  /**
   * Assigns an employee (support staff) to handle this issue.
   *
   * @param employee the {@link OfflinePlayer} assigned to handle the issue.
   */
  void setEmployee(final OfflinePlayer employee);

  /**
   * Retrieves the current status of the issue.
   *
   * @return the current {@link IssueStatus} of the issue.
   */
  IssueStatus getStatus();

  /**
   * Updates the status of the issue.
   *
   * @param status the new {@link IssueStatus} to set for this issue.
   */
  void setStatus(final IssueStatus status);
}
