package com.t05g04.game.controller.menu;

import com.t05g04.game.controller.menu.MenuController;
import com.t05g04.game.gui.GUI;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.model.menu.MapSelectionMenu;
import com.t05g04.game.viewer.menu.MenuViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MapMenuController extends MenuController {
    MapSelectionMenu menu;
    public MapMenuController(MapSelectionMenu menu, LanternaGui gui) throws IOException {
        super(menu, gui);
        this.menu = menu;
    }

    @Override
    public void run(MenuViewer menuViewer)
            throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        while (action != GUI.ACTION.QUIT) {
            action = gui.getNextAction();

            if (action == null) {
                action = GUI.ACTION.NONE;
            }
            switch (action) {
                case LEFT:
                    moveUp();
                    menuViewer.drawMenu();
                    break;
                case RIGHT:
                    moveDown();
                    menuViewer.drawMenu();
                    break;
                case SELECT:
                    selectOption();
                    break;
                case UP, DOWN, NONE, QUIT, SHOOT:
                    break;
                default:
                    break;
            }
        }
        menu.close();
    }
}
