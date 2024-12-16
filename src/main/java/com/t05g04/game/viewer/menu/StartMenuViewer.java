package com.t05g04.game.viewer.menu;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.t05g04.game.model.menu.StartMenu;

public class StartMenuViewer extends MenuViewer {
  StartMenu startMenu;

  public StartMenuViewer(Screen screen, StartMenu startMenu) {
    super(screen);
    this.startMenu = startMenu;
  }

  @Override
  public void drawMenu() {
    TextGraphics textGraphics = screen.newTextGraphics();
    int option = startMenu.getSelectedOption();
    String[] options = startMenu.getOptions();
    screen.clear();
    textGraphics.putString(5, 5, "SUPER MOURATO BROS", com.googlecode.lanterna.SGR.BOLD, com.googlecode.lanterna.SGR.UNDERLINE);
    drawOptions(textGraphics, option, options);
  }
}
