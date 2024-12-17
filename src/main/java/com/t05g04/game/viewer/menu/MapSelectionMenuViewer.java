package com.t05g04.game.viewer.menu;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.t05g04.game.model.menu.MapSelectionMenu;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MapSelectionMenuViewer extends MenuViewer {
    MapSelectionMenu mapSelectionMenu;

    public MapSelectionMenuViewer(Screen screen, MapSelectionMenu mapSelectionMenu) throws IOException, URISyntaxException, FontFormatException {
        super(screen);
        this.mapSelectionMenu = mapSelectionMenu;
    }

    @Override
    public void drawMenu() {
        TextGraphics textGraphics = screen.newTextGraphics();
        int option = mapSelectionMenu.getSelectedOption();
        String[] options = mapSelectionMenu.getOptions();
        screen.clear();
        textGraphics.putString(1, 5, "WHAT LEVEL DO YOU WANT TO PLAY?", com.googlecode.lanterna.SGR.BOLD);
        drawOptions(textGraphics, option, options);
    }
}
