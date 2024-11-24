package com.t05g04.game.model.game.elements;

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
        if (getClass() != o.getClass()) return false;
        Position p = (Position) o;
        return x_ == p.getX() && y_ == p.getY();
    }
    public void setPosition(Position position) {
        x_=position.getX();
        y_=position.getY();
    }
}
