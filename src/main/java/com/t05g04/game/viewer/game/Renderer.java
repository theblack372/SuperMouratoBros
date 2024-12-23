package com.t05g04.game.viewer.game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t05g04.game.controller.sound.SoundController;
import com.t05g04.game.model.game.map.Map;
import com.t05g04.game.model.game.elements.*;
import com.t05g04.game.model.game.Position;
import com.t05g04.game.model.sound.SoundOptions;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static com.t05g04.game.Application.gui;
import static com.t05g04.game.viewer.game.MapLoader.loadMap;

public class Renderer {
    int start;
    boolean moving= false;
    int terminalStart=32;
    boolean objectsReceived = false;
    char[][] map_;
    String mapPath;

    public Renderer(String path){
        setMap_(loadMap(path));
        this.mapPath = path;
    }

    public int getStart() {
        return start;
    }

    public char[][] getMap_() {
        return map_;
    }
    public void setMap_(char[][] map_) {
        this.map_ = map_;
    }

    public void draw(TextGraphics graphics, Map map, int coins, int bullets) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        start = map.getStartX_();
        terminalStart=start+32;
        moving = map.isMouratoMiddle();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(map.getWidth_()*16, map.getHeight_()*16), ' ');
        gui.draw_curentCoins(coins);
        gui.draw_currentBullets(bullets);
        for (int x = start; x < terminalStart; x++) {
            for (int y = 0; y < map_[x].length; y++) {
                char tile = map_[x][y];
                Position position = new Position(x-start,y);
                switch (tile) {
                    case '#':
                        gui.draw_Wall(position);
                        break;
                    case 'H':
                        gui.draw_breakableWall(position);
                        break;
                    case '|':
                        gui.draw_flag();
                    // fall through
                    default:
                        break;
                }
            }
        }
        drawElements(graphics, map);
    }


    private void drawElements(TextGraphics graphics, Map map) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        map.getMourato().draw(graphics, map.getMourato().getPosition(), moving);
        map.checkAndFall(map.getMourato());
        synchronized (map.getKoopas()) {
            for (Koopa koopa : map.getKoopas()) {
                gui.draw_koopa1(koopa.getPosition());
            }
        }
        for (Coin coin : map.getCoins()) {
            gui.draw_coin(coin.getPosition());
        }

        for (Flower flower : map.getFlowers()) {
            if (flower.isAppearing()) {
                gui.draw_flower(flower.getPosition());
            }
        }
        for(Powerup powerup : map.getPowerups()) {
            if(powerup.isAppearing()) {
                gui.draw_powerUpFlower(powerup.getPosition());
            }
        }
        for(PowerUpBlock powerupblock: map.getPowerupBlocks()) {
            gui.draw_questionBlock(powerupblock.getPosition());        }
        for(Bullet bullet:map.getBullets()){
            gui.draw_fireBall1(bullet.getPosition());
        }
    }

    public boolean breakBlock(Mourato mourato) {
        if (mourato.isJump_()) {
            if (mourato.getJumpVelocity_() >= 0) {// caso mourato esteja em salto em momento ascendente
                Position positionBlock = new Position(mourato.getPosition().getX() + start, mourato.getPosition().getY() - 1);
                if (map_[positionBlock.getX()][positionBlock.getY()] == 'H') {
                    map_[positionBlock.getX()][positionBlock.getY()] = ' ';//parte o bloco
                    SoundController.getInstance().run(SoundOptions.BREAK_BLOCK);
                    mourato.setCountJump_(mourato.getJumpHeight_()); //mete o contador de salto no maximo para provocar momento descendente
                    return true;
                }
            }
        }
        return false;
    }

    public void renderObjects(Map map){
        for (int x = 0; x < map_.length; x++) {
            for (int y = 0; y < map_[x].length; y++) {
                char tile = map_[x][y];
                switch (tile) {
                    case 'C': // Block
                        map.createCoin(new Position(x, y));
                        break;
                    case 'K': // Question Block
                        map.createKoopa(new Position(x, y));
                        break;
                    case 'F': // Flag
                        map.createFlower(new Position(x, y));
                        break;
                    case 'P':
                        map.createPowerup(new Position(x, y));
                        break;
                    case '!':
                        map.createpowerupBlock(new Position(x,y));
                        break;
                    default: // Empty space
                        break;
                }
            }
        }
        objectsReceived = true;
    }

    public String getMapPath() {
        return mapPath;
    }
}