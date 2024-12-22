package com.t05g04.game.viewer.menu;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.model.game.Position;
import com.t05g04.game.model.menu.EndLevelMenu;
import com.t05g04.game.model.menu.MapSelectionMenu;

import java.io.IOException;

import static com.t05g04.game.Application.gui;

public class EndLevelMenuViewer extends MenuViewer {
    EndLevelMenu endLevelMenu;

    public EndLevelMenuViewer(Screen screen, EndLevelMenu endLevelMenu, LanternaGui gui) throws IOException {
        super(screen, gui);
        this.endLevelMenu = endLevelMenu;
    }


    @Override
    public void drawMenu() throws IOException {
        int option = endLevelMenu.getSelectedOption();
        gui.clear();
        drawOptions(option);
        gui.refresh();
    }

    static void drawOptions(int option) throws IOException {
        if (option==0) gui.draw_endLevelMenu_continue(new Position(0,0));
        if (option==1) gui.draw_endLevelMenu_retry(new Position(0,0));
        if (option==2) gui.draw_endLevelMenu_exit(new Position(0,0));
    }

}
