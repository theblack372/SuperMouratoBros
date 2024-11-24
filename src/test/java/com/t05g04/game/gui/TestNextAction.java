package com.t05g04.game.gui;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestNextAction {
    Screen mockScreen;
    LanternaGui gui;
    //screen mocked
    @BeforeEach
    void setUp() {
        mockScreen = Mockito.mock(Screen.class);
    }

    @Test
    void testGetNextActionQuit() throws IOException {

        // "q" pressionado
        KeyStroke mockKeyStroke = new KeyStroke('q', false, false);
        when(mockScreen.pollInput()).thenReturn(mockKeyStroke);

        gui = new LanternaGui(mockScreen);

        assertEquals(GUI.ACTION.QUIT, gui.getNextAction());

    }

    @Test
    void testGetNextActionArrowKeys() throws IOException {
        // todas as setas vao ser pressionadas

        when(mockScreen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowUp, false, false));
        gui = new LanternaGui(mockScreen);
        assertEquals(GUI.ACTION.UP, gui.getNextAction());

        when(mockScreen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowDown, false, false));
        assertEquals(GUI.ACTION.DOWN, gui.getNextAction());

        when(mockScreen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowLeft, false, false));
        assertEquals(GUI.ACTION.LEFT, gui.getNextAction());

        when(mockScreen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowRight, false, false));
        assertEquals(GUI.ACTION.RIGHT, gui.getNextAction());

    }
    @Test
    void testGetNextActionNullInput() throws IOException {

        // nao houveram inputs
        when(mockScreen.pollInput()).thenReturn(null);

        // Create the object with the mocked screen
        gui = new LanternaGui(mockScreen);

        // Assert that the method returns null
        assertNull(gui.getNextAction());
    }


}