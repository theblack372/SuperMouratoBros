package com.t05g04.game;
import com.t05g04.game.controller.Game;

import java.io.IOException;


public class Application {
    public static void main(String[] args) throws IOException {
        Game game= new Game();
        game.run();
    }
}