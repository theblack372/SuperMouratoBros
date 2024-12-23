package com.t05g04.game.model.elements;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.googlecode.lanterna.screen.Screen;
import com.t05g04.game.model.game.Position;
import com.t05g04.game.model.game.arena.Map;
import com.t05g04.game.model.game.elements.Koopa;
import com.t05g04.game.model.game.elements.Mourato;
import com.t05g04.game.viewer.game.Renderer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.t05g04.game.gui.LanternaGui;
import org.mockito.Mockito;


class MouratoTest {
    private Mourato mourato;
    private Map mapMock;
    private Renderer rendererMock;

    @BeforeEach
    void setUp() throws IOException {

        mourato = new Mourato(new Position(5, 5), false, true, 1, 4, 0, 5);
        mapMock = mock(Map.class);
        rendererMock = mock(Renderer.class);

        when(mapMock.getRenderer()).thenReturn(rendererMock);
        when(rendererMock.getMapPath()).thenReturn("mocked/path/to/map");
    }

    @Test
    void testUpdateJump_NoJump() {
        mourato.setJump_(false);
        mourato.updateJump(mapMock);
        assertEquals(0, mourato.getCountJump_());
    }


    @Test
    void testDestroyKoopaIfHit() {
        List<Koopa> koopas = new ArrayList<>();
        Koopa koopa1 = new Koopa(new Position(5, 6), -1);
        Koopa koopa2 = new Koopa(new Position(7, 6), -1);
        koopas.add(koopa1);
        koopas.add(koopa2);
        when(mapMock.getKoopas()).thenReturn(koopas);

        mourato.destroyKoopaIfHit(mapMock);

        assertEquals(1, koopas.size());
        assertFalse(koopas.contains(koopa1));
    }

    @Test
    void testShootBullet_SuperMourato() {
        mourato.setSuperMourato_(true);
        mourato.setCountBullets_(3);

        mourato.shootBullet(mapMock);

        verify(mapMock, times(1)).createBullet(any(Position.class));
        assertEquals(2, mourato.getCountBullets_());
    }

    @Test
    void testShootBullet_NoBullets() {
        mourato.setSuperMourato_(true);
        mourato.setCountBullets_(0);

        mourato.shootBullet(mapMock);

        verify(mapMock, never()).createBullet(any(Position.class));
        assertFalse(mourato.isSuperMourato_());
    }
}