package com.t05g04.game.model;

import com.t05g04.game.model.game.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PositionTest {
    @Test
    public void testEqualsMethod() {
        Position position1 = new Position(2, 3);
        Position position2 = new Position(2, 3);
        Position position3 = new Position(4, 5);

        assertTrue(position1.equals(position1));

        assertTrue(position1.equals(position2));

        assertFalse(position1.equals(position3));

        assertFalse(position1.equals(null));

        assertFalse(position1.equals("Not a Position"));
    }
}
