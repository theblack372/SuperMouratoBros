package com.t05g04.game.states;

import com.t05g04.game.controller.Controller;
import com.t05g04.game.controller.game.MapController;
import com.t05g04.game.model.game.map.Map;

import java.io.IOException;

public class GameState extends State<Map> {
    public GameState(Map map) throws IOException {
        super(map);
    }

    @Override
    protected Controller<Map> getController() {
        return new MapController(getModel());
    }
}
