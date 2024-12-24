package com.t05g04.game.controller.game;

import com.t05g04.game.Game;
import com.t05g04.game.gui.GUI;
import com.t05g04.game.model.game.elements.Flower;
import com.t05g04.game.model.game.map.Map;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class FlowerController extends GameController {
    public long lastFlowerAppearingTime;
    public static final long Flower_APPEARING_INTERVAL = 3000;

    public FlowerController(Map map) {
        super(map);
        this.lastFlowerAppearingTime = 0;
    }

    @Override
    public void run(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        if (time - lastFlowerAppearingTime >= Flower_APPEARING_INTERVAL) {
            for (Flower flower:getModel().getFlowers()) {
                flower.setAppearing(!flower.isAppearing());
            }
            lastFlowerAppearingTime = time;
        }
    }

}
