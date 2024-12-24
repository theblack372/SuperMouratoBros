package com.t05g04.game.model.menu;

import com.t05g04.game.controller.menu.MenuController;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.viewer.menu.DeathMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

public class DeathMenuTest {

    private LanternaGui mockGui;
    private MenuController mockMenuController;
    private DeathMenuViewer mockMenuViewer;
    private DeathMenu deathMenu;

    @BeforeEach
    public void setup() throws IOException, URISyntaxException, FontFormatException {
        mockGui = mock(LanternaGui.class);
        mockMenuController = mock(MenuController.class);
        mockMenuViewer = mock(DeathMenuViewer.class);

        String[] options = {"Restart", "Quit"};
        String currentMap = "testMap";
        deathMenu = new DeathMenu(options, mockGui, currentMap);

        deathMenu.menuController = mockMenuController;
        deathMenu.menuViewer = mockMenuViewer;
    }

    @Test
    public void testDrawMenu() throws IOException {
        deathMenu.draw();

        verify(mockMenuViewer, times(1)).drawMenu();
    }
}
