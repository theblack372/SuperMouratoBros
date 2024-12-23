package com.t05g04.game.model.elements;

import com.t05g04.game.model.game.Position;
import com.t05g04.game.model.game.arena.Map;
import com.t05g04.game.model.game.elements.Koopa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class KoopaTest {

    private Koopa koopa;
    private Map mapMock;

    @BeforeEach
    void setUp() {
        koopa = new Koopa(new Position(5, 5), 1);
        mapMock = mock(Map.class);
    }

    @Test
    void testMoveWithPositiveVelocity() {
        koopa.move();
        Position position = koopa.getPosition();

        assertEquals(6, position.getX());
        assertEquals(5, position.getY());
    }

    @Test
    void testMoveWithNegativeVelocity() {
        koopa.setVelocity_(-1);

        koopa.move();
        Position position = koopa.getPosition();

        assertEquals(4, position.getX());
        assertEquals(5, position.getY());
    }

    @Test
    void testMoveWithMapCheck_CanMove() {
        when(mapMock.canObjectMove(any(Position.class))).thenReturn(true);

        koopa.move(mapMock);
        Position position = koopa.getPosition();

        // A posição X deveria ter sido aumentada em 1
        assertEquals(6, position.getX());
        assertEquals(5, position.getY());

        verify(mapMock, times(1)).canObjectMove(any(Position.class));
    }

    @Test
    void testMoveWithMapCheck_CannotMove() {
        when(mapMock.canObjectMove(any(Position.class))).thenReturn(false);

        koopa.move(mapMock);
        Position position = koopa.getPosition();

        assertEquals(4, position.getX());
        assertEquals(5, position.getY());

        verify(mapMock, times(1)).canObjectMove(any(Position.class));
    }
}