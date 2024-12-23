package com.t05g04.game.controller.game;

import com.t05g04.game.Game;
import com.t05g04.game.gui.GUI;
import com.t05g04.game.model.game.elements.Koopa;
import com.t05g04.game.model.game.map.Map;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class KoopaController extends GameController {
    public long lastMove;
    private static final long KOOPA_MOVE_INTERVAL = 1000;
    public KoopaController(Map map) {
        super(map);
        this.lastMove = 0;
    }

    @Override
    public void run(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        if(time - lastMove >= KOOPA_MOVE_INTERVAL) {
            for(Koopa koopa: getModel().getKoopas()) {
                getModel().KoopaMove(koopa);
                lastMove = time;
            }
        }
    }
}
