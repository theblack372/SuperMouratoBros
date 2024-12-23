package com.t05g04.game;
import com.t05g04.game.controller.sound.SoundController;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.model.game.map.Map;
import com.t05g04.game.model.sound.SoundOptions;
import com.t05g04.game.states.GameState;
import com.t05g04.game.states.State;


import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class Game {
    LanternaGui gui;
    private Map map;
    private State state;
    public int coinsTaken;
    public int remainingBullets;

    public Game(String mapPath, LanternaGui gui) throws IOException, URISyntaxException, FontFormatException {
        map = new Map(32, 18, mapPath, gui);
        this.gui = gui;
        this.state = new GameState(map);
    }

    public void setState(State state) {
        this.state = state;
    }

    public void run() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        int fps = 18;
        int frameTime = 1000 / fps;
        map.getRenderer().renderObjects(map);
        int coinCounter= map.getCoins().size();
        SoundController.getInstance().run(SoundOptions.MUSIC);
        while (this.state != null) {
            long startTime = System.currentTimeMillis();
            remainingBullets =map.getMourato().getCountBullets_();
            coinsTaken = coinCounter - map.getCoins().size();
            state.step(this, gui, startTime);
            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime>0){Thread.sleep(sleepTime);} // Ajusta para que o loop tenha uma duração constante
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        gui.close();
    }


    public void draw(int coins, int bullets) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        map.getRenderer().draw(gui.getScreen().newTextGraphics(),map, coins, bullets);
        map.updateJump(map.getMourato());
        gui.refresh();
    }
}

