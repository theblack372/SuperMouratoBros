package com.t05g04.game.controller.game;

import com.t05g04.game.Game;
import com.t05g04.game.controller.sound.SoundController;
import com.t05g04.game.gui.GUI;
import com.t05g04.game.model.game.Position;
import com.t05g04.game.model.game.map.Map;
import com.t05g04.game.model.game.elements.*;
import com.t05g04.game.model.menu.DeathMenu;
import com.t05g04.game.model.menu.EndLevelMenu;
import com.t05g04.game.model.sound.SoundOptions;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static com.t05g04.game.Application.gui;

public class MouratoController extends GameController {
    public Mourato mourato = getModel().getMourato();
    public MouratoController(Map map) {
        super(map);
    }

    public void mouratoJump() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        if(!getModel().checkAndFall(mourato)) {
            if (!mourato.isJump_()) {
                    mourato.setJump_(true);
                    SoundController.getInstance().run(SoundOptions.JUMP);
            }
        }
    }

    public void moveMouratoDown(){
        getModel().moveMourato(mourato.moveDown());
    }
    public void moveMouratoLeft() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        getModel().moveMourato(mourato.moveLeft());
        getModel().checkAndFall(mourato);
        getModel().retrieveCoins(mourato.getPosition());
        getModel().goSuperMourato(mourato.getPosition());
        for(Bullet bullet: getModel().getBullets()) {
            if(bullet.getVelocity_()==0) {
                bullet.setVelocity_(-1);
            }
        }
    }
    public void moveMouratoRight() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        if (getModel().isMouratoMiddle() && getModel().canObjectMove(mourato.moveRight())) {
            getModel().incrementStartX_();
            for(Koopa koopa : getModel().getKoopas()){
                koopa.moveTerminal();
            }
            for(Coin coin : getModel().getCoins()) {
                coin.moveTerminal();
            }
            for(Flower flower : getModel().getFlowers()) {
                flower.moveTerminal();
            }
            for(Powerup powerup : getModel().getPowerups()){
                powerup.moveTerminal();
            }
            for(PowerUpBlock powerupblock : getModel().getPowerupBlocks()){
                powerupblock.moveTerminal();
            }
            for(Bullet bullet : getModel().getBullets()){
                bullet.moveTerminal();
                if(bullet.getVelocity_()==0) {
                    bullet.setVelocity_(1);
                }
            }
        }
        else{getModel().moveMourato(mourato.moveRight());
            getModel().checkAndFall(mourato);
            for(Bullet bullet : getModel().getBullets()){
                if(bullet.getVelocity_()==0) {
                    bullet.setVelocity_(1);
                }
            }
        }

        getModel().retrieveCoins(mourato.getPosition());
        getModel().goSuperMourato(mourato.getPosition());

    }

    private boolean mouratoOutOfBounds() {
        return getModel().outBounds();
    }
    private boolean koopasHitDeath(){
        for(int i=0;i<getModel().koopasNo();i++) {
            if((getModel().getKoopa(i)!=null &&getModel().getMourato().getPosition().equals(getModel().getKoopa(i).getPosition()))) {
                if (!getModel().getMourato().isSuperMourato_()) {
                    return true;
                } else {
                    getModel().getMourato().setCountBullets_(getModel().getMourato().getCountBullets_()-1);
                    if(getModel().getMourato().getCountBullets_()==0) {
                        getModel().getMourato().setSuperMourato_(false);
                    }
                }
            }
        }
        return false;
    }

    private boolean flowerHitDeath(){
        for(int i=0;i<getModel().flowerNo();i++) {
            if((getModel().getFlower(i).isAppearing() && getModel().getMourato().getPosition().equals(getModel().getFlower(i).getPosition()))){
                if(!getModel().getMourato().isSuperMourato_()) {
                    return true;

                } else {
                    getModel().getMourato().setCountBullets_(getModel().getMourato().getCountBullets_()-1);
                    if(getModel().getMourato().getCountBullets_()==0) {
                        getModel().getMourato().setSuperMourato_(false);
                    }
                }
            }
        }
        return false;
    }

    private boolean flagReached() throws InterruptedException {
        return getModel().flagReach();
    }

    private void endGameWin(Game game) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        game.draw(game.coinsTaken,game.remainingBullets);
        game.setState(null);
        SoundController.getInstance().run(SoundOptions.STAGE_CLEAR);
        Thread.sleep(7000);
        EndLevelMenu endMenu = new EndLevelMenu(new String[]{"Continue", "Retry", "Exit"}, gui, getModel().getPath_());
        endMenu.run();
        gui.close();
    }

    @Override
    public void run(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        if (action == GUI.ACTION.UP) {
            mouratoJump();
        }
        if (action == GUI.ACTION.DOWN) {
            moveMouratoDown();
        }
        if (action == GUI.ACTION.LEFT) {
            moveMouratoLeft();
        }
        if (action == GUI.ACTION.RIGHT) {
            moveMouratoRight();
        }
        if(action==GUI.ACTION.SHOOT) {
            mourato.shootBullet(getModel());
        }
        if (mouratoOutOfBounds()) {
            game.setState(null);SoundController.getInstance().run(SoundOptions.MARIO_DEATH);
            mourato.setPosition_(new Position(1,14));
            Thread.sleep(4000);
            DeathMenu deathMenu = new DeathMenu(new String[]{"Retry", "Exit"}, gui, getModel().getPath_());
            deathMenu.run();
        }

        if (koopasHitDeath() || flowerHitDeath()){
            game.draw(game.coinsTaken,game.remainingBullets);
            game.setState(null);SoundController.getInstance().run(SoundOptions.MARIO_DEATH);
            Thread.sleep(4000);
            DeathMenu deathMenu = new DeathMenu(new String[]{"Retry", "Exit"}, gui, getModel().getPath_());
            deathMenu.run();
        }
        if (flagReached()){
            endGameWin(game);

        }
    }
}
