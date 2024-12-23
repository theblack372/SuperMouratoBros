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
        // Mocking dependencies
        mockMap = mock(Map.class);
        mockGame = mock(Game.class);

        // Spying on controllers
        mockMonstersController = spy(new MonstersController(mockMap));
        mockBulletController = spy(new BulletController(mockMap));
        mockMouratoController = spy(new MouratoController(mockMap));

        // Initialize the MapController
        mapController = new MapController(mockMap);

        // Replace the controllers with spies
        mapController.setMonstersController(mockMonstersController);
        mapController.setBulletController(mockBulletController);
        mapController.setMouratoController(mockMouratoController);
    }

    @Test
    void testRun_HandlesGame() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        // Call the method under test
        mapController.run(mockGame, GUI.ACTION.NONE, 1000);

        // Verify the controllers are called as expected
        verify(mockMouratoController, times(1)).run(mockGame, GUI.ACTION.NONE, 1000);
        verify(mockMonstersController, times(1)).run(mockGame, GUI.ACTION.NONE, 1000);
        verify(mockBulletController, times(1)).run(mockGame, GUI.ACTION.NONE, 1000);

        // Verify other interactions
        verify(mockMap, times(1)).makePowerup();
        verify(mockGame, times(1)).draw(anyInt(), anyInt());
    }
    @Test
    void testRun_QuitAction() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        mapController.run(mockGame, GUI.ACTION.QUIT, 1000);

        // Verify that the game's state is set to null
        verify(mockGame, times(1)).setState(null);
    }
    @Test
    void testRun_MakesPowerup() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        mapController.run(mockGame, GUI.ACTION.NONE, 1000);

        verify(mockMap, times(1)).makePowerup();
    }
}

