package com.t05g04.game.model.game.elements;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t05g04.game.model.game.Position;

import java.io.IOException;

import static com.t05g04.game.Application.gui;


public class Mourato extends Element{
    private boolean jump_;
    private boolean superMourato_;
    private int jumpVelocity_;
    private final int jumpHeight_;
    private int countJump_;
    private int countBullets_;
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

    public Position moveDown(){
        return new Position(position_.getX(), position_.getY()+1);
    }
    public Position moveLeft(){
        return new Position(position_.getX()-1, position_.getY());
    }
    public Position moveRight(){
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
        gui.draw_mourato_run1(position);
    }

    @Override
    void moveTerminal() {
    }
}