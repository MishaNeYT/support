package ru.mishaneyt.support.base.menu;

import gg.voided.api.menu.Menu;
import gg.voided.api.menu.MenuSize;
import gg.voided.api.menu.annotation.Async;
import gg.voided.api.menu.annotation.AutoUpdate;
import gg.voided.api.menu.button.Button;
import gg.voided.api.menu.layer.impl.BackgroundLayer;
import gg.voided.api.menu.layer.impl.ForegroundLayer;
import gg.voided.api.menu.pagination.PaginatedMenu;
import gg.voided.api.menu.pagination.PaginationSlot;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import ru.mishaneyt.support.SupportPlugin;
import ru.mishaneyt.support.base.menu.buttons.BorderButton;
import ru.mishaneyt.support.base.menu.buttons.page.NextPageButton;
import ru.mishaneyt.support.base.menu.buttons.page.PreviousPageButton;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Async
@AutoUpdate(value = 20)
public class BaseIssuesPaginatedMenu extends PaginatedMenu {

  /**
   * Constructor for the paginated menu displaying support issues.
   *
   * @param player the player for whom the menu is being created.
   */
  public BaseIssuesPaginatedMenu(final @NotNull Player player) {
    super("Issues", MenuSize.FIVE, player);
  }

  @Override
  public void setup(final @NotNull BackgroundLayer background, final @NotNull ForegroundLayer foreground) {
    apply(new BaseIssuesPaginatedTemplate());
    foreground.center(new PaginationSlot(this));
    background.set(0, getRows() - 3, new PreviousPageButton(this));
    background.set(Menu.COLUMNS - 1, getRows() - 3, new NextPageButton(this));
  }

  /**
   * Retrieves the entries (issues) to be displayed in the paginated menu.
   * It converts each issue into a Button to be used in the menu.
   *
   * @return a list of Buttons representing the issues.
   */
  @Override
  public List<Button> getEntries() {
    return SupportPlugin.getPlugin().getIssueManager().getIssues().values().stream()
      .map(issue -> (Button) issue)
      .toList();
  }
}
