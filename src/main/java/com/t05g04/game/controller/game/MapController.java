package com.t05g04.game.controller.game;

import com.t05g04.game.Game;
import com.t05g04.game.gui.GUI;
import com.t05g04.game.model.game.map.Map;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MapController extends GameController {
    private MouratoController mouratoController;
    private MonstersController monstersController;
    private BulletController bulletController;

    public MapController(Map map) {
        super(map);
        mouratoController = new MouratoController(map);
        monstersController = new MonstersController(map);
        bulletController = new BulletController(map);

    }

    public void setBulletController(BulletController newBulletController) {
        this.bulletController = newBulletController;
    }
    public void setMonstersController(MonstersController newMonstersController) {
        this.monstersController = newMonstersController;
    }
    public void setMouratoController(MouratoController newMouratoController) {
        this.mouratoController = newMouratoController;
    }

    @Override
    public void run(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        getModel().makePowerup();
        if(action == GUI.ACTION.QUIT){
            game.setState(null);
        }
        monstersController.run(game, action, time);
        bulletController.run(game, action, time);
        mouratoController.run(game, action, time);
        game.draw(game.coinsTaken,game.remainingBullets);

    }
}
