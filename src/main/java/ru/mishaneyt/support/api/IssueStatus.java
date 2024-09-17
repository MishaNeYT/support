package ru.mishaneyt.support.api;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import ru.mishaneyt.support.utils.ItemBuilder;

public enum IssueStatus {

  /**
   *
   */
  OPENED(
    new ItemBuilder(Material.PAPER)
      .setDisplayName("§aOpened")
      .getItemStack()
  ),

  /**
   *
   */
  BUSY(
    new ItemBuilder(Material.MAP)
      .setDisplayName("§eBusy")
      .getItemStack()
  ),

  /**
   *
   */
  CLOSED(
    new ItemBuilder(Material.BOOK)
      .setDisplayName("§cClosed")
      .getItemStack()
  );

  private final ItemStack itemStack;

  IssueStatus(final ItemStack itemStack) {
    this.itemStack = itemStack;
  }

  public ItemStack getItemStack() {
    return itemStack;
  }
}
