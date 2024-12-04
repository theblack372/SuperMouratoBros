package com.t05g04.game;
import com.t05g04.game.controller.Game;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class Application {
    static Game game;

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        Application.start();
    }

    public static void start() throws IOException, URISyntaxException, FontFormatException {
        game = new Game();
        game.run();
    }
}