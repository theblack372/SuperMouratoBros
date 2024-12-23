package com.t05g04.game.Controller;

import com.t05g04.game.controller.menu.MapMenuController;
import com.t05g04.game.controller.menu.MenuController;
import com.t05g04.game.gui.GUI;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.model.menu.MapSelectionMenu;
import com.t05g04.game.viewer.menu.MenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

public class MapMenuControllerTest {

    private LanternaGui mockGui;
    private MapSelectionMenu mockMenu;
    private MenuViewer mockViewer;
    private MapMenuController controller;

    @BeforeEach
    public void setup() throws IOException {
        // Mock dependencies
        mockGui = mock(LanternaGui.class);
        mockMenu = mock(MapSelectionMenu.class);
        mockViewer = mock(MenuViewer.class);

        // Initialize the controller
        controller = new MapMenuController(mockMenu, mockGui);
    }

    @Test
    public void testHandleLeftAction() throws Exception {
        // Simulate LEFT action followed by QUIT
        when(mockGui.getNextAction()).thenReturn(GUI.ACTION.LEFT, GUI.ACTION.QUIT);

        // Run the controller
        controller.run(mockViewer);

        // Verify behaviors
        verify(mockMenu).previousOption(); // Menu moves up
        verify(mockViewer, times(1)).drawMenu(); // Menu is redrawn
        verify(mockMenu, times(1)).close(); // Menu is closed
    }

    @Test
    public void testHandleRightAction() throws Exception {
        // Simulate RIGHT action followed by QUIT
        when(mockGui.getNextAction()).thenReturn(GUI.ACTION.RIGHT, GUI.ACTION.QUIT);

        // Run the controller
        controller.run(mockViewer);

        // Verify behaviors
        verify(mockMenu).nextOption(); // Menu moves down
        verify(mockViewer, times(1)).drawMenu(); // Menu is redrawn
        verify(mockMenu, times(1)).close(); // Menu is closed
    }

    @Test
    public void testHandleSelectAction() throws Exception {
        // Simulate SELECT action followed by QUIT
        when(mockGui.getNextAction()).thenReturn(GUI.ACTION.SELECT, GUI.ACTION.QUIT);

        // Run the controller
        controller.run(mockViewer);

        // Verify behaviors
        verify(mockMenu).selectOption(); // Option is selected
        verify(mockMenu, times(1)).close(); // Menu is closed
    }

    @Test
    public void testHandleNullAction() throws Exception {
        // Simulate null action followed by QUIT
        when(mockGui.getNextAction()).thenReturn(null, GUI.ACTION.QUIT);

        // Run the controller
        controller.run(mockViewer);

        // Verify no movement or selection occurred
        verify(mockMenu, never()).previousOption();
        verify(mockMenu, never()).nextOption();
        verify(mockMenu, never()).selectOption();
        verify(mockMenu, times(1)).close(); // Menu is closed
    }

    @Test
    public void testHandleQuitAction() throws Exception {
        // Simulate QUIT action
        when(mockGui.getNextAction()).thenReturn(GUI.ACTION.QUIT);

        // Run the controller
        controller.run(mockViewer);

        // Verify menu was closed
        verify(mockMenu, times(1)).close();
        verifyNoMoreInteractions(mockMenu); // No other interactions occurred
    }
}