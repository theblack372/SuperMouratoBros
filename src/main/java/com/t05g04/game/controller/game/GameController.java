package com.t05g04.game.controller.game;

import com.t05g04.game.controller.Controller;
import com.t05g04.game.model.game.map.Map;

public abstract class GameController extends Controller<Map> {
    public GameController(Map map) {
        super(map);
    }


}
