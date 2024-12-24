package com.t05g04.game.model.menu;

import com.googlecode.lanterna.screen.Screen;
import com.t05g04.game.Game;
import com.t05g04.game.controller.menu.MapMenuController;
import com.t05g04.game.gui.GUI;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.viewer.menu.MapSelectionMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

public class MapSelectionMenuTest {

    private LanternaGui mockGui;
    private MapSelectionMenuViewer mockMenuViewer;
    private MapMenuController mockMenuController;
    @SuppressWarnings("MockNotUsedInProduction")
    private Game mockGame = mock(Game.class);
    private MapSelectionMenu mapSelectionMenu;

    @BeforeEach
    public void setup() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        mockGui = mock(LanternaGui.class);
        mockMenuViewer = mock(MapSelectionMenuViewer.class);
        mockMenuController = mock(MapMenuController.class);

        doNothing().when(mockGame).run();

        Screen mockScreen = mock(Screen.class);
        when(mockGui.getScreen()).thenReturn(mockScreen);

        String[] options = {"Map 1", "Map 2", "Map 3", "Map 4", "Quit"};
        mapSelectionMenu = new MapSelectionMenu(options, mockGui);

        mapSelectionMenu.menuViewer = mockMenuViewer;
        mapSelectionMenu.menuController = mockMenuController;
    }

    @Test
    public void testSelectOption_Quit() throws Exception {
        mapSelectionMenu.setSelectedOption(4);

        mapSelectionMenu.selectOption();

        verify(mockMenuController, times(1)).action = GUI.ACTION.QUIT;
    }

    @Test
    public void testDraw() throws IOException {
        mapSelectionMenu.draw();

        verify(mockMenuViewer, times(1)).drawMenu();
    }

    @Test
    public void testRun() throws Exception {
        mapSelectionMenu.run();

        verify(mockMenuViewer, times(1)).drawMenu();
        verify(mockMenuController, times(1)).run(mockMenuViewer);
    }
}