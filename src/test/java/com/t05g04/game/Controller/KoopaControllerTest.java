package com.t05g04.game.Controller;

import com.t05g04.game.controller.game.KoopaController;
import com.t05g04.game.gui.GUI;
import com.t05g04.game.model.game.elements.Koopa;
import com.t05g04.game.model.game.map.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.FontFormatException;;

class KoopaControllerTest {

    private KoopaController koopaController;
    private Koopa mockKoopa;
    private Map mockMap;

    @BeforeEach
    void setUp() {
        mockKoopa = mock(Koopa.class);

        mockMap = mock(Map.class);
        when(mockMap.koopasNo()).thenReturn(1);
        when(mockMap.getKoopa(0)).thenReturn(mockKoopa);
        koopaController = new KoopaController(mockMap);
    }
    //to debug:koopamove is messy
    //@Test
    //void testRunMovesKoopaAfterInterval() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
    //     long currentTime = System.currentTimeMillis();
    //    koopaController.lastMove = currentTime - 2000; // Simula que já passou o intervalo

    //    koopaController.run(null, GUI.ACTION.NONE, currentTime + 2000);
    //    verify(mockMap, times(1)).KoopaMove(mockKoopa);
    //   verify(mockKoopa, times(1)).move(mockMap); // O Koopa deve ter se movido uma vez
    //}
}

