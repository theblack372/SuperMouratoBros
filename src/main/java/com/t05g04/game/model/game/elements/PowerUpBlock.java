package com.t05g04.game.model.game.elements;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t05g04.game.model.game.Position;
public class PowerUpBlock extends Element {
    private boolean checked_;
    public PowerUpBlock(Position position,boolean checked) {
    super(position);
    checked_ = checked;
    }
    public boolean isChecked() {
        return checked_;
    }
    public void setChecked(boolean checked) {
        checked_ = checked;
    }
    @Override
    public void draw(TextGraphics graphics, Position position, boolean moving) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "!");
    }

    @Override
    public void moveTerminal() {
        int newX = getPosition().getX() - 1;
        position_ = new Position(newX, getPosition().getY());
    }
}
