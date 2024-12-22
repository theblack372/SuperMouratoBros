package com.t05g04.game.model.menu;

import com.t05g04.game.controller.menu.MenuController;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.viewer.menu.MenuViewer;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class Menu {
    private final String[] options;
    public MenuController menuController;
    public MenuViewer menuViewer;
    int selectedOption;
    LanternaGui gui;

    public Menu(String[] options, LanternaGui gui) throws IOException, URISyntaxException, FontFormatException {
        this.options = options;
        this.selectedOption = 0;
        this.gui = gui;
        menuController = new MenuController(this, gui);
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
    }

    public void run() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
    }

    public void close() throws IOException {
        gui.close();
    }

    public abstract void draw() throws IOException;

}
