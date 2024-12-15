package com.t05g04.game.model.menu;

import com.t05g04.game.controller.game.Game;
import com.t05g04.game.controller.menu.MenuController;
import com.t05g04.game.gui.GUI;
import com.t05g04.game.gui.LanternaGui;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class Menu {
    private final String[] options;
    int selectedOption;
    public MenuController menuController = new MenuController(this);
    LanternaGui gui;

    public Menu(String[] options, LanternaGui gui) throws IOException, URISyntaxException, FontFormatException {
        this.options = options;
        this.selectedOption = 0;
        this.gui = gui;
    }

    public String[] getOptions() {
        return options;
    }

    public int getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(int selectedOption) {
        this.selectedOption = selectedOption;
    }

    public void nextOption() {
        selectedOption = (selectedOption + 1) % options.length;
    }

    public void previousOption() {
        selectedOption = (selectedOption - 1 + options.length) % options.length;
    }

    public void selectOption() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        switch (selectedOption) {
            case 0:
                //start game
                Game game = new Game();
                game.run();
                break;
            case 1:
                //exit
                gui.close();
                break;
        }

    }

    public void run() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        System.out.println("menu start running");
        System.out.println("MenuController created");
        while (menuController.action != GUI.ACTION.QUIT) {
            menuController.run();
            System.out.println("menuController running");
        }
    }

    public void close() throws IOException {
        gui.close();
    }

}
