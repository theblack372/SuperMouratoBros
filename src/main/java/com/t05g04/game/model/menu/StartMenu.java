package com.t05g04.game.model.menu;

import com.t05g04.game.gui.GUI;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.viewer.menu.StartMenuViewer;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class StartMenu extends Menu {
  public StartMenu(String[] strings, LanternaGui gui)
      throws IOException, URISyntaxException, FontFormatException {
    super(strings, gui);
    menuViewer = new StartMenuViewer(gui.getScreen(), this, gui);
  }

  @Override
  public void selectOption()
      throws IOException, URISyntaxException, FontFormatException, InterruptedException {
    switch (selectedOption) {
      case 0:
        MapSelectionMenu mapSelectionMenu =
            new MapSelectionMenu(new String[] {"Map 1", "Map 2", "Map 3", "Map 4", "Exit"}, gui);
        mapSelectionMenu.run();
        break;
      case 1:
        InstructionsMenu instructionsMenu = new InstructionsMenu(new String[] {"Back"}, gui);
        instructionsMenu.run();
        break;
      case 2:
        menuController.action = GUI.ACTION.QUIT;
        break;
    }
  }

  @Override
  public void draw() throws IOException {
    menuViewer.drawMenu();
  }

  @Override
  public void run()
      throws IOException, URISyntaxException, FontFormatException, InterruptedException {
    draw();
    menuController.run(menuViewer);
  }
}
