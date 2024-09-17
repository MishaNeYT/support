package ru.mishaneyt.support.base;

import gg.voided.api.menu.button.Button;
import org.bukkit.OfflinePlayer;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import ru.mishaneyt.support.api.Issue;
import ru.mishaneyt.support.api.IssueStatus;

import java.time.LocalDateTime;
import java.util.Optional;

public final class BaseIssue extends Button implements Issue {
  private final OfflinePlayer author;
  private final LocalDateTime dateTime;
  private final String reason;

  private OfflinePlayer employee;
  private IssueStatus status;

  /**
   * Constructor for creating a new support issue.
   *
   * @param author the player who created the issue.
   * @param reason the reason for creating the issue.
   */
  public BaseIssue(final OfflinePlayer author, final String reason) {
    this.author = author;
    this.reason = reason;

    this.employee = null;
    this.dateTime = LocalDateTime.now();

    this.setStatus(IssueStatus.OPENED);
  }

  @Override
  public ItemStack getIcon() {
    return this.status.getItemStack();
  }

  @Override
  public OfflinePlayer getAuthor() {
    return author;
  }

  @Override
  public LocalDateTime getDateTime() {
    return dateTime;
  }

  @Override
  public String getReason() {
    return reason;
  }

  @Override
  public Optional<OfflinePlayer> getEmployee() {
    return Optional.ofNullable(employee);
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
  public void setStatus(final @NotNull IssueStatus status) {
    this.status = status;
    switch (status) {
      case OPENED -> {
        // A
      }
      case BUSY -> {
        // B
      }
      case CLOSED -> {
        // C
      }
    }
  }
}
