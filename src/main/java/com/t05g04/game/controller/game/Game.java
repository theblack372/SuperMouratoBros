package com.t05g04.game.controller.game;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.model.game.arena.Map;
import com.t05g04.game.gui.GUI;


import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private static final long KOOPA_MOVE_INTERVAL = 1000;
    private static final long Flower_APPEARING_INTERVAL = 3000;
    boolean endTerminal = false;
    LanternaGui gui;
    private long lastFlowerAppearingTime = System.currentTimeMillis();
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
            if(System.currentTimeMillis() - lastFlowerAppearingTime >= Flower_APPEARING_INTERVAL) {
                for(int i=0;i<map.flowerNo();i++) {
                    map.getFlower(i).setAppearing(!map.getFlower(i).isAppearing());
                    lastFlowerAppearingTime = System.currentTimeMillis();
                }
            }
            // Verifica se já passou 1 segundo (1000 ms)
            if (System.currentTimeMillis() - lastKoopaMoveTime >= KOOPA_MOVE_INTERVAL) {
                for(int i=0;i<map.flowerNo();i++) {
                    map.KoopaMove(map.getKoopa(i)); // Mover Koopa
                    lastKoopaMoveTime = System.currentTimeMillis();
                }// Atualiza o tempo do último movimento
            }
            GUI.ACTION action = gui.getNextAction();

            if (action== GUI.ACTION.QUIT || (map.getMourato().getPosition().getY()>= map.getHeight_()-1)){
                endTerminal = true;
                gui.close();
            }
            for(int i=0;i<map.koopasNo();i++) {
                if((map.getKoopa(i)!=null &&map.getMourato().getPosition().equals(map.getKoopa(i).getPosition()))){
                    endTerminal = true;
                    gui.close();
                }
            }
            for(int i=0;i<map.flowerNo();i++) {
                if((map.getFlower(i).isAppearing() && map.getMourato().getPosition().equals(map.getFlower(i).getPosition()))){
                    endTerminal = true;
                    gui.close();
                }
            }

            map.processKey(action);
            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;
            int currentCoin = coinCounter - map.getCoins().size();
            draw();// redesenha a tela
            String messageCoin = String.format("coins: %d", currentCoin);
            if (map.flagReach()){
                String endMessage = String.format("you won with %d coins!",currentCoin);
                gui.displayMessage(gui.getScreen(), endMessage, 6,7);
                Thread.sleep(3000);
                gui.close();
            }
            gui.displayMessage(gui.getScreen(), messageCoin, 1,1);
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

