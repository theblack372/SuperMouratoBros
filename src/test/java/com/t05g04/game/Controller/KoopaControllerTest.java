package com.t05g04.game.Controller;

import com.t05g04.game.Game;
import com.t05g04.game.controller.game.KoopaController;
import com.t05g04.game.gui.GUI;
import com.t05g04.game.model.game.elements.Koopa;
import com.t05g04.game.model.game.map.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class KoopaControllerTest {

    private KoopaController koopaController;
    private Map mockMap;
    private Game mockGame;
    private Koopa mockKoopa1;
    private Koopa mockKoopa2;


    @BeforeEach
    void setUp() {
        mockMap = mock(Map.class);
        mockGame = mock(Game.class);
        mockKoopa1 = mock(Koopa.class);
        mockKoopa2 = mock(Koopa.class);
        ArrayList<Koopa> koopas = new ArrayList<>(Arrays.asList(mockKoopa1, mockKoopa2));
        when(mockMap.getKoopas()).thenReturn(koopas);
        koopaController = new KoopaController(mockMap);
    }


    @Test
    void testRun_WithinInterval_NoMove() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        long initialTime = 999;

        // Run the controller with a time less than KOOPA_MOVE_INTERVAL
        koopaController.run(mockGame, GUI.ACTION.NONE, initialTime);

        // Verify that KoopaMove was never called
        verify(mockMap, never()).KoopaMove(any());
    }

    @Test
    void testRun_ExceedsInterval_MoveKoopas() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        koopaController.lastMove = 1000;
        long currentTime = 2001; // Exceeds KOOPA_MOVE_INTERVAL (1000ms)

        koopaController.run(mockGame, GUI.ACTION.NONE, currentTime);

        // Verify that KoopaMove was called for each Koopa
        verify(mockMap, times(1)).KoopaMove(mockKoopa1);
        verify(mockMap, times(1)).KoopaMove(mockKoopa2);
    }

    @Test
    void testRun_ExceedsInterval_UpdatesLastMove() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        koopaController.lastMove = 1000;
        long currentTime = 2001;

        // Run the controller
        koopaController.run(mockGame, GUI.ACTION.NONE, currentTime);

        // Verify that lastMove is updated
        assertEquals(currentTime, koopaController.lastMove);
    }
}


