package com.t05g04.game.Controller;

import com.t05g04.game.gui.GUI;
import com.t05g04.game.model.game.elements.Flower;
import com.t05g04.game.model.game.map.Map;
import com.t05g04.game.controller.game.FlowerController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

class FlowerControllerTest {

    private FlowerController flowerController;
    private Map mockMap;
    private Flower mockFlower;

    @BeforeEach
    void setUp() {
        mockMap = Mockito.mock(Map.class);
        mockFlower = Mockito.mock(Flower.class);

        when(mockMap.flowerNo()).thenReturn(1);
        when(mockMap.getFlower(0)).thenReturn(mockFlower);
        flowerController = new FlowerController(mockMap);
    }

    @Test
    void testRunDoesNotAlternateFlowerVisibilityBeforeInterval() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        when(mockFlower.isAppearing()).thenReturn(true);

        long currentTime = System.currentTimeMillis();
        long timeBeforeInterval = currentTime + 2000;

        flowerController.run(null, GUI.ACTION.NONE, timeBeforeInterval);

        verify(mockFlower, never()).setAppearing(anyBoolean());
    }
}