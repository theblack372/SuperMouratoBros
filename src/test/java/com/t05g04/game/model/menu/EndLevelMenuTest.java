package com.t05g04.game.model.menu;
import com.t05g04.game.Game;
import com.t05g04.game.controller.menu.MenuController;
import com.t05g04.game.gui.GUI;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.viewer.menu.EndLevelMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

public class EndLevelMenuTest {

    private LanternaGui mockGui;
    private EndLevelMenuViewer mockMenuViewer;
    private EndLevelMenu endLevelMenu;

    @BeforeEach
    public void setup() throws IOException, URISyntaxException, FontFormatException {
        // Mock dependencies
        mockGui = mock(LanternaGui.class);
        mockMenuViewer = mock(EndLevelMenuViewer.class);

        // Initialize EndLevelMenu
        String[] options = {"Map Selection", "Restart", "Quit"};
        String currentMap = "testMap";
        endLevelMenu = new EndLevelMenu(options, mockGui, currentMap);

        // Replace default menu viewer with a mock
        endLevelMenu.menuViewer = mockMenuViewer;
    }

    @Test
    public void testDrawMenu() throws IOException {
        // Call draw
        endLevelMenu.draw();

        // Verify the menuViewer's drawMenu method is called
        verify(mockMenuViewer, times(1)).drawMenu();
    }

    @Test
    public void testSelectOption_Quit() throws Exception {
        // Set selected option to "Quit"
        endLevelMenu.setSelectedOption(2);

        // Call selectOption
        endLevelMenu.selectOption();

        // Verify GUI is closed
        verify(mockGui, times(1)).close();
    }

    @Test
    public void testRun() throws Exception {
        // Mock the menuController's run method
        MenuController mockMenuController = mock(MenuController.class);
        endLevelMenu.menuController = mockMenuController;

        // Call run
        endLevelMenu.run();

        // Verify that draw is called
        verify(mockMenuViewer, times(1)).drawMenu();

        // Verify menuController's run is called
        verify(mockMenuController, times(1)).run(mockMenuViewer);
    }
}

