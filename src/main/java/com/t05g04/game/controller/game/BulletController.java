package com.t05g04.game.controller.game;

import com.t05g04.game.Game;
import com.t05g04.game.gui.GUI;
import com.t05g04.game.model.game.elements.Bullet;
import com.t05g04.game.model.game.map.Map;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class BulletController extends GameController{
    private static final long BULLET_DELAY = 100;
    private long lastbulletAppearingTime;

    public BulletController(Map map) {
        super(map);
    }

    @Override
    public void run(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        if (time - BULLET_DELAY >= lastbulletAppearingTime) {
            for (Bullet bullet : getModel().getBullets()) {
                getModel().BulletMove(bullet);
                getModel().headshot();
            }
            lastbulletAppearingTime = time;
        }
    }

}
