package com.t05g04.game.controller.game;

import com.t05g04.game.Game;
import com.t05g04.game.gui.GUI;
import com.t05g04.game.model.game.map.Map;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class FlowerController extends GameController {
    private long lastFlowerAppearingTime;
    private static final long Flower_APPEARING_INTERVAL = 3000;

    public FlowerController(Map map) {
        super(map);
        this.lastFlowerAppearingTime = 0;
    }

    @Override
    public void run(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        if(time - lastFlowerAppearingTime >= Flower_APPEARING_INTERVAL) {
            for(int i=0;i<getModel().flowerNo();i++) {
                getModel().getFlower(i).setAppearing(!getModel().getFlower(i).isAppearing());
                lastFlowerAppearingTime = time;
            }
        }
    }
}
