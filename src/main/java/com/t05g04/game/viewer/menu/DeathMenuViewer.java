package com.t05g04.game.viewer.menu;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.model.game.Position;
import com.t05g04.game.model.menu.DeathMenu;

import java.io.IOException;

import static com.t05g04.game.Application.gui;

public class DeathMenuViewer extends MenuViewer {

  DeathMenu deathMenu;

  public DeathMenuViewer(Screen screen, DeathMenu deathMenu, LanternaGui gui) throws IOException {
    super(screen, gui);
    this.deathMenu = deathMenu;
  }

  @Override
  public void drawMenu() throws IOException {
    int option = deathMenu.getSelectedOption();
    gui.clear();
    drawOptions(option);
    gui.refresh();
  }

  static void drawOptions(int option) throws IOException {
    if (option==0) gui.draw_deathMenu_retry(new Position(0,0));
    if (option==1) gui.draw_deathMenu_exit(new Position(0,0));
  }

}
