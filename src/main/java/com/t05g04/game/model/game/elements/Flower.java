package com.t05g04.game.model.game.elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t05g04.game.model.game.Position;

public class Flower extends Element {
    private boolean appearing_;
    public Flower(Position position,boolean appearing) {
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
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#868B49"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position_.getX(), position_.getY()), "F");
    }
}
