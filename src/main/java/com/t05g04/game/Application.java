package com.t05g04.game;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.model.menu.Menu;
import com.t05g04.game.model.menu.StartMenu;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class Application {
    static Menu menu;

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        Application.startMenu();
    }

    public static void startMenu() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        menu = new StartMenu(new String[]{"Start", "Exit"}, new LanternaGui(32, 18));
        menu.run();
    }
}