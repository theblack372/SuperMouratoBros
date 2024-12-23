package com.t05g04.game.model.game.elements;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t05g04.game.controller.sound.SoundController;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.model.game.Position;
import com.t05g04.game.model.game.map.Map;
import com.t05g04.game.model.menu.DeathMenu;
import com.t05g04.game.model.sound.SoundOptions;
import com.t05g04.game.states.game.MouratoState;


import static com.t05g04.game.Application.gui;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Mourato extends Element{
    private boolean jump_;
    private boolean superMourato_;
    private int jumpVelocity_;
    private final int jumpHeight_;
    private int countJump_;
    private int countBullets_;
    public MouratoState state = new MouratoState();

    public Mourato(Position position, boolean jump,boolean superMourato, int jumpVelocity, int jumpHeight, int countJump, int countBullets) {
        super(position);
        jump_ = jump;
        superMourato_ = superMourato;
        jumpVelocity_ = jumpVelocity;
        jumpHeight_ = jumpHeight;
        countJump_ = countJump;
        countBullets_ = countBullets;
    }

    public int getJumpVelocity_() {
        return jumpVelocity_;
    }

    public int getJumpHeight_() {
        return jumpHeight_;
    }
    public int getCountJump_() {
        return countJump_;
    }
    public void setCountJump_(int countJump) {
        countJump_ = countJump;
    }
    public void setJumpVelocity_(int jumpVelocity) {
        jumpVelocity_ = jumpVelocity;
    }
    public void setPosition_(Position position) {
            position_ = position;
    }

    public Position moveDown(){
        return new Position(position_.getX(), position_.getY()+1);
    }
    public Position moveLeft(){
        state.isRunningToLeft();
        return new Position(position_.getX()-1, position_.getY());
    }
    public Position moveRight(){
        state.isRunningToRight();
        return new Position(position_.getX()+1, position_.getY());
    }
    public boolean isJump_() {
        return jump_;
    }

    public boolean isSuperMourato_() {
        return superMourato_;
    }

    public void setSuperMourato_(boolean superMourato) {superMourato_ = superMourato;}
    public void setJump_(boolean jump) {
        jump_ = jump;
    }
    public int getCountBullets_() {
        return countBullets_;
    }
    public void setCountBullets_(int countBullets_) {
        this.countBullets_ = countBullets_;
    }
    @Override
    public void draw(TextGraphics graphics, Position position, boolean moving) throws IOException {
        switch (state.getState()) {
            case IDLE_LOOKING_LEFT:
                System.out.println("IDLE_LOOKING_LEFT");
                gui.draw_mourato_Lidle(position);
                break;
            case IDLE_LOOKING_RIGHT:
                System.out.println("IDLE_LOOKING_RIGHT");
                gui.draw_mourato_idle(position);
                break;
            case RUNNING_TO_LEFT:

                if (state.getAnimationStep() == 1) {
                    System.out.println("RUNNING_TO_LEFT1");
                    gui.draw_mourato_Lrun2(position);
                    state.stepAnimation();
                }
                else {
                    System.out.println("RUNNING_TO_LEFT2");
                    gui.draw_mourato_Lrun1(position);
                    state.stepAnimation();
                }
                break;
            case RUNNING_TO_RIGHT:
                if (state.getAnimationStep() == 1){
                    System.out.println("RUNNING_TO_RIGHT1");
                    gui.draw_mourato_run2(position);
                    state.stepAnimation();
                }
                else {
                    System.out.println("RUNNING_TO_RIGHT2");
                    gui.draw_mourato_run1(position);
                    state.stepAnimation();
                }
                break;
            case JUMPING_FROM_LEFT:
                System.out.println("JUMPING_FROM_LEFT");
                gui.draw_mourato_Ljump(position);
                break;
            case JUMPING_FROM_RIGHT:
                System.out.println("JUMPING_FROM_RIGHT");
                gui.draw_mourato_jump(position);
                break;
        }
    }

    @Override
    void moveTerminal() {
    }

    public void updateJump(Map map) {
        if (!isJump_()) return;

        if(state.isIdleLookingLeft()){
            state.isJumpingFromLeft();
        }

        if(state.isIdleLookingRight()){
            state.isJumpingFromRight();
        }

        int jumpProgress = getCountJump_();

        if (jumpProgress < getJumpHeight_()) {
            boolean blockBroken = map.getRenderer().breakBlock(this);
            if (blockBroken) {
                setCountJump_(0);
                setJump_(false);
                return;
            }
        }

        int newY = getPosition().getY() + ((jumpProgress < getJumpHeight_()) ? -getJumpVelocity_() : getJumpVelocity_());
        Position newPosition = new Position(getPosition().getX(), newY);

        if (newY >= map.getHeight_()) {
            System.exit(0);
        }

        if (map.canObjectMove(newPosition)) {
            getPosition().setPosition(newPosition);
            map.retrieveCoins(newPosition);
            map.goSuperMourato(newPosition);
            setCountJump_(jumpProgress + 1);

            if (jumpProgress >= getJumpHeight_()) {
                map.destroyKoopaIfHit(this);
            }
        } else {
            setJump_(false);
            setCountJump_(0);
        }

        if (!map.canObjectMove(new Position(getPosition().getX(), getPosition().getY() + 1))) {
            setJump_(false);
            setCountJump_(0);
        }
    }

    public boolean checkAndFall(Map map) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        if (isJump_()) return false;

        Position currentPosition = getPosition();
        int x = currentPosition.getX();
        int y = currentPosition.getY();

        if (y + 1 < map.getHeight_() && map.canObjectMove(new Position(x, y + 1))) {
            getPosition().setPosition(new Position(x, y + 1));
            map.retrieveCoins(getPosition());
            map.goSuperMourato(getPosition());
            map.destroyKoopaIfHit(this);
            return true;
        } else if (y + 1 >= map.getHeight_()) {
            DeathMenu menu = new DeathMenu(new String[]{"Retry", "Exit"}, new LanternaGui(32, 18), map.getRenderer().getMapPath());
            menu.run();
        }
        return false;
    }
    public void destroyKoopaIfHit(Map map) {
        Position mouratoPosition = getPosition();
        synchronized (map.getKoopas()) {
            for (Koopa koopa : map.getKoopas()) {
                Position koopaPosition = koopa.getPosition();
                if (mouratoPosition.getX() == koopaPosition.getX() && mouratoPosition.getY() == koopaPosition.getY() - 1) {
                    map.getKoopas().remove(koopa);
                    break;
                }
            }
        }
    }
    public void shootBullet(Map map) {
        if (isSuperMourato_() && getCountBullets_() > 0) {
            Position bulletPosition = new Position(getPosition().getX(), getPosition().getY());
            map.createBullet(bulletPosition); // Usa o Map para criar a bala
            setCountBullets_(getCountBullets_() - 1);
            SoundController.getInstance().run(SoundOptions.FIREBALL);
        } else {
            setSuperMourato_(false);
        }
    }
}