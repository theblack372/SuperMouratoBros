package com.t05g04.game.model.map;

import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.model.game.Position;
import com.t05g04.game.model.game.elements.*;
import com.t05g04.game.model.game.map.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MapTest {

    private Map map;
    private LanternaGui mockGui;

    @BeforeEach
    void setUp() {
        mockGui = mock(LanternaGui.class);
        map = new Map(32, 18, "maps/map1.txt", mockGui);
    }

    @Test
    void testCreateAndRetrieveCoins() {
        Position position = new Position(5, 5);
        map.createCoin(position);

        List<Coin> coins = map.getCoins();
        assertEquals(1, coins.size());
        assertEquals(position, coins.get(0).getPosition());
    }

    @Test
    void testCreateKoopas() {
        Position position = new Position(7, 3);
        map.createKoopa(position);

        List<Koopa> koopas = map.getKoopas();
        assertEquals(1, koopas.size());
        assertEquals(position, koopas.getFirst().getPosition());
    }

    @Test
    void testRetrieveCoins_CoinTaken() {
        Position coinPosition = new Position(5, 5);
        map.createCoin(coinPosition);

        map.retrieveCoins(coinPosition);

        assertTrue(map.getCoins().isEmpty());
    }

    @Test
    void testGoSuperMourato_ValidPowerup() {
        Position powerupPosition = new Position(6, 6);
        map.createPowerup(powerupPosition);
        map.getPowerups().get(0).setAppearing(true);

        map.goSuperMourato(powerupPosition);

        assertTrue(map.getMourato().isSuperMourato_());
        assertTrue(map.getPowerups().isEmpty());
    }

    @Test
    void testCanObjectMove_ValidPosition() {
        Position validPosition = new Position(10, 5);
        map.getRenderer().getMap_()[10][5] = ' '; // Simula terreno acessível

        assertTrue(map.canObjectMove(validPosition));
    }

    @Test
    void testCanObjectMove_InvalidPosition() {
        Position invalidPosition = new Position(10, 5);
        map.getRenderer().getMap_()[10][5] = '#'; // Simula terreno bloqueado

        assertFalse(map.canObjectMove(invalidPosition));
    }

    @Test
    void testOutBounds_True() {
        map.getMourato().getPosition().setY(map.getHeight_() - 1);

        assertTrue(map.outBounds());
    }

    @Test
    void testOutBounds_False() {
        map.getMourato().getPosition().setY(map.getHeight_() - 2);

        assertFalse(map.outBounds());
    }

    @Test
    void testFlagReach_True() throws InterruptedException {
        Position flagPosition = new Position(15, 10);
        map.getMourato().setPosition_(flagPosition);
        map.getRenderer().getMap_()[15 + map.getStartX_()][10] = '|';

        assertTrue(map.flagReach());
    }

    @Test
    void testFlagReach_False() throws InterruptedException {
        Position nonFlagPosition = new Position(15, 10);
        map.getMourato().setPosition_(nonFlagPosition);
        map.getRenderer().getMap_()[15 + map.getStartX_()][10] = ' ';

        assertFalse(map.flagReach());
    }

    @Test
    void testHeadshot_KoopaDestroyed() {
        Position bulletPosition = new Position(10, 10);
        Position koopaPosition = new Position(11, 10);
        map.createBullet(bulletPosition);
        map.createKoopa(koopaPosition);

        map.headshot();

        assertTrue(map.getKoopas().isEmpty());
        assertTrue(map.getBullets().isEmpty());
    }

    @Test
    void testHeadshot_NoKoopaDestroyed() {
        Position bulletPosition = new Position(10, 10);
        Position koopaPosition = new Position(15, 10);
        map.createBullet(bulletPosition);
        map.createKoopa(koopaPosition);

        map.headshot();

        assertFalse(map.getKoopas().isEmpty());
        assertFalse(map.getBullets().isEmpty());
    }

    @Test
    void testIsMouratoMiddle_True() {
        // Mourato está na posição central (X=16)
        map.getMourato().setPosition_(new Position(16, 10));
        map.incrementStartX_(); // StartX diferente de largura do mapa - largura da tela

        boolean result = map.isMouratoMiddle();

        assertTrue(result, "Mourato deveria estar no meio do mapa.");
    }
}