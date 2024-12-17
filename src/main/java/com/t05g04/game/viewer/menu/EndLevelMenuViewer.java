package com.t05g04.game.viewer.menu;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.t05g04.game.model.menu.EndLevelMenu;
import com.t05g04.game.model.menu.MapSelectionMenu;

public class EndLevelMenuViewer extends MenuViewer {
    EndLevelMenu endLevelMenu;

    public EndLevelMenuViewer(Screen screen, EndLevelMenu endLevelMenu) {
        super(screen);
        this.endLevelMenu = endLevelMenu;
    }


    @Override
    public void drawMenu() {
        TextGraphics textGraphics = screen.newTextGraphics();
        int option = endLevelMenu.getSelectedOption();
        String[] options = endLevelMenu.getOptions();
        screen.clear();
        textGraphics.putString(1, 5, "WELL DONE! WANT TO PLAY AGAIN?", com.googlecode.lanterna.SGR.BOLD);
        drawOptions(textGraphics, option, options);
    }

}
