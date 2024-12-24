package com.t05g04.game.viewer.menu;

import com.googlecode.lanterna.screen.Screen;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.model.game.Position;
import com.t05g04.game.model.menu.MapSelectionMenu;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static com.t05g04.game.Application.gui;

public class MapSelectionMenuViewer extends MenuViewer {
    MapSelectionMenu mapSelectionMenu;

    public MapSelectionMenuViewer(Screen screen, MapSelectionMenu mapSelectionMenu, LanternaGui gui) throws IOException, URISyntaxException, FontFormatException {
        super(screen, gui);
        this.mapSelectionMenu = mapSelectionMenu;
    }

    @Override
    public void drawMenu() throws IOException {
        int option = mapSelectionMenu.getSelectedOption();
        gui.clear();
        drawOptions(option);
        gui.refresh();
    }

    static void drawOptions(int option) throws IOException {
        if (option==0) gui.draw_mapSelect_1(new Position(0,0));
        if (option==1) gui.draw_mapSelect_2(new Position(0,0));
        if (option==2) gui.draw_mapSelect_3(new Position(0,0));
        if (option==3) gui.draw_mapSelect_4(new Position(0,0));
        if (option==4) gui.draw_mapSelect_exit(new Position(0,0));
    }
}
