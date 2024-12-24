package com.t05g04.game.states.game;

public class KoopaState extends MovableState{
    private int animationStep = 0;

    public KoopaState() {
    }

    public int getAnimationStep() {
        return animationStep;
    }


    public void stepAnimation() {
        if (animationStep == 0) {
            animationStep = 1;
        } else {
            animationStep = 0;
        }
    }


}
