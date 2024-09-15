package ru.mishaneyt.support.base;

import org.bukkit.OfflinePlayer;

import ru.mishaneyt.support.api.Issue;
import ru.mishaneyt.support.api.IssueStatus;

import java.time.LocalDateTime;

public final class BaseIssue implements Issue {
  private final OfflinePlayer author;
  private final LocalDateTime dateTime;
  private final String reason;

  private OfflinePlayer employee;
  private IssueStatus status;

  public BaseIssue(final OfflinePlayer author, final String reason) {
    this.author = author;
    this.reason = reason;

    this.dateTime = LocalDateTime.now();
    this.status = IssueStatus.OPENED;
  }

  @Override
  public OfflinePlayer getAuthor() {
    return author;
  }

  @Override
  public String getReason() {
    return reason;
  }

  @Override
  public OfflinePlayer getEmployee() {
    return employee;
  }

  @Override
  public void setEmployee(final OfflinePlayer employee) {
    this.employee = employee;
  }

  @Override
  public IssueStatus getStatus() {
    return status;
  }

  @Override
  public void setStatus(final IssueStatus status) {
    this.status = status;
  }
}
