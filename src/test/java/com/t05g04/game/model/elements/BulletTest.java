package com.t05g04.game.model.elements;

import com.t05g04.game.model.game.Position;
import com.t05g04.game.model.game.map.Map;
import com.t05g04.game.model.game.elements.Bullet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class BulletTest {

    private Bullet bullet;
    private Map mapMock;

    @BeforeEach
    void setUp() {
        bullet = new Bullet(new Position(5, 5), 1);
        mapMock = mock(Map.class);
    }

    @Test
    void testMoveWithPositiveVelocity() {
        bullet.move();
        Position position = bullet.getPosition();

        assertEquals(6, position.getX());
        assertEquals(5, position.getY());
    }

    @Test
    void testMoveWithMapCheck_CanMove() {
        when(mapMock.canObjectMove(any(Position.class))).thenReturn(true);

        bullet.move(mapMock);
        Position position = bullet.getPosition();

        assertEquals(6, position.getX());
        assertEquals(5, position.getY());

        verify(mapMock, times(1)).canObjectMove(any(Position.class));
    }

    @Test
    void testMoveWithMapCheck_Collision() {
        when(mapMock.canObjectMove(any(Position.class))).thenReturn(false);

        bullet.move(mapMock);
        Position position = bullet.getPosition();

        assertEquals(5, position.getX());
        assertEquals(5, position.getY());

        verify(mapMock, times(1)).getBullets();
    }

}
