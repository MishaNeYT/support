package ru.mishaneyt.support.api;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import ru.mishaneyt.support.utils.ItemBuilder;

public enum IssueStatus {

  /**
   * Represents an open support issue.
   */
  OPENED(
    new ItemBuilder(Material.PAPER)
      .setDisplayName("§aOpened")
      .getItemStack()
  ),

  /**
   * Represents a support issue that is currently being worked on.
   */
  BUSY(
    new ItemBuilder(Material.MAP)
      .setDisplayName("§eBusy")
      .getItemStack()
  ),

  /**
   * Represents a support issue that has been closed.
   */
  CLOSED(
    new ItemBuilder(Material.BOOK)
      .setDisplayName("§cClosed")
      .getItemStack()
  );

  private final ItemStack itemStack;

  /**
   * Constructs an IssueStatus with the provided visual representation (ItemStack).
   *
   * @param itemStack the ItemStack associated with this status
   */
  IssueStatus(final ItemStack itemStack) {
    this.itemStack = itemStack;
  }

  /**
   * Retrieves the ItemStack associated with this status.
   *
   * @return the visual representation of the issue status
   */
  public ItemStack getItemStack() {
    return itemStack;
  }
}
