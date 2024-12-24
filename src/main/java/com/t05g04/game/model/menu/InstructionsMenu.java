package com.t05g04.game.model.menu;

import com.t05g04.game.controller.menu.MapMenuController;
import com.t05g04.game.controller.menu.MenuController;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.viewer.menu.InstructionsMenuViewer;
import com.t05g04.game.viewer.menu.MapSelectionMenuViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class InstructionsMenu extends Menu{
    public InstructionsMenu(String[] strings, LanternaGui gui) throws IOException, URISyntaxException, FontFormatException {
        super(strings, gui);
        menuViewer = new InstructionsMenuViewer(gui.getScreen(), this, gui);
        menuController = new MenuController(this, gui);
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

    @Override
    public void selectOption() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        StartMenu startMenu = new StartMenu(new String[]{"Start", "Instructions", "Exit"}, gui);
        startMenu.run();
    }
}
