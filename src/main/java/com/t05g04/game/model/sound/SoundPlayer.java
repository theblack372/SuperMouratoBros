package com.t05g04.game.model.sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SoundPlayer {
    private Clip sound;

    public SoundPlayer(String filename){
        Path path = Paths.get(filename);
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(path.toFile());
            sound = AudioSystem.getClip();
            sound.open(inputStream);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void play(){
        if(sound.isRunning()){
            stop();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        sound.setFramePosition(0);
        sound.start();
    }

    public void playContinuously(){
        sound.setFramePosition(0);
        sound.start();
        sound.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        sound.stop();
    }

    public void resumePlaying(){
        sound.start();
        sound.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public boolean isPlaying(){
        return sound.isRunning();
    }

    public Clip getSound(){
        return sound;
    }

    public void setSound(Clip sound){
        this.sound = sound;
    }

}

