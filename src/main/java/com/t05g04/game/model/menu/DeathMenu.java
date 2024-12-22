package com.t05g04.game.model.menu;

import com.t05g04.game.controller.game.Game;
import com.t05g04.game.gui.GUI;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.viewer.menu.DeathMenuViewer;
import com.t05g04.game.viewer.menu.StartMenuViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class DeathMenu extends Menu{
    String  currentMap;

   public DeathMenu(String[] strings, LanternaGui gui, String currentMap) throws IOException, URISyntaxException, FontFormatException {
        super(strings, gui);
        menuViewer = new DeathMenuViewer(gui.getScreen(), this, gui);
        this.currentMap = currentMap;
    }

    @Override
    public void draw() throws IOException {
        menuViewer.drawMenu();
    }

    @Override
    public void selectOption()
            throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        switch (selectedOption) {
            case 0:
                Game game = new Game(currentMap, gui);
                game.run();
                break;
            case 1:
                menuController.action = GUI.ACTION.QUIT;
                break;
        }
    }

    @Override
    public void run()
            throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        draw();
        menuController.run(menuViewer);
    }
}
