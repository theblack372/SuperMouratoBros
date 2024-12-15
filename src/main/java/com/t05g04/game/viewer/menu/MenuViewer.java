package com.t05g04.game.viewer.menu;

import com.googlecode.lanterna.screen.Screen;

public abstract class MenuViewer {
  Screen screen;

  public MenuViewer(Screen screen) {
    this.screen = screen;
  }

  public abstract void drawMenu();
}
