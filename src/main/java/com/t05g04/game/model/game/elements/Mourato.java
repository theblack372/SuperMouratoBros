package com.t05g04.game.model.game.elements;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t05g04.game.model.game.Position;


public class Mourato extends Element{
    private boolean jump_;
    private int jumpVelocity_;
    private final int jumpHeight_;
    private int countJump_;
    public Mourato(Position position, boolean jump, int jumpVelocity, int jumpHeight, int countJump) {
        super(position);
        jump_ = jump;
        jumpVelocity_ = jumpVelocity;
        jumpHeight_ = jumpHeight;
        countJump_ = countJump;
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

    public void setJump_(boolean jump) {
        jump_ = jump;
    }

    @Override
    public void draw(TextGraphics graphics, Position position, boolean moving) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(),
                position_.getY()), "M");
    }

    @Override
    void moveTerminal() {
    }
}