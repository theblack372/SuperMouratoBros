package com.t05g04.game.gui;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.t05g04.game.model.game.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.mockito.Mockito.*;

class LanternaGuiTest {

    private LanternaGui lanternaGui;

    @Mock
    private Screen mockScreen;

    @Mock
    private TextGraphics mockTextGraphics;

    @Mock
    private BufferedImage mockImage;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);

        when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);

        lanternaGui = new LanternaGui(mockScreen);
    }

    @Test
    void testClear() {
        lanternaGui.clear();

        verify(mockScreen).clear();
    }

    @Test
    void testRefresh() throws IOException {
        lanternaGui.refresh();

        verify(mockScreen).refresh();
    }

    @Test
    void testClose() throws IOException {
        lanternaGui.close();

        verify(mockScreen).close();
    }

    @Test
    void testDrawPNG() throws IOException {
        Position position = new Position(0, 0);

        lanternaGui.drawPNG(position, mockImage);

    }

    @Test
    void testDrawWall() throws IOException {
        Position position = new Position(5, 5);

        lanternaGui.draw_Wall(position);

        verify(mockScreen).newTextGraphics();
    }

    @Test
    void testDisplayMessage() throws IOException {
        lanternaGui.displayMessage(mockScreen, "Hello, World!", 10, 5);

        verify(mockTextGraphics).setForegroundColor(any());
        verify(mockTextGraphics).putString(10, 5, "Hello, World!");
    }
}

