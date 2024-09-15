package ru.mishaneyt.support.api;

import org.bukkit.OfflinePlayer;

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
  String getReason();

  /**
   *
   * @return
   */
  OfflinePlayer getEmployee();

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
