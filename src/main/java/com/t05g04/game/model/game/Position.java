package com.t05g04.game.model.game;

import java.util.Objects;

public class Position {
    private int x_;
    private int y_;
    public Position(int x, int y) {
        x_=x;
        y_=y;
    }
    public int getX(){
        return x_;
    }
    public int getY(){
        return y_;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Position)) return false;
        Position p = (Position) o;
        return x_ == p.getX() && y_ == p.getY();
    }
    @Override
    public int hashCode() {
        return Objects.hash(x_, y_);
    }
    public void setPosition(Position position) {
        x_=position.getX();
        y_=position.getY();
    }
}
