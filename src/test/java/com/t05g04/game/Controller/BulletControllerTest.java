package com.t05g04.game.Controller;
import com.t05g04.game.Game;
import com.t05g04.game.controller.game.BulletController;
import com.t05g04.game.gui.GUI;
import com.t05g04.game.model.game.map.Map;
import com.t05g04.game.model.game.elements.Bullet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

class BulletControllerTest {
    private BulletController bulletController;
    private Map mockMap;
    private Game mockGame;
    private Bullet mockBullet;

    @BeforeEach
    void setUp() {
        mockMap = mock(Map.class);
        bulletController = new BulletController(mockMap);
        mockGame = mock(Game.class);

        // Configuração para uma lista mock de balas
        mockBullet = mock(Bullet.class);
        when(mockMap.getBullets()).thenReturn(java.util.List.of(mockBullet));
    }


    @Test
    void testRunMovesBullets() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        // Arrange
        long currentTime = System.currentTimeMillis();

        // Act
        bulletController.run(mockGame, GUI.ACTION.NONE, currentTime + 200);

        // Assert
        verify(mockMap).BulletMove(mockBullet);
    }


    @Test
    void testRunTriggersHeadshot() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        // Arrange
        when(mockMap.getBullets()).thenReturn(java.util.List.of(mockBullet));
        long currentTime = System.currentTimeMillis();

        // Act
        bulletController.run(mockGame, GUI.ACTION.NONE, currentTime + 200);

        // Assert
        verify(mockMap, times(1)).headshot();
    }

    @Test
    void testNoBullets() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        // Arrange
        when(mockMap.getBullets()).thenReturn(java.util.Collections.emptyList());
        long currentTime = System.currentTimeMillis();

        // Act
        bulletController.run(mockGame, GUI.ACTION.NONE, currentTime + 200);

        // Assert
        verify(mockMap, never()).BulletMove(any());
        verify(mockMap, never()).headshot();
    }
}
