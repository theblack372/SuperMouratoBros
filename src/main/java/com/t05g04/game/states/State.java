package com.t05g04.game.states;

import com.t05g04.game.controller.Controller;
import com.t05g04.game.Game;
import com.t05g04.game.gui.GUI;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class State<T> {

    private final T model;
    private final Controller<T> controller;


    public State(T model) throws IOException {
        this.model = model;
        this.controller = getController();

    }


    protected abstract Controller<T> getController() throws IOException;

    public T getModel() {
        return model;
    }


    public void step(Game game, GUI gui, long time) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        GUI.ACTION action = gui.getNextAction();
        controller.run(game, action, time);
    }
}
