package com.t05g04.game.viewer.menu;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.model.game.Position;
import com.t05g04.game.model.menu.StartMenu;

import java.io.IOException;

import static com.t05g04.game.Application.gui;

public class StartMenuViewer extends MenuViewer {
  StartMenu startMenu;

  public StartMenuViewer(Screen screen, StartMenu startMenu, LanternaGui gui) throws IOException {
    super(screen, gui);
    this.startMenu = startMenu;
  }

  @Override
  public void drawMenu() throws IOException {
    int option = startMenu.getSelectedOption();
    gui.clear();
    drawOptions(option);
    gui.refresh();
  }

  static void drawOptions(int option) throws IOException {
    if (option==0) gui.draw_startMenu_start(new Position(0,0));
    if (option==1) gui.draw_startMenu_instructions(new Position(0,0));
    if (option==2) gui.draw_startMenu_exit(new Position(0,0));
  }
}
