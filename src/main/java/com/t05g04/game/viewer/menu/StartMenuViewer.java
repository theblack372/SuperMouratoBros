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
    for (int i = 0; i < options.length; i++) {
      if (i == option) {
        textGraphics.putString(10, 10 + i, options[i], com.googlecode.lanterna.SGR.REVERSE);
      } else {
        textGraphics.putString(10, 10 + i, options[i]);
      }
    }
  }
}
