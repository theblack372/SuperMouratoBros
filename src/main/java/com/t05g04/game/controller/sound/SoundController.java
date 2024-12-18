package com.t05g04.game.controller.sound;

import com.t05g04.game.model.sound.SoundOptions;
import com.t05g04.game.model.sound.SoundPlayer;


public class SoundController {
    private static SoundController soundController;
    private final SoundPlayer coin;
    private final SoundPlayer music;
    private final SoundPlayer deathSound;
    private final SoundPlayer winSound;
    private final SoundPlayer powerupSound;
    private final SoundPlayer breakSound;
    private final SoundPlayer jumpSound;
    private final SoundPlayer fireballSound;

    public SoundController() {
        this.music = new SoundPlayer("src/main/resources/sounds/SuperMarioBros.wav");
        this.deathSound = new SoundPlayer("src/main/resources/sounds/smb_mariodie.wav");
        this.breakSound = new SoundPlayer("src/main/resources/sounds/smb_breakblock.wav");
        this.fireballSound = new SoundPlayer("src/main/resources/sounds/smb_fireball.wav");
        this.jumpSound = new SoundPlayer("src/main/resources/sounds/smb_jump-small.wav");
        this.powerupSound = new SoundPlayer("src/main/resources/sounds/smb_powerup.wav");
        this.winSound = new SoundPlayer("src/main/resources/sounds/smb_stage_clear.wav");
        this.coin = new SoundPlayer("src/main/resources/sounds/smb_coin.wav");
    }

    public static SoundController getInstance() {
        if (soundController==null){
            soundController=new SoundController();
        }
    return soundController;
    }

    public void playSound(SoundOptions soundName) {
        switch (soundName){
            case MUSIC:
                music.playContinuously();
                break;
            case JUMP:
                jumpSound.play();
                break;
            case FIREBALL:
                fireballSound.play();
                break;
            case POWER_UP:
                powerupSound.play();
                break;
            case BREAK_BLOCK:
                breakSound.play();
                break;
            case MARIO_DEATH:
                deathSound.play();
                break;
            case STAGE_CLEAR:
                winSound.play();
                break;
            case COIN:
                coin.play();
                break;
            default:
                break;
        }
    }
    public void silence(){
        music.stop();
        deathSound.stop();
        breakSound.stop();
        fireballSound.stop();
        powerupSound.stop();
        winSound.stop();
        jumpSound.stop();
        coin.stop();
    }
}
