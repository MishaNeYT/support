package ru.mishaneyt.support.base.menu;

import gg.voided.api.menu.layer.impl.BackgroundLayer;
import gg.voided.api.menu.layer.impl.ForegroundLayer;
import gg.voided.api.menu.template.Template;

import org.bukkit.Material;

import ru.mishaneyt.support.base.menu.buttons.BorderButton;
import org.jetbrains.annotations.NotNull;

public class BaseIssuesPaginatedTemplate implements Template {

  /**
   * Applies the template to the given background and foreground layers.
   * This method sets up a border around the menu using red stained-glass panes.
   * Additional customization can be added to the foreground layer if needed.
   *
   * @param background the background layer of the menu.
   * @param foreground the foreground layer of the menu.
   */
  @Override
  public void apply(final @NotNull BackgroundLayer background, final @NotNull ForegroundLayer foreground) {
    background.border(new BorderButton(Material.RED_STAINED_GLASS_PANE));
    // This can be extended later if more elements need to be added to the foreground.
  }
}
