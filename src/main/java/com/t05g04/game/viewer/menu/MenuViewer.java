package com.t05g04.game.viewer.menu;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public abstract class MenuViewer {
  Screen screen;

  public MenuViewer(Screen screen) {
    this.screen = screen;
  }

  static void drawOptions(TextGraphics textGraphics, int option, String[] options) {
    for (int i = 0; i < options.length; i++) {
      if (i == option) {
        textGraphics.putString(10, 10 + i, options[i], com.googlecode.lanterna.SGR.REVERSE);
      } else {
        textGraphics.putString(10, 10 + i, options[i]);
      }
    }
  }

  public abstract void drawMenu();
}