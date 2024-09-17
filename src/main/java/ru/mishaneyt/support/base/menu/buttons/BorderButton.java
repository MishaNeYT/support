package ru.mishaneyt.support.base.menu.buttons;

import gg.voided.api.menu.button.Button;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import ru.mishaneyt.support.utils.ItemBuilder;

public class BorderButton extends Button {
  private final Material material;

  public BorderButton(final Material material) {
    this.material = material;
  }

  @Override
  public ItemStack getIcon() {
    return new ItemBuilder(this.material)
      .setDisplayName("§r")
      .getItemStack();
  }
}
