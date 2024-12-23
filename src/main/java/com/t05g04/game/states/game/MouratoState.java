package com.t05g04.game.states.game;


public class MouratoState extends MovableState {
    private State state;
    private int animationStep;

    public enum State {
        JUMPING_FROM_LEFT,
        JUMPING_FROM_RIGHT,
        RUNNING_TO_LEFT,
        RUNNING_TO_RIGHT,
        IDLE_LOOKING_LEFT,
        IDLE_LOOKING_RIGHT
    }

    public MouratoState() {
        this.state = State.IDLE_LOOKING_RIGHT;
        this.animationStep = 1;
    }

    public boolean isJumpingFromLeft() {
        return (state == State.JUMPING_FROM_LEFT);
    }

    public void setJumpingFromLeft(boolean jumpingFromLeft) {
        state = State.JUMPING_FROM_LEFT;
    }

    public boolean isJumpingFromRight() {
        return (state == State.JUMPING_FROM_RIGHT);
    }

    public void setJumpingFromRight(boolean jumpingFromRight) {
        state = State.JUMPING_FROM_RIGHT;
    }

    public boolean isRunningToLeft() {
        return (state == State.RUNNING_TO_LEFT);
    }

    public void setRunningToLeft(boolean runningToLeft) {
        state = State.RUNNING_TO_LEFT;
    }

    public boolean isRunningToRight() {
        return (state == State.RUNNING_TO_RIGHT);
    }

    public void setRunningToRight(boolean runningToRight) {
        state = State.RUNNING_TO_RIGHT;
    }
    public boolean isIdleLookingLeft() {
        return (state == State.IDLE_LOOKING_LEFT);
    }

    public void setIdleLookingLeft(boolean idleLookingLeft) {
        state = State.IDLE_LOOKING_LEFT;
    }

    public boolean isIdleLookingRight() {
        return (state == State.IDLE_LOOKING_RIGHT);
    }

    public void setIdleLookingRight(boolean idleLookingRight) {
        state = State.IDLE_LOOKING_RIGHT;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getAnimationStep() {
        return animationStep;
    }

    public void stepAnimation() {
        if (this.animationStep == 1) {
            this.animationStep = 2;
        } else {
            this.animationStep = 1;
        }
    }
}