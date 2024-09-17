package ru.mishaneyt.support.api;

import org.bukkit.OfflinePlayer;

import java.time.LocalDateTime;
import java.util.Optional;

public interface Issue {

  /**
   *
   * @return
   */
  OfflinePlayer getAuthor();

  /**
   *
   * @return
   */
  LocalDateTime getDateTime();

  /**
   *
   * @return
   */
  String getReason();

  /**
   *
   * @return
   */
  Optional<OfflinePlayer> getEmployee();

  /**
   *
   * @param employee
   */
  void setEmployee(final OfflinePlayer employee);

  /**
   *
   * @return
   */
  IssueStatus getStatus();

  /**
   *
   * @param status
   */
  void setStatus(final IssueStatus status);
}
