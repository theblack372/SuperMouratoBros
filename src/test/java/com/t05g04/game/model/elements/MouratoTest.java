// Não foi testado o movimento do salto e da queda do mourato
package com.t05g04.game.model.elements;
import com.t05g04.game.model.game.Position;
import com.t05g04.game.model.game.arena.Map;
import com.t05g04.game.model.game.elements.Mourato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MouratoTest {
    Position position;
    Map map;
    Mourato mourato;

    @BeforeEach
    void setUp() {
        position = new Position(5, 5);
        map= new Map(10,10, "maps/map1");

        mourato = new Mourato(position, false, false,5, 10, 2,0);
    }

    @Test
    public void mouratoConstructorTest() {

        assertEquals(position, mourato.getPosition());
        assertFalse(mourato.isJump_());
        assertEquals(5, mourato.getJumpVelocity_());
        assertEquals(10, mourato.getJumpHeight_());
        assertEquals(2, mourato.getCountJump_());
    }


    @Test
    public void mouratoGetters() {

        assertFalse(mourato.isJump_());
        assertEquals(5, mourato.getJumpVelocity_());
        assertEquals(10, mourato.getJumpHeight_());
        assertEquals(2, mourato.getCountJump_());
    }

    @Test
    public void testSetters() {
        mourato.setCountJump_(3);
        assertEquals(3, mourato.getCountJump_());

        mourato.setJumpVelocity_(12);
        assertEquals(12, mourato.getJumpVelocity_());

        mourato.setJump_(true);
        assertTrue(mourato.isJump_());
    }

    @Test
    //testing left movement
    public void testMoveLeft() {
        Position newPosition = mourato.moveLeft();
        assertEquals(4, newPosition.getX());
        assertEquals(5, newPosition.getY());
    }


    //Testing right movement
    @Test
    public void testMoveRight() {
        Position newPosition = mourato.moveRight();
        assertEquals(6, newPosition.getX());
        assertEquals(5, newPosition.getY());
    }
}
