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
        // Mock dependencies
        mockGui = mock(LanternaGui.class);
        mockMenuViewer = mock(MapSelectionMenuViewer.class);
        mockMenuController = mock(MapMenuController.class);

        // Mock the Game class's run method
        doNothing().when(mockGame).run();  // Prevent the actual run method from executing

        // Mock getScreen() to return a valid screen object
        Screen mockScreen = mock(Screen.class);
        when(mockGui.getScreen()).thenReturn(mockScreen);

        // Mock the MapSelectionMenu constructor to use the mocked dependencies
        String[] options = {"Map 1", "Map 2", "Map 3", "Map 4", "Quit"};
        mapSelectionMenu = new MapSelectionMenu(options, mockGui);

        // Inject mocks into the mapSelectionMenu
        mapSelectionMenu.menuViewer = mockMenuViewer;
        mapSelectionMenu.menuController = mockMenuController;
    }

    @Test
    public void testSelectOption_Quit() throws Exception {
        // Set selected option to Quit (index 4)
        mapSelectionMenu.setSelectedOption(4);

        // Call selectOption
        mapSelectionMenu.selectOption();

        // Verify that the action is set to quit
        verify(mockMenuController, times(1)).action = GUI.ACTION.QUIT;
    }

    @Test
    public void testDraw() throws IOException {
        // Call draw
        mapSelectionMenu.draw();

        // Verify that the menuViewer's drawMenu method is called
        verify(mockMenuViewer, times(1)).drawMenu();
    }

    @Test
    public void testRun() throws Exception {
        // Call run
        mapSelectionMenu.run();

        // Verify that draw and menuController's run methods are called
        verify(mockMenuViewer, times(1)).drawMenu();
        verify(mockMenuController, times(1)).run(mockMenuViewer);
    }
}