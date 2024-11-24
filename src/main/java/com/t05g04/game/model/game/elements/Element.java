package com.t05g04.game.model.game.elements;
import com.t05g04.game.model.game.elements.Position;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


public abstract class Element{
    protected Position position_;

    public Element(Position position) {
        position_ = position;
    }
    public Position getPosition() {
        return position_;
    }

    abstract void draw(TextGraphics graphics);
}
