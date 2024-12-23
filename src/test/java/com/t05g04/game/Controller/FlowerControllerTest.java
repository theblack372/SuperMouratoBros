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
        // Criar mocks
        mockMap = Mockito.mock(Map.class);
        mockFlower = Mockito.mock(Flower.class);

        // Configurar o mapa para retornar uma flor mockada
        when(mockMap.flowerNo()).thenReturn(1); // Uma flor no mapa
        when(mockMap.getFlower(0)).thenReturn(mockFlower);

        // Criar a instância do FlowerController com o mapa mockado
        flowerController = new FlowerController(mockMap);
    }


    @Test
    void testRunDoesNotAlternateFlowerVisibilityBeforeInterval() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        // Configura o mock para o flower, se necessário
        when(mockFlower.isAppearing()).thenReturn(true);

        // Simula a passagem de tempo - com um tempo menor que o intervalo de 3000ms
        long currentTime = System.currentTimeMillis();
        long timeBeforeInterval = currentTime + 2000; // tempo menor que 3000ms

        // Chama o método run
        flowerController.run(null, GUI.ACTION.NONE, timeBeforeInterval); // Não deve alterar a visibilidade

        // Verifica que setAppearing não foi chamado, pois o intervalo ainda não passou
        verify(mockFlower, never()).setAppearing(anyBoolean());  // Garantir que setAppearing não foi chamado
    }
}


