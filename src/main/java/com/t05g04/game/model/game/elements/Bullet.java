package com.t05g04.game.model.game.elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t05g04.game.model.game.Position;

public class Bullet extends Element {
    private int velocity_;
    private boolean direction_;
    public Bullet(Position position, int velocity,boolean direction) {
        super(position);
        velocity_=velocity;
        direction_=direction;
    }

    @Override
    public void draw(TextGraphics graphics, Position position, boolean moving) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF6400"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "-");
    }

    @Override
    public void moveTerminal() {
        int newX = getPosition().getX() - 1;
        position_ = new Position(newX, getPosition().getY());
    }

    public void move() {
        int newX = getPosition().getX() + velocity_;
        position_ =new Position(newX, getPosition().getY());
    }

    public int getVelocity_() {
        return velocity_;
    }
    public void setVelocity_(int velocity) {
        velocity_ = velocity;
    }
    public void setDirection_(boolean direction) {
        direction_ = direction;
    }
}

