package com.t05g04.game;
import com.t05g04.game.controller.game.Game;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.model.menu.Menu;
import com.t05g04.game.model.menu.StartMenu;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class Application {
    static Game game;
    static Menu menu;

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException, InterruptedException {

        Application.startMenu();

    }

    public static void start() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        game = new Game();
        game.run();
    }

    public static void startMenu() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        menu = new StartMenu(new String[]{"Start", "Exit"}, new LanternaGui(32, 18));
        System.out.println("Menu created");
        menu.run();
        System.out.println("Menu run");
    }
}