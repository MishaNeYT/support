package ru.mishaneyt.support.base.menu;

import gg.voided.api.menu.layer.impl.BackgroundLayer;
import gg.voided.api.menu.layer.impl.ForegroundLayer;
import gg.voided.api.menu.template.Template;

import org.bukkit.Material;

import ru.mishaneyt.support.base.menu.buttons.BorderButton;
import org.jetbrains.annotations.NotNull;

public class BaseIssuesPaginatedTemplate implements Template {

  @Override
  public void apply(final @NotNull BackgroundLayer background, final @NotNull ForegroundLayer foreground) {
    background.border(new BorderButton(Material.RED_STAINED_GLASS_PANE));
    // Возможно для чего то пригодится...
  }
}
