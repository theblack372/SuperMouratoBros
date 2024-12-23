package com.t05g04.game.controller.menu;

import com.t05g04.game.Game;
import com.t05g04.game.controller.Controller;
import com.t05g04.game.gui.GUI;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.model.menu.Menu;
import com.t05g04.game.viewer.menu.MenuViewer;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MenuController extends Controller<Menu> {

  private final Menu menu;
  public GUI.ACTION action;
  LanternaGui gui;

  public MenuController(Menu menu, LanternaGui gui1) throws IOException {
      super(menu);
      this.menu = menu;
    gui = gui1;
    action = gui.getNextAction();
    if (action == null) {
      action = GUI.ACTION.NONE; // Handle null case
    }
  }

  public void run(MenuViewer menuViewer)
      throws IOException, URISyntaxException, FontFormatException, InterruptedException {
    while (action != GUI.ACTION.QUIT) {
      action = gui.getNextAction();

      if (action == null) {
        action = GUI.ACTION.NONE; // Handle null case
      }
      switch (action) {
        case UP:
          moveUp();
          menuViewer.drawMenu();
          break;
        case DOWN:
          moveDown();
          menuViewer.drawMenu();
          break;
        case SELECT:
          selectOption();
          break;
      }
    }
    menu.close();
  }

  public void moveUp() {
    menu.previousOption();
  }

  public void moveDown() {
    menu.nextOption();
  }

  public void selectOption()
      throws IOException, URISyntaxException, FontFormatException, InterruptedException {
    menu.selectOption();
  }

  @Override
  public void run(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
    run(getModel().menuViewer);
  }
}
