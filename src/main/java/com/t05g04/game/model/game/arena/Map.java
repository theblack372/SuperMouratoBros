package com.t05g04.game.model.game.arena;
import com.t05g04.game.gui.GUI;
import com.t05g04.game.model.game.elements.Coin;
import com.t05g04.game.model.game.elements.Koopa;
import com.t05g04.game.model.game.elements.Mourato;
import com.t05g04.game.model.game.elements.Position;
import com.t05g04.game.viewer.Renderer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Map {
    int height_;
    int width_;
    private Mourato mourato;
    private List<Coin> coins;
    private CopyOnWriteArrayList<Koopa> koopas;
    Renderer renderer = new Renderer();
    public Map(int width, int height) {
        width_ = width;
        height_ = height;
        coins= createCoins();
        koopas = new CopyOnWriteArrayList<>(createKoopas());
        mourato = new Mourato(new Position(0, 8), false, 1, 4, 0);
    }

    public int getHeight_() {
        return height_;
    }

    public int getWidth_() {
        return width_;
    }
    public List<Coin> getCoins() {
        return coins;
    }
    public CopyOnWriteArrayList<Koopa> getKoopas() {
        return koopas;
    }
    public Renderer getRenderer() {
        return renderer;
    }

    private List<Coin> createCoins() {
        List<Coin> coins = new ArrayList<>();
        coins.add(new Coin(new Position(3, 8)));
        coins.add(new Coin(new Position(3, 5)));
        coins.add(new Coin(new Position(4, 5)));
        coins.add(new Coin(new Position(5, 5)));
        coins.add(new Coin(new Position(6, 5)));
        coins.add(new Coin(new Position(8, 8)));
        coins.add(new Coin(new Position(14, 8)));
        coins.add(new Coin(new Position(27, 8)));
        return coins;
    }

    private List<Koopa> createKoopas() {
        List<Koopa> koopas = new ArrayList<>();
        koopas.add(new Koopa(new Position(19, 8), 1));
        return koopas;
    }

    public void processKey(GUI.ACTION action) throws IOException {
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
        }
        if (action== GUI.ACTION.RIGHT) {
            moveMourato(mourato.moveRight());
            checkAndFall(mourato);
        }
    }


    public void retrieveCoins(Position position) {
        coins.removeIf(coin -> coin.getPosition().equals(position));
    }

    private boolean canMouratoMove(Position position) {
        // Verificar se a posição está dentro dos limites
        if (position.getX() < 0 || position.getY() < 0 || position.getX() >= width_ || position.getY() >= height_) {
            return false; // Fora dos limites
        }

        // Verificar se a posição não colide com objetos
        char tile = renderer.getMap_()[position.getX()][position.getY()];
        return tile!='#' && tile!='H';
    }


    private void moveMourato(Position position) {
        if (canMouratoMove(position)) {
            mourato.getPosition().setPosition(position);
            retrieveCoins(position);
        }
    }



    private boolean canKoopaMove(Position position) {
        return renderer.getMap_()[position.getX()][position.getY()] != '#';
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

    public Koopa getKoopa() {
        if (koopas != null && !koopas.isEmpty()) {
            return koopas.get(0); // Retorna o primeiro elemento da lista
        }
        return null; // Retorna null caso não haja Koopas
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
            retrieveCoins(newPosition); // Coleta moedas
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
    private void checkAndFall(Mourato mourato) {
        if (mourato.isJump_()) {
            return; // Se está no meio do salto, não aplica a lógica de queda
        }

        Position currentPosition = mourato.getPosition();
        int x = currentPosition.getX();
        int y = currentPosition.getY();
        while (y + 1 < height_ && canMouratoMove(new Position(x, y + 1))) {
            mourato.getPosition().setPosition(new Position(x, y + 1));
            y++;
            destroyKoopaIfHit(mourato);
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
        return renderer.getMap_()[currentPosition.getX()][currentPosition.getY()] == '|';
    }
}

