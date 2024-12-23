package com.t05g04.game.controller.game;

import com.t05g04.game.Game;
import com.t05g04.game.gui.GUI;
import com.t05g04.game.model.game.map.Map;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MonstersController extends GameController {
    private final FlowerController flowerController;
    private final KoopaController koopaController;

    public MonstersController(Map map) {
        super(map);
        this.flowerController = new FlowerController(map);
        this.koopaController = new KoopaController(map);

    }

    @Override
    public void run(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        flowerController.run(game, action, time);
        koopaController.run(game, action, time);
    }
}
