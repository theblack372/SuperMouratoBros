package com.t05g04.game.controller.menu;

import com.t05g04.game.gui.GUI;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.model.menu.Menu;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MenuController {

    private final Menu menu;
    LanternaGui gui;
    public GUI.ACTION action;

    public MenuController(Menu menu) throws IOException, URISyntaxException, FontFormatException {
        this.menu = menu;
        gui = new LanternaGui(32, 18);
        action = gui.getNextAction();
        if (action == null) {
            action = GUI.ACTION.NONE; // Handle null case
        }
    }

    public void run() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        while (action != GUI.ACTION.QUIT) {
            if (action == null) {
                action = GUI.ACTION.NONE; // Handle null case
            }
            switch (action) {
                case UP:
                    moveUp();
                    break;
                case DOWN:
                    moveDown();
                    break;
                case SELECT:
                    selectOption();
                    break;
            }

            gui.refresh();
            action = gui.getNextAction();
        }
    }



    public void moveUp() {
        menu.previousOption();
    }

    public void moveDown() {
        menu.nextOption();
    }

    public void selectOption() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        menu.selectOption();
    }



}
