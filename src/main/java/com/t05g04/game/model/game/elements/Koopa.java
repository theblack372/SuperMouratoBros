package com.t05g04.game.model.game.elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t05g04.game.model.game.Position;
import com.t05g04.game.model.game.map.Map;

public class Koopa extends Element implements Movable {
    private int velocity_;
    public Koopa(Position position, int velocity) {
        super(position);
        velocity_=velocity;
    }

    @Override
    public void draw(TextGraphics graphics, Position position, boolean moving) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#013220"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "K");
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

    @Override
    public void move(Map map) {
        synchronized (this) {
            int nextX = getPosition().getX() + getVelocity_();
            int nextY = getPosition().getY();
            Position nextPosition = new Position(nextX, nextY);

            if (map.canObjectMove(nextPosition)) {
                getPosition().setPosition(nextPosition);
            } else {
                setVelocity_(-getVelocity_());
                getPosition().setPosition(new Position(getPosition().getX() + getVelocity_(), nextY));
            }
        }
    }

}

