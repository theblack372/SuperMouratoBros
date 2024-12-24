package com.t05g04.game.Controller;

import com.t05g04.game.Game;
import com.t05g04.game.controller.game.MouratoController;
import com.t05g04.game.gui.GUI;
import com.t05g04.game.model.game.Position;
import com.t05g04.game.model.game.elements.Bullet;
import com.t05g04.game.model.game.elements.Koopa;
import com.t05g04.game.model.game.elements.Mourato;
import com.t05g04.game.model.game.elements.PowerUpBlock;
import com.t05g04.game.model.game.map.Map;
import com.t05g04.game.model.menu.DeathMenu;
import com.t05g04.game.model.menu.EndLevelMenu;
import com.t05g04.game.model.sound.SoundOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

public class MouratoControllerTest {

    private MouratoController mouratoController;
    private Map mockMap;
    private Mourato mockMourato;

    @BeforeEach
    void setUp() {
        mockMap = mock(Map.class);
        mockMourato = mock(Mourato.class);
        when(mockMap.getMourato()).thenReturn(mockMourato);

        mouratoController = new MouratoController(mockMap);
    }

    @Test
    void testMouratoJump_CallsModelCheckAndFall() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        // Given
        when(mockMap.checkAndFall(mockMourato)).thenReturn(false);
        when(mockMourato.isJump_()).thenReturn(false);

        // When
        mouratoController.mouratoJump();

        // Then
        verify(mockMap, times(1)).checkAndFall(mockMourato);
        verify(mockMourato, times(1)).setJump_(true);
    }

    @Test
    void testMouratoJump_AlreadyJumpingDoesNotSetJumpAgain() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        // Given
        when(mockMap.checkAndFall(mockMourato)).thenReturn(false);
        when(mockMourato.isJump_()).thenReturn(true);

        // When
        mouratoController.mouratoJump();

        // Then
        verify(mockMourato, times(0)).setJump_(true);
    }

    @Test
    void testRun_PauseActionDoesNothing() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        mouratoController.run(mock(Game.class), GUI.ACTION.NONE, 0);

        verify(mockMap, never()).moveMourato(any(Position.class));
        verify(mockMap, never()).retrieveCoins(any(Position.class));
        verify(mockMap, never()).goSuperMourato(any(Position.class));
    }

    @Test
    void testRun_LeftActionAtBoundaryDoesNotMove() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        when(mockMap.canObjectMove(any(Position.class))).thenReturn(false);

        mouratoController.run(mock(Game.class), GUI.ACTION.LEFT, 0);

        verify(mockMap, never()).moveMourato(any(Position.class));
    }

    @Test
    void testRun_RightActionAtBoundaryDoesNotIncrementStartX() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        when(mockMap.isMouratoMiddle()).thenReturn(true);
        when(mockMap.canObjectMove(any(Position.class))).thenReturn(false);

        mouratoController.run(mock(Game.class), GUI.ACTION.RIGHT, 0);

        verify(mockMap, never()).incrementStartX_();
    }
}