package com.t05g04.game.controller;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.model.game.arena.Map;
import com.t05g04.game.gui.GUI;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Game {
    private static final long KOOPA_MOVE_INTERVAL = 1000;
    boolean endTerminal = false;
    LanternaGui gui;
    private long lastKoopaMoveTime = System.currentTimeMillis(); // Controla o tempo de movimento do Koopa
    private final Map map = new Map(32, 18);

    public Game() throws IOException, URISyntaxException, FontFormatException {
        gui = new LanternaGui(map.getWidth_(), map.getHeight_());
    }

    public void run() throws IOException, InterruptedException {
        int fps = 18;
        int frameTime = 1000 / fps;
        int coinCounter= map.getCoins().size();
        while (!endTerminal) {
            long startTime = System.currentTimeMillis();
            // Verifica se já passou 1 segundo (1000 ms)
            if (System.currentTimeMillis() - lastKoopaMoveTime >= KOOPA_MOVE_INTERVAL) {
                map.KoopaMove(map.getKoopa()); // Mover Koopa
                lastKoopaMoveTime = System.currentTimeMillis(); // Atualiza o tempo do último movimento
            }
            GUI.ACTION action = gui.getNextAction();
            if (action== GUI.ACTION.QUIT || (map.getMourato().getPosition().getY()>= map.getHeight_()-1)
                    ||(map.getKoopa()!=null &&map.getMourato().getPosition().equals(map.getKoopa().getPosition()))){
                endTerminal = true;
                gui.close();
            }
            draw();// redesenha a tela
            map.processKey(action);
            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;
            int currentCoin = coinCounter - map.getCoins().size();
            String messageCoin = String.format("coins: %d", currentCoin);
            if (map.flagReach()){
                String endMessage = String.format("you won with %d coins!",currentCoin);
                gui.displayMessage(gui.getScreen(), endMessage, 6,7);
                Thread.sleep(3000);
                gui.close();
            }
            gui.displayMessage(gui.getScreen(), messageCoin, 2,2);
            try {
                if (sleepTime>0){Thread.sleep(sleepTime);} // Ajusta para que o loop tenha uma duração constante
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


    private void draw() throws IOException {
        map.getRenderer().draw(gui.getScreen().newTextGraphics(),map);
        map.updateJump(map.getMourato());
        gui.refresh();
    }
}

