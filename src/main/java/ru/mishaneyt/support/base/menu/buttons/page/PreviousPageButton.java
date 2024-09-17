package ru.mishaneyt.support.base.menu.buttons.page;

import gg.voided.api.menu.button.Button;
import gg.voided.api.menu.button.ButtonClick;
import gg.voided.api.menu.pagination.PaginatedMenu;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import org.jetbrains.annotations.NotNull;
import ru.mishaneyt.support.utils.ItemBuilder;

public class PreviousPageButton extends Button {
  private final PaginatedMenu currentMenu;

  public PreviousPageButton(final PaginatedMenu currentMenu) {
    this.currentMenu = currentMenu;
  }

  @Override
  public void onClick(final @NotNull ButtonClick click) {
    if (this.currentMenu.hasPreviousPage())
      this.currentMenu.previous();
  }

  @Override
  public ItemStack getIcon() {
    return new ItemBuilder(Material.SPECTRAL_ARROW)
      .setDisplayName("§6[<] Previous page")
      .getItemStack();
  }
}
