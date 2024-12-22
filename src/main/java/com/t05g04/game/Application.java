package com.t05g04.game;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.model.menu.Menu;
import com.t05g04.game.model.menu.StartMenu;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class Application {
    static Menu menu;
    public static LanternaGui gui = null;

    public Application() throws IOException, URISyntaxException, FontFormatException {
        gui= new LanternaGui(32*16,18*16);
    }

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        new Application();
        startMenu();
    }

    public static void startMenu() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        menu = new StartMenu(new String[]{"Start", "Exit"}, gui);
        menu.run();
    }
}