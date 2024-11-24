package com.t05g04.game;
import com.t05g04.game.controller.Game;

import java.io.IOException;


public class Application {
    static Game game;

    public static void main(String[] args) throws IOException {
        Application.start();
    }

    public static void start() throws IOException {
        game = new Game();
        game.run();
    }
}