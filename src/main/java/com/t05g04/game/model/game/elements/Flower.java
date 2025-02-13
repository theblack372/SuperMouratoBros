package com.t05g04.game.model.game.elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t05g04.game.model.game.Position;

public class Flower extends Element{
    private boolean appearing_;

    public Flower(Position position, boolean appearing) {
        super(position);
        appearing_ = appearing;
    }

    public boolean isAppearing() {
        return appearing_;
    }

    public void setAppearing(boolean appearing) {
        appearing_ = appearing;
    }

    @Override
    public void draw(TextGraphics graphics, Position position, boolean moving) {
        graphics.setForegroundColor(TextColor.ANSI.GREEN);
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "F");
    }

    @Override
    public void moveTerminal() {
        int newX = getPosition().getX() - 1;
        position_ = new Position(newX, getPosition().getY());
    }
}
