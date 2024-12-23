package com.t05g04.game.model.menu;

import com.t05g04.game.Game;
import com.t05g04.game.controller.menu.MapMenuController;
import com.t05g04.game.gui.GUI;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.viewer.menu.MapSelectionMenuViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MapSelectionMenu extends Menu{
    public MapSelectionMenu(String[] strings, LanternaGui gui)
            throws IOException, URISyntaxException, FontFormatException {
        super(strings, gui);
        menuViewer = new MapSelectionMenuViewer(gui.getScreen(), this, gui);
    menuController = new MapMenuController(this, gui);
    }

    @Override
    public void selectOption()
            throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        switch (selectedOption) {
            case 0:

                Game game = new Game("maps/map1.txt", gui);
                game.run();
                break;
            case 1:

                Game game2 = new Game("maps/map2.txt", gui);
                game2.run();
                break;
            case 2:

                Game game3 = new Game("maps/map3.txt", gui);
                game3.run();
                break;
            case 3:

                Game game4 = new Game("maps/map4.txt", gui);
                game4.run();
                break;
            case 4:
                menuController.action = GUI.ACTION.QUIT;
                break;
        }
    }

    @Override
    public void draw() throws IOException {
        menuViewer.drawMenu();
    }

    @Override
    public void run() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        draw();
        menuController.run(menuViewer);
    }
}
