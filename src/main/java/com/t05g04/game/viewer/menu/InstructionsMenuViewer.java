package com.t05g04.game.viewer.menu;

import com.googlecode.lanterna.screen.Screen;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.model.game.Position;
import com.t05g04.game.model.menu.DeathMenu;
import com.t05g04.game.model.menu.InstructionsMenu;

import java.io.IOException;

import static com.t05g04.game.Application.gui;

public class InstructionsMenuViewer extends MenuViewer {

    InstructionsMenu instructionsMenu;

    public InstructionsMenuViewer(Screen screen, InstructionsMenu instructionsMenu, LanternaGui gui) throws IOException {
            super(screen, gui);
            this.instructionsMenu = instructionsMenu;
    }

    @Override
    public void drawMenu() throws IOException {
        gui.clear();
        gui.draw_instructionsMenu(new Position(0,0));
        gui.refresh();
    }
}
