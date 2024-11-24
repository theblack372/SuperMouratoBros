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

        // testa objetos identicos
        assertTrue(position1.equals(position1));

        // testa objetos com os mesmos valores
        assertTrue(position1.equals(position2));

        // testa objetos com valores diferentes
        assertFalse(position1.equals(position3));

        // testa contra o nulo
        assertFalse(position1.equals(null));

        // testa contra objetos de classes diferentes.
        assertFalse(position1.equals("Not a Position"));
    }
}
