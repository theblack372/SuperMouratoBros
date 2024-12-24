package com.t05g04.game.Controller;

import com.t05g04.game.Game;
import com.t05g04.game.controller.game.BulletController;
import com.t05g04.game.controller.game.MapController;
import com.t05g04.game.controller.game.MonstersController;
import com.t05g04.game.controller.game.MouratoController;
import com.t05g04.game.gui.GUI;
import com.t05g04.game.model.game.map.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

public class MapControllerTest {
    private MapController mapController;
    private Map mockMap;
    private Game mockGame;
    private MonstersController mockMonstersController;
    private BulletController mockBulletController;
    private MouratoController mockMouratoController;

    @BeforeEach
    void setUp() {
        mockMap = mock(Map.class);
        mockGame = mock(Game.class);

        mockMonstersController = spy(new MonstersController(mockMap));
        mockBulletController = spy(new BulletController(mockMap));
        mockMouratoController = spy(new MouratoController(mockMap));

        mapController = new MapController(mockMap);

        mapController.setMonstersController(mockMonstersController);
        mapController.setBulletController(mockBulletController);
        mapController.setMouratoController(mockMouratoController);
    }

    @Test
    void testRun_HandlesGame() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        mapController.run(mockGame, GUI.ACTION.NONE, 1000);

        verify(mockMouratoController, times(1)).run(mockGame, GUI.ACTION.NONE, 1000);
        verify(mockMonstersController, times(1)).run(mockGame, GUI.ACTION.NONE, 1000);
        verify(mockBulletController, times(1)).run(mockGame, GUI.ACTION.NONE, 1000);

        verify(mockMap, times(1)).makePowerup();
        verify(mockGame, times(1)).draw(anyInt(), anyInt());
    }
    @Test
    void testRun_QuitAction() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        mapController.run(mockGame, GUI.ACTION.QUIT, 1000);

        verify(mockGame, times(1)).setState(null);
    }

    @Test
    void testRun_MakesPowerup() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        mapController.run(mockGame, GUI.ACTION.NONE, 1000);

        verify(mockMap, times(1)).makePowerup();
    }
}

