package com.t05g04.game.model.game.arena;
import com.t05g04.game.gui.GUI;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.model.game.elements.*;
import com.t05g04.game.model.game.Position;
import com.t05g04.game.model.menu.DeathMenu;
import com.t05g04.game.viewer.game.Renderer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
    private final List<Powerup> powerups = new ArrayList<>();
    Renderer renderer = new Renderer();

    public Map(int width, int height) {
        width_ = width;
        height_ = height;
        mourato = new Mourato(new Position(1, 14),false, false, 1, 4, 0);
    }

    public int getHeight_() {
        return height_;
    }
    public int getStartX_() {return startX_;}
    public int getWidth_() {
        return width_;
    }
    public List<Coin> getCoins() {
        return coins;
    }

    public List<Powerup> getPowerups() {return powerups;}

    public int flowerNo(){
        return flowers.size();
    }

    public int koopasNo(){
        return koopas.size();
    }

    public List<Koopa> getKoopas() {
        return koopas;
    }
    public List<Flower> getFlowers() {return flowers;}
    public Renderer getRenderer() {
        return renderer;
    }

    public void createCoin(Position position) {
        coins.add(new Coin(position));
    }

    public void createKoopa(Position position) {
        koopas.add(new Koopa(position, -1));
    }
    public void createFlower(Position position) {
        flowers.add(new Flower(position,true));
    }
    public void createPowerup(Position position) {powerups.add(new Powerup(position));}

    public void processKey(GUI.ACTION action) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        if (action== GUI.ACTION.UP) {
            if (!mourato.isJump_()) {
                mourato.setJump_(true);
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
        }
        if (action== GUI.ACTION.RIGHT) {

            if (isMouratoMiddle() && canMouratoMove(mourato.moveRight()) && getRenderer().getMap_().length-width_!=startX_) {
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
            }
            else{moveMourato(mourato.moveRight());
            checkAndFall(mourato);}

            retrieveCoins(mourato.getPosition());
            goSuperMourato(mourato.getPosition());
        }
    }


    public void retrieveCoins(Position position) {
        coins.removeIf(coin->coin.getPosition().equals(position));

    }

    public void goSuperMourato(Position position) {
        powerups.removeIf(powerup->powerup.getPosition().equals(position));
        mourato.setSuperMourato_(true);
    }

    private boolean canMouratoMove(Position position) {
        // Verificar se a posição está dentro dos limites
        if (position.getX() < 0 || position.getY() < 0 || position.getX() >= width_ || position.getY() >= height_) {
            return false; // Fora dos limites
        }

        // Verificar se a posição não colide com objetos
        char tile = renderer.getMap_()[position.getX()+renderer.getStart()][position.getY()];

        return tile!='#' && tile!='H' && tile!='!';
    }


    private void moveMourato(Position position) {
        if (canMouratoMove(position)) {
            mourato.getPosition().setPosition(position);
        }
    }
    public boolean isMouratoMiddle(){
        return mourato.getPosition().getX() == 16;
    }

    private boolean canKoopaMove(Position position) {
        return renderer.getMap_()[position.getX()+ renderer.getStart()][position.getY()] != '#';
    }

    public void KoopaMove(Koopa koopa) {
        if (koopa == null) {
            return;
        }
        synchronized (koopas) {
            int nextX = koopa.getPosition().getX() + koopa.getVelocity_();
            int nextY = koopa.getPosition().getY();
            Position nextPosition = new Position(nextX, nextY);
            if (canKoopaMove(nextPosition)) {
                koopa.move();
            } else {
                koopa.setVelocity_(-koopa.getVelocity_());
                koopa.move();
            }
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


    public Mourato getMourato() {
        return mourato;
    }


    public void updateJump(Mourato mourato) {
        if (!mourato.isJump_()) return;

        int velocity = mourato.getJumpVelocity_();
        int jumpHeight = mourato.getJumpHeight_();
        int jumpProgress = mourato.getCountJump_();
        if (jumpProgress < jumpHeight) { // verifica se o mourato ainda está em momento ascendente do salto
            boolean blockBroken = renderer.breakBlock(mourato);
            if (blockBroken) {        // se o bloco for partido, força o mourato ir para sentido contrário
                jumpProgress = jumpHeight;
                mourato.setCountJump_(jumpProgress);
            }

        }

        // Atualiza a posição para subir ou descer
        int newY = mourato.getPosition().getY() + ((jumpProgress < jumpHeight) ? -velocity : velocity);
        Position newPosition = new Position(mourato.getPosition().getX(), newY);

        if(newY>=height_){
            System.exit(0);
        }

        if (canMouratoMove(newPosition)) {
            mourato.getPosition().setPosition(newPosition);
            retrieveCoins(newPosition);
            goSuperMourato(newPosition);// Coleta moedas
            mourato.setCountJump_(jumpProgress + 1);
            if (jumpProgress >= jumpHeight) {
                destroyKoopaIfHit(mourato); // mata o koopa em caso de velocidade <0
            }
        } else {
            mourato.setJump_(false); // Termina o salto em caso de colisão
        }

        // Verifica se atingiu o chão
        if (!canMouratoMove(new Position(mourato.getPosition().getX(), mourato.getPosition().getY() + 1))) {
            mourato.setJump_(false); // Termina o salto ao atingir o chão
            mourato.setCountJump_(0); // Reseta o progresso
        }
    }
    public void checkAndFall(Mourato mourato) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        if (mourato.isJump_()) {
            return; // Se está no meio do salto, não aplica a lógica de queda
        }

        Position currentPosition = mourato.getPosition();
        int x = currentPosition.getX();
        int y = currentPosition.getY();
        if (y + 1 < height_ && canMouratoMove(new Position(x, y + 1))) {
            mourato.getPosition().setPosition(new Position(x, y + 1));
            retrieveCoins(mourato.getPosition());
            goSuperMourato(mourato.getPosition());
            destroyKoopaIfHit(mourato);
        }
        else if (y + 1 >= height_) {
            DeathMenu menu = new DeathMenu(new String[]{"Retry", "Exit"}, new LanternaGui(32, 18));
            menu.run();
        }
    }

    private void destroyKoopaIfHit (Mourato mourato){
        Position mouratoPosition = mourato.getPosition();
        synchronized (koopas) {
            for (Koopa koopa : koopas) {
                Position koopaPosition = koopa.getPosition();

                // Verifica se Mourato está na mesma posição ou imediatamente acima do Koopa
                if (mouratoPosition.getX() == koopaPosition.getX() && mouratoPosition.getY() == koopaPosition.getY() - 1) {
                    koopas.remove(koopa); // Remove o Koopa atingido
                    break; // Sai após destruir o Koopa
                }
            }
        }
    }

    public boolean flagReach() {
        Position currentPosition = mourato.getPosition();
        return renderer.getMap_()[currentPosition.getX()+startX_][currentPosition.getY()] == '|';
    }
}

