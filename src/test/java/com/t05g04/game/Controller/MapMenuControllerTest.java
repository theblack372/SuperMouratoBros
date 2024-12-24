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
        mockGui = mock(LanternaGui.class);
        mockMenu = mock(MapSelectionMenu.class);
        mockViewer = mock(MenuViewer.class);

        controller = new MapMenuController(mockMenu, mockGui);
    }

    @Test
    public void testHandleLeftAction() throws Exception {
        when(mockGui.getNextAction()).thenReturn(GUI.ACTION.LEFT, GUI.ACTION.QUIT);

        controller.run(mockViewer);


        verify(mockMenu).previousOption();
        verify(mockViewer, times(1)).drawMenu();
        verify(mockMenu, times(1)).close();
    }

    @Test
    public void testHandleRightAction() throws Exception {
        when(mockGui.getNextAction()).thenReturn(GUI.ACTION.RIGHT, GUI.ACTION.QUIT);

        controller.run(mockViewer);

        verify(mockMenu).nextOption();
        verify(mockViewer, times(1)).drawMenu();
        verify(mockMenu, times(1)).close();
    }

    @Test
    public void testHandleSelectAction() throws Exception {
        when(mockGui.getNextAction()).thenReturn(GUI.ACTION.SELECT, GUI.ACTION.QUIT);

        controller.run(mockViewer);

        verify(mockMenu).selectOption();
        verify(mockMenu, times(1)).close();
    }

    @Test
    public void testHandleNullAction() throws Exception {
        when(mockGui.getNextAction()).thenReturn(null, GUI.ACTION.QUIT);


        controller.run(mockViewer);


        verify(mockMenu, never()).previousOption();
        verify(mockMenu, never()).nextOption();
        verify(mockMenu, never()).selectOption();
        verify(mockMenu, times(1)).close();
    }

    @Test
    public void testHandleQuitAction() throws Exception {
        when(mockGui.getNextAction()).thenReturn(GUI.ACTION.QUIT);

        controller.run(mockViewer);


        verify(mockMenu, times(1)).close();
        verifyNoMoreInteractions(mockMenu);
    }
}