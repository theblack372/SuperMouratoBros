package com.t05g04.game.viewer.menu;

import com.googlecode.lanterna.screen.Screen;
import com.t05g04.game.gui.LanternaGui;
import java.io.IOException;

public abstract class MenuViewer {
  Screen screen;

  public MenuViewer(Screen screen, LanternaGui gui) throws IOException {
    this.screen = screen;
  }

  public abstract void drawMenu() throws IOException;
}
