package com.t05g04.game.model.game.elements;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t05g04.game.model.game.Position;

public class Powerup extends Element {
    private boolean appearing_;
    private int index_;
    public Powerup(Position position,boolean appearing,int index) {
        super(position);appearing_=appearing;index_=index;
    }
    public boolean isAppearing() {
        return appearing_;
    }
    public void setAppearing(boolean appearing) {
        appearing_ = appearing;
    }
    public int getIndex() {
        return index_;
    }

    @Override
    public void draw(TextGraphics graphics, Position position, boolean moving) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "P");
    }
    @Override
    public void moveTerminal() {
        int newX = getPosition().getX() - 1;
        position_ = new Position(newX, getPosition().getY());
    }
}
