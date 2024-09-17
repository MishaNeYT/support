package ru.mishaneyt.support.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ItemBuilder {
  private final ItemStack itemStack;

  /**
   * Constructor to create a custom item using an existing ItemStack.
   * @param itemStack The Bukkit ItemStack object to modify.
   */
  public ItemBuilder(final @NotNull ItemStack itemStack) {
    this.itemStack = itemStack;
  }

  /**
   * Constructor to create a custom item using a material and an amount.
   * @param material The material of the item.
   * @param amount The quantity of the item.
   */
  public ItemBuilder(final @NotNull Material material, int amount) {
    this(new ItemStack(material, amount));
  }

  /**
   * Constructor to create a custom item using just a material.
   * Defaults the amount to 1.
   * @param material The material of the item.
   */
  public ItemBuilder(final @NotNull Material material) {
    this(material, 1);
  }

  /**
   * Updates the ItemMeta (metadata) of the item using a lambda function.
   * @param action The action to perform on the ItemMeta.
   * @return The current builder instance for method chaining.
   */
  protected ItemBuilder updateItemMeta(final @NotNull ItemMetaAction action) {
    var itemMeta = itemStack.getItemMeta();
    action.update(itemMeta);
    itemStack.setItemMeta(itemMeta);
    return this;
  }

  /**
   * Sets the type of the item.
   * @param material The material to set.
   * @return The current builder instance for method chaining.
   */
  public ItemBuilder setType(final @NotNull Material material) {
    itemStack.setType(material);
    return this;
  }

  /**
   * Sets the amount of the item.
   * @param amount The amount to set.
   * @return The current builder instance for method chaining.
   */
  public ItemBuilder setAmount(int amount) {
    itemStack.setAmount(amount);
    return this;
  }

  /**
   * Adds an enchantment to the item.
   * @param enchantment The enchantment to add.
   * @param level The level of the enchantment.
   * @return The current builder instance for method chaining.
   */
  public ItemBuilder addEnchantment(final @NotNull Enchantment enchantment, int level) {
    return updateItemMeta(meta -> meta.addEnchant(enchantment, level, true));
  }

  /**
   * Adds an unsafe enchantment to the item, allowing for levels beyond the default maximum.
   * @param enchantment The enchantment to add.
   * @param level The level of the enchantment.
   * @return The current builder instance for method chaining.
   */
  public ItemBuilder addUnsafeEnchantment(final @NotNull Enchantment enchantment, int level) {
    return updateItemMeta(meta -> meta.addEnchant(enchantment, level, true));
  }

  /**
   * Removes an enchantment from the item.
   * @param enchantment The enchantment to remove.
   * @return The current builder instance for method chaining.
   */
  public ItemBuilder removeEnchantment(final @NotNull Enchantment enchantment) {
    return updateItemMeta(meta -> meta.removeEnchant(enchantment));
  }

  /**
   * Sets the display name of the item (deprecated).
   * @param name The new display name.
   * @return The current builder instance for method chaining.
   */
  @Deprecated
  public ItemBuilder setDisplayName(final @NotNull String name) {
    return updateItemMeta(meta -> meta.setDisplayName(name));
  }

  /**
   * Sets the display name of the item using a Component, removing the italic style by default.
   * @param name The new display name as a Component.
   * @return The current builder instance for method chaining.
   */
  public ItemBuilder displayName(final @NotNull Component name) {
    return updateItemMeta(meta -> meta.displayName(name.decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE)));
  }

  /**
   * Sets the lore (description) of the item.
   * @param lores The list of lore components.
   * @return The current builder instance for method chaining.
   */
  public ItemBuilder lore(final @NotNull List<Component> lores) {
    return updateItemMeta(meta -> meta.lore(lores.stream()
      .map(lore -> lore.decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE))
      .toList()));
  }

  /**
   * Sets the lore of the item using an array of components.
   * @param lores Array of lore components.
   * @return The current builder instance for method chaining.
   */
  public ItemBuilder lore(final @NotNull Component... lores) {
    return lore(Arrays.asList(lores));
  }

  /**
   * Sets the lore of the item using a list of strings (deprecated).
   * @param lores The list of lore strings.
   * @return The current builder instance for method chaining.
   */
  @Deprecated
  public ItemBuilder setLore(final @NotNull List<String> lores) {
    return updateItemMeta(meta -> meta.setLore(lores));
  }

  /**
   * Sets the lore of the item using an array of strings.
   * @param lores The array of lore strings.
   * @return The current builder instance for method chaining.
   */
  public ItemBuilder setLore(final @NotNull String... lores) {
    return setLore(Arrays.asList(lores));
  }

  /**
   * Adds a lore line to the item.
   * @param line The lore line to add.
   * @return The current builder instance for method chaining.
   */
  public ItemBuilder addLoreLine(final @NotNull String line) {
    return updateItemMeta(meta -> {
      var lore = new ArrayList<>(meta.getLore() == null ? List.of() : meta.getLore());
      lore.add(line);
      meta.setLore(lore);
    });
  }

  /**
   * Adds multiple lore lines to the item.
   * @param lines The lore lines to add.
   * @return The current builder instance for method chaining.
   */
  public ItemBuilder addLoreLines(final @NotNull String... lines) {
    Arrays.stream(lines).forEach(this::addLoreLine);
    return this;
  }

  /**
   * Removes a specific lore line from the item by component.
   * @param line The lore line to remove.
   * @return The current builder instance for method chaining.
   */
  public ItemBuilder removeLoreLine(final @NotNull Component line) {
    return updateItemMeta(meta -> {
      var lore = meta.lore();
      if (lore != null) {
        lore.remove(line);
        meta.lore(lore);
      }
    });
  }

  /**
   * Removes a specific lore line from the item by string (deprecated).
   * @param line The lore line to remove.
   * @return The current builder instance for method chaining.
   */
  @Deprecated
  public ItemBuilder removeLoreLine(final @NotNull String line) {
    return updateItemMeta(meta -> {
      var lore = meta.getLore();
      if (lore != null) {
        lore.remove(line);
        meta.setLore(lore);
      }
    });
  }

  /**
   * Removes a lore line by its index.
   * @param index The index of the lore line to remove.
   * @return The current builder instance for method chaining.
   */
  public ItemBuilder removeLoreLine(int index) {
    return updateItemMeta(meta -> {
      var lore = meta.lore();
      if (lore != null && index >= 0 && index < lore.size()) {
        lore.remove(index);
        meta.lore(lore);
      }
    });
  }

  /**
   * Adds special item flags to the item.
   * @param itemFlags The item flags to add.
   * @return The current builder instance for method chaining.
   */
  public ItemBuilder addItemFlags(final @NotNull ItemFlag... itemFlags) {
    return updateItemMeta(meta -> meta.addItemFlags(itemFlags));
  }

  /**
   * Sets custom data in the PersistentDataContainer of the item.
   * @param key The key for the data.
   * @param type The data type.
   * @param value The value to store.
   * @param <Z> The data type.
   * @return The current builder instance for method chaining.
   */
  public <Z> ItemBuilder setPersistentData(final @NotNull NamespacedKey key, final @NotNull PersistentDataType<?, Z> type, final @NotNull Z value) {
    return updateItemMeta(meta -> meta.getPersistentDataContainer().set(key, type, value));
  }

  /**
   * Retrieves data from the PersistentDataContainer of the item.
   * @param key The key for the data.
   * @param type The data type.
   * @param <Z> The data type.
   * @return An Optional containing the value if present.
   */
  public <Z> Optional<Z> getPersistentData(final @NotNull NamespacedKey key, final @NotNull PersistentDataType<?, Z> type) {
    var meta = itemStack.getItemMeta();
    if (meta != null)
      return Optional.ofNullable(meta.getPersistentDataContainer().get(key, type));
    return Optional.empty();
  }

  /**
   * Returns the current ItemStack.
   * @return The built ItemStack.
   */
  public ItemStack getItemStack() {
    return itemStack;
  }

  /**
   * Functional interface for performing actions on ItemMeta.
   */
  @FunctionalInterface
  public interface ItemMetaAction {
    /**
     * Applies changes to the ItemMeta.
     * @param itemMeta The item metadata.
     */
    void update(final @NotNull ItemMeta itemMeta);
  }
}
