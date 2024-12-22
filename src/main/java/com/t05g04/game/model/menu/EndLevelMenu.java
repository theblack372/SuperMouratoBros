package com.t05g04.game.model.menu;

import com.t05g04.game.controller.game.Game;
import com.t05g04.game.gui.GUI;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.viewer.menu.EndLevelMenuViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class EndLevelMenu extends Menu {
    String currentMap;
    public EndLevelMenu(String[] string, LanternaGui gui, String currentMap) throws IOException, URISyntaxException, FontFormatException {
        super(string, gui);
        menuViewer = new EndLevelMenuViewer(gui.getScreen(), this, gui);
        this.currentMap = currentMap;
    }

    @Override
    public void selectOption()
            throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        switch (selectedOption) {
            case 0:
                gui.close();
                MapSelectionMenu mapSelectionMenu = new MapSelectionMenu(
                        new String[]{"Map 1", "Map 2", "Map 3", "Map 4", "Exit"}, gui);
                mapSelectionMenu.run();
                break;
            case 1:
                gui.close();
                Game game = new Game(currentMap, gui);
                game.run();
                break;

            case 2:
                menuController.action = GUI.ACTION.QUIT;
                break;

        }
    }

    @Override
    public void draw() throws IOException {menuViewer.drawMenu();}

    @Override
    public void run() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        draw();
        menuController.run(menuViewer);
    }

}
