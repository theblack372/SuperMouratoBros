package com.t05g04.game.model.menu;

import com.t05g04.game.controller.game.Game;
import com.t05g04.game.gui.LanternaGui;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class StartMenu extends Menu {
    public StartMenu(String[] strings, LanternaGui gui) throws IOException, URISyntaxException, FontFormatException {
        super(strings, gui);
    }

    @Override
    public void selectOption() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        switch (selectedOption) {
            case 0:
                //start game
                System.out.println("Game started");
                Game game = new Game();
                game.run();
            case 1:
                //exit
                System.out.println("close");
                close();
            break;
        }
    }

}
