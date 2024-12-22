package com.t05g04.game.controller.game;
import com.t05g04.game.controller.sound.SoundController;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.model.game.arena.Map;
import com.t05g04.game.gui.GUI;
import com.t05g04.game.model.game.elements.Coin;
import com.t05g04.game.model.menu.DeathMenu;
import com.t05g04.game.model.menu.EndLevelMenu;
import com.t05g04.game.model.sound.SoundOptions;


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
    private long lastbulletAppearingTime = System.currentTimeMillis();
    private Map map;
    private String mapPath;

    public Game(String mapPath, LanternaGui gui) throws IOException, URISyntaxException, FontFormatException {
        map = new Map(32, 18, mapPath, gui);
        this.mapPath = mapPath;
        this.gui = gui;
    }

    public void run() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        int fps = 18;
        int frameTime = 1000 / fps;
        map.getRenderer().renderObjects(map);
        int coinCounter= map.getCoins().size();
        SoundController.getInstance().playSound(SoundOptions.MUSIC);
        while (!endTerminal) {
            map.makePowerup();
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
                    map.headshot(); // Mover Koopa
                    lastbulletAppearingTime = System.currentTimeMillis();
                }// Atualiza o tempo do último movimento
            }
            GUI.ACTION action = gui.getNextAction();

            if (action== GUI.ACTION.QUIT ) {
                endTerminal = true;
                gui.close();
            }

            if ((map.getMourato().getPosition().getY()>= map.getHeight_()-1)){
                endTerminal = true;
                SoundController.getInstance().playSound(SoundOptions.MARIO_DEATH);
                Thread.sleep(4000);
                DeathMenu menu = new DeathMenu(new String[]{"Retry", "Exit"}, gui, mapPath);
                menu.run();
                break;
            }

            for(int i=0;i<map.koopasNo();i++) {
                if((map.getKoopa(i)!=null &&map.getMourato().getPosition().equals(map.getKoopa(i).getPosition()))) {
                    if (!map.getMourato().isSuperMourato_()) {
                        endTerminal = true;
                        SoundController.getInstance().playSound(SoundOptions.MARIO_DEATH);
                        Thread.sleep(4000);
                        DeathMenu deathMenu = new DeathMenu(new String[]{"Retry", "Exit"}, gui, mapPath);
                        deathMenu.run();
                        break;
                    } else {
                        map.getMourato().setCountBullets_(map.getMourato().getCountBullets_()-1);
                        if(map.getMourato().getCountBullets_()==0) {
                            map.getMourato().setSuperMourato_(false);
                        }
                    }
                }
            }
            for(int i=0;i<map.flowerNo();i++) {
                if((map.getFlower(i).isAppearing() && map.getMourato().getPosition().equals(map.getFlower(i).getPosition()))){
                    if(!map.getMourato().isSuperMourato_()) {
                        endTerminal = true;
                        SoundController.getInstance().playSound(SoundOptions.MARIO_DEATH);
                        Thread.sleep(4000);
                        DeathMenu deathMenu = new DeathMenu(new String[]{"Retry", "Exit"}, gui, mapPath);
                        deathMenu.run();

                    } else {
                        map.getMourato().setCountBullets_(map.getMourato().getCountBullets_()-1);
                        if(map.getMourato().getCountBullets_()==0) {
                            map.getMourato().setSuperMourato_(false);
                        }
                    }
                }
            }

            map.processKey(action);
            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;
            int currentBullet =map.getMourato().getCountBullets_();
            int currentCoin = coinCounter - map.getCoins().size();
            draw(currentCoin, currentBullet);// redesenha a tela
//            String messageCoin = String.format("coins: %d", currentCoin);
//            String messageBullet = String.format("bullets: %d", currentBullet);
            if (map.flagReach()){
                SoundController.getInstance().playSound(SoundOptions.STAGE_CLEAR);
                EndLevelMenu endMenu = new EndLevelMenu(new String[]{"Continue", "Retry", "Exit"}, gui, mapPath);
                endMenu.run();
                break;
            }
//            gui.displayMessage(gui.getScreen(), messageCoin, 1,1);
//            gui.displayMessage(gui.getScreen(), messageBullet, 1,2);
            try {
                if (sleepTime>0){Thread.sleep(sleepTime);} // Ajusta para que o loop tenha uma duração constante
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


    private void draw(int coins, int bullets) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        map.getRenderer().draw(gui.getScreen().newTextGraphics(),map, coins, bullets);
        map.updateJump(map.getMourato());
        gui.refresh();
    }
}

