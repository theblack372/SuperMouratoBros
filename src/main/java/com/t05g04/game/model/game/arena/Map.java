package com.t05g04.game.model.game.arena;
import com.t05g04.game.controller.sound.SoundController;
import com.t05g04.game.gui.GUI;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.model.game.elements.*;
import com.t05g04.game.model.game.Position;
import com.t05g04.game.model.menu.DeathMenu;
import com.t05g04.game.model.sound.SoundOptions;
import com.t05g04.game.viewer.game.Renderer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Map {
    int height_;
    int width_;
    int startX_=0;
    private final Mourato mourato;
    private final List<Coin> coins = new ArrayList<>();
    private final List<Koopa> koopas = new ArrayList<>();
    private final List<Flower> flowers = new ArrayList<>();
    private List<Powerup> powerups = new CopyOnWriteArrayList<>();
    private final List<PowerUpBlock> powerupBlocks = new ArrayList<>();
    Renderer renderer;
    LanternaGui gui;
    private final List<Bullet> bullets = new CopyOnWriteArrayList<>();

    public Map(int width, int height, String path, LanternaGui gui) {
        width_ = width;
        height_ = height;
        renderer = new Renderer(path);
        mourato = new Mourato(new Position(1, 14),false, false, 1, 4, 0,0);
        this.gui = gui;
    }

    public int getHeight_() {return height_;}
    public int getStartX_() {return startX_;}
    public int getWidth_() {return width_;}

    public List<Coin> getCoins() {return coins;}
    public List<Powerup> getPowerups() {return powerups;}
    public List<PowerUpBlock> getPowerupBlocks() {return powerupBlocks;}
    public List<Koopa> getKoopas() {return koopas;}
    public List<Flower> getFlowers() {return flowers;}
    public List<Bullet> getBullets() {return bullets;}
    public Renderer getRenderer() {return renderer;}

    public int flowerNo(){return flowers.size();}
    public int koopasNo(){return koopas.size();}
    public int powerupBlocksNo(){return powerupBlocks.size();}

    public void createCoin(Position position) {coins.add(new Coin(position));}
    public void createKoopa(Position position) {koopas.add(new Koopa(position, -1));}
    public void createFlower(Position position) {flowers.add(new Flower(position,true));}
    public void createPowerup(Position position) {powerups.add(new Powerup(position,false,powerups.size()));}
    public void createpowerupBlock(Position position) {powerupBlocks.add(new PowerUpBlock(position,false,powerupBlocks.size()));}
    public void createBullet(Position position) {bullets.add(new Bullet(position,0,true));}

    public void processKey(GUI.ACTION action) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        if (action== GUI.ACTION.UP) {
            if(!checkAndFall(mourato)) {
                if (!mourato.isJump_()) {
                    mourato.setJump_(true);
                    SoundController.getInstance().playSound(SoundOptions.JUMP);
                }
            }
        }
        if (action== GUI.ACTION.DOWN) {
            moveMourato(mourato.moveDown());
        }
        if (action== GUI.ACTION.LEFT) {
            moveMourato(mourato.moveLeft());
            checkAndFall(mourato);
            retrieveCoins(mourato.getPosition());
            goSuperMourato(mourato.getPosition());
            for(Bullet bullet: bullets) {
                if(bullet.getVelocity_()==0) {
                    bullet.setVelocity_(-1);
                    bullet.setDirection_(false);
                }
            }
        }
        if (action== GUI.ACTION.RIGHT) {
            if (isMouratoMiddle() && canObjectMove(mourato.moveRight()) && getRenderer().getMap_().length-width_!=startX_) {
                startX_++;
                for(Koopa koopa : koopas){
                    koopa.moveTerminal();
                }
                for(Coin coin : coins) {
                    coin.moveTerminal();
                }
                for(Flower flower : flowers) {
                    flower.moveTerminal();
                }
                for(Powerup powerup : powerups){
                    powerup.moveTerminal();
                }
                for(PowerUpBlock powerupblock :powerupBlocks){
                    powerupblock.moveTerminal();
                }
                for(Bullet bullet : bullets){
                    bullet.moveTerminal();
                    if(bullet.getVelocity_()==0) {
                        bullet.setVelocity_(1);
                        bullet.setDirection_(true);
                    }
                }
            }
            else{moveMourato(mourato.moveRight());
            checkAndFall(mourato);
                for(Bullet bullet : bullets){
                    if(bullet.getVelocity_()==0) {
                        bullet.setVelocity_(1);
                        bullet.setDirection_(true);
                    }
                }
            }

            retrieveCoins(mourato.getPosition());
            goSuperMourato(mourato.getPosition());
        }
        if(action==GUI.ACTION.SHOOT) {
            mourato.shootBullet(this);
        }
    }


    public void retrieveCoins(Position position) {
        if (coinTaken(position)){
            SoundController.getInstance().playSound(SoundOptions.COIN);
        }
        coins.removeIf(coin -> coin.getPosition().equals(position));

    }
    private boolean coinTaken(Position position) {
        for (Coin coin : coins) {
            if (coin.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    public void goSuperMourato(Position position) {
        for(Powerup powerup:powerups) {
            if (powerup.getPosition().equals(position)&&powerup.isAppearing()) {
                mourato.setSuperMourato_(true);
                SoundController.getInstance().playSound(SoundOptions.POWER_UP);
                powerups.remove(powerup);
                mourato.setCountBullets_(mourato.getCountBullets_()+5);
            }
        }
    }

    public boolean canObjectMove(Position position) {
        if (position.getX() < 0 || position.getY() < 0 || position.getX() >= width_ || position.getY() >= height_) {
            return false; // Fora dos limites
        }
        char tile = renderer.getMap_()[position.getX()+renderer.getStart()][position.getY()];
        return tile!='#' && tile!='H' && tile!='!';
    }

    private void moveMourato(Position position) {
        if (canObjectMove(position)) {
            mourato.getPosition().setPosition(position);
        }
    }
    public boolean isMouratoMiddle(){
        return mourato.getPosition().getX() == 16;
    }

    public void KoopaMove(Koopa koopa) {
        if (koopa != null) {
            koopa.move(this);
        }
    }

    public void BulletMove(Bullet bullet) {
        if (bullet != null) {
            bullet.move(this);
        }
    }

    public Koopa getKoopa(int i) {
        if (!koopas.isEmpty()) {
            return koopas.get(i); // Retorna o primeiro elemento da lista
        }
        return null; // Retorna null caso não haja Koopas
    }
    public Flower getFlower(int i) {
        if (!flowers.isEmpty()) {
            return flowers.get(i);
        }
        return null;
    }
    public Bullet getBullet(int i) {
        if (!bullets.isEmpty()) {
            return bullets.get(i); // Retorna o primeiro elemento da lista
        }
        return null; // Retorna null caso não haja Koopas
    }

    public Mourato getMourato() {return mourato;}

    public void updateJump(Mourato mourato) {mourato.updateJump(this);}
    public boolean checkAndFall(Mourato mourato) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        return mourato.checkAndFall(this);}
    public void destroyKoopaIfHit(Mourato mourato) {mourato.destroyKoopaIfHit(this);}

    public void headshot(){
        for(Bullet bullet : bullets) {
            Position bulletPosition = bullet.getPosition();
            synchronized (koopas) {
                for (Koopa koopa : koopas) {
                    Position koopaPosition = koopa.getPosition();

                    // Verifica se Mourato está na mesma posição ou imediatamente acima do Koopa
                    if ((bulletPosition.getX() == koopaPosition.getX() + 1 || bulletPosition.getX() == koopaPosition.getX() - 1) && bulletPosition.getY() == koopaPosition.getY()) {
                        koopas.remove(koopa);// Remove o Koopa atingido
                        bullets.remove(bullet);
                        break; // Sai após destruir o Koopa
                    }
                }
            }
        }
    }

    public void makePowerup() {
        if (mourato.isJump_()) {
            if (mourato.getJumpVelocity_() >= 0) {
                for(PowerUpBlock powerUpBlock:powerupBlocks) {
                    Position positionBlock = new Position(mourato.getPosition().getX(), mourato.getPosition().getY() - 1);
                    if (powerUpBlock.getPosition().equals(positionBlock)&& !powerUpBlock.isChecked()) {
                        for(Powerup powerup:powerups) {
                            if(powerup.getIndex()==powerUpBlock.getIndex()) {
                                powerup.setAppearing(true);
                                powerUpBlock.setChecked(true);
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean flagReach() {
        Position currentPosition = mourato.getPosition();
        return renderer.getMap_()[currentPosition.getX()+startX_][currentPosition.getY()] == '|';
    }
}