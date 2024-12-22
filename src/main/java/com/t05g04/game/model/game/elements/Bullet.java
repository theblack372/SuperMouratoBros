package com.t05g04.game.model.game.elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t05g04.game.model.game.Position;
import com.t05g04.game.model.game.arena.Map;

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

    public void move(Map map) {
        synchronized (this) {
            int nextX = getPosition().getX() + getVelocity_();
            int nextY = getPosition().getY();
            Position nextPosition = new Position(nextX, nextY); // Nova posição para teste de colisão

            if (map.canObjectMove(nextPosition)) {
                // Atualiza a posição do projétil
                getPosition().setPosition(nextPosition);
            } else {
                // Remove o projétil do mapa ao colidir
                map.getBullets().remove(this);
            }
        }
    }


}

