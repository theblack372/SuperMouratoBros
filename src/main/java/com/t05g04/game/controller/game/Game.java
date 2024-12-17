package com.t05g04.game.controller.game;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.model.game.arena.Map;
import com.t05g04.game.gui.GUI;
import com.t05g04.game.model.menu.DeathMenu;
import com.t05g04.game.model.menu.EndLevelMenu;


import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private static final long BULLET_DELAY = 100;
    private static final long KOOPA_MOVE_INTERVAL = 1000;
    private static final long Flower_APPEARING_INTERVAL = 3000;
    boolean endTerminal = false;
    LanternaGui gui;
    private long lastFlowerAppearingTime = System.currentTimeMillis();
    private long lastKoopaMoveTime = System.currentTimeMillis(); // Controla o tempo de movimento do Koopa
    private long lastbulletAppearingTime = System.currentTimeMillis();    private Map map;
    private String mapPath;

    public Game(String mapPath) throws IOException, URISyntaxException, FontFormatException {
        map = new Map(32, 18, mapPath);
        gui = new LanternaGui(map.getWidth_(), map.getHeight_());
        this.mapPath = mapPath;
    }

    public void run() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        int fps = 18;
        int frameTime = 1000 / fps;
        map.getRenderer().renderObjects(map);
        int coinCounter= map.getCoins().size();
        while (!endTerminal) {
            map.makePowerup(map.getMourato());
            long startTime = System.currentTimeMillis();
            if(System.currentTimeMillis() - lastFlowerAppearingTime >= Flower_APPEARING_INTERVAL) {
                for(int i=0;i<map.flowerNo();i++) {
                    map.getFlower(i).setAppearing(!map.getFlower(i).isAppearing());
                    lastFlowerAppearingTime = System.currentTimeMillis();
                }
            }
            // Verifica se já passou 1 segundo (1000 ms)
            if (System.currentTimeMillis() - lastKoopaMoveTime >= KOOPA_MOVE_INTERVAL) {
                for(int i=0;i<map.koopasNo();i++) {
                    map.KoopaMove(map.getKoopa(i)); // Mover Koopa
                    lastKoopaMoveTime = System.currentTimeMillis();
                }// Atualiza o tempo do último movimento
            }
            if (System.currentTimeMillis() - BULLET_DELAY >= lastbulletAppearingTime) {
                for(int i=0;i<map.getBullets().size();i++) {
                    map.BulletMove(map.getBullet(i));
                    map.headshot();// Mover Koopa
                    lastbulletAppearingTime = System.currentTimeMillis();
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

                    DeathMenu menu = new DeathMenu(new String[]{"Retry", "Exit"}, new LanternaGui(32, 18), mapPath);
                    menu.run();
        }
            }
            for(int i=0;i<map.flowerNo();i++) {
                if((map.getFlower(i).isAppearing() && map.getMourato().getPosition().equals(map.getFlower(i).getPosition()))){
                    endTerminal = true;
                    gui.close();
                    DeathMenu menu = new DeathMenu(new String[]{"Retry", "Exit"}, new LanternaGui(32, 18), mapPath);
                    menu.run();
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
                Thread.sleep(2000);
                gui.close();
                EndLevelMenu menu = new EndLevelMenu(new String[]{"Continue", "Retry", "Exit"}, new LanternaGui(32,18) , mapPath);
                menu.run();
                break;
            }
            gui.displayMessage(gui.getScreen(), messageCoin, 1,1);
            try {
                if (sleepTime>0){Thread.sleep(sleepTime);} // Ajusta para que o loop tenha uma duração constante
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


    private void draw() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        map.getRenderer().draw(gui.getScreen().newTextGraphics(),map);
        map.updateJump(map.getMourato());
        gui.refresh();
    }
}

