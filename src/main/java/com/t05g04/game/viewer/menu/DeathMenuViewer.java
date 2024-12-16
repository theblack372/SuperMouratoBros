package com.t05g04.game.viewer.menu;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.t05g04.game.model.menu.DeathMenu;

public class DeathMenuViewer extends MenuViewer {

  DeathMenu deathMenu;

  public DeathMenuViewer(Screen screen, DeathMenu deathmenu) {
    super(screen);
    this.deathMenu = deathmenu;
  }

  @Override
  public void drawMenu() {
    TextGraphics textGraphics = screen.newTextGraphics();
    int option = deathMenu.getSelectedOption();
    String[] options = deathMenu.getOptions();
    screen.clear();
    textGraphics.putString(9, 2, "YOU DIED!", com.googlecode.lanterna.SGR.BOLD, com.googlecode.lanterna.SGR.UNDERLINE);
    textGraphics.putString(5, 5, "Do you want to try again?");
    drawOptions(textGraphics, option, options);
  }

}
