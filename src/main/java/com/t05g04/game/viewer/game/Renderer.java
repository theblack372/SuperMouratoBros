package com.t05g04.game.viewer.game;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t05g04.game.model.game.arena.Map;
import com.t05g04.game.model.game.elements.*;
import com.t05g04.game.model.game.Position;
import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static com.t05g04.game.viewer.game.MapLoader.loadMap;

public class Renderer {
    int start;
    boolean moving= false;
    int terminalStart=32;
    boolean objectsReceived = false;
    char[][] map_ = loadMap("maps/map1.txt");
    public int getStart() {
        return start;
    }
    public char[][] getMap_() {
        return map_;
    }

    public void draw(TextGraphics graphics, Map map) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        System.out.println(map_.length);
        start = map.getStartX_();
        terminalStart=start+32;
        moving= map.isMouratoMiddle();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(map.getWidth_(), map.getHeight_()), ' ');
        for (int x = start; x < terminalStart; x++) {
            for (int y = 0; y < map_[x].length; y++) {
                char tile = map_[x][y];
                switch (tile) {
                    case '#': // Block
                        graphics.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
                        graphics.enableModifiers(SGR.BOLD);
                        graphics.putString(new TerminalPosition(x - start, y), "#");
                        break;
                    case 'H': // breakable block
                        graphics.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
                        graphics.enableModifiers(SGR.BOLD);
                        graphics.putString(new TerminalPosition(x - start, y), "H");
                        break;
                    case '!': //question block
                        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
                        graphics.enableModifiers(SGR.BOLD);
                        graphics.putString(new TerminalPosition(x - start, y), "!");
                        break;
                    case '|': // Flag
                        graphics.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
                        graphics.enableModifiers(SGR.BOLD);
                        graphics.putString(new TerminalPosition(x - start, y), "|");
                    default: // Empty space
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
                koopa.draw(graphics, koopa.getPosition(), moving);
            }
        }
        for (Coin coin : map.getCoins()) {
            coin.draw(graphics, coin.getPosition(), moving);
        }

        for (Flower flower : map.getFlowers()) {
            if (flower.isAppearing()) {
                flower.draw(graphics, flower.getPosition(), moving);
            }
        }
        for(Powerup powerup : map.getPowerups()) {
            powerup.draw(graphics,powerup.getPosition(), moving);
        }
    }

    public boolean breakBlock(Mourato mourato) {
        if (mourato.isJump_()) {
            if (mourato.getJumpVelocity_() >= 0) {// caso mourato esteja em salto em momento ascendente
                Position positionBlock = new Position(mourato.getPosition().getX() + start, mourato.getPosition().getY() - 1);
                if (map_[positionBlock.getX()][positionBlock.getY()] == 'H') {
                    map_[positionBlock.getX()][positionBlock.getY()] = '.';//parte o bloco
                    mourato.setCountJump_(mourato.getJumpHeight_()); //mete o contador de salto no maximo para provocar momento descendente
                    return true;
                }
            }
        }
        return false;
    }
    public boolean makePowerup(Mourato mourato,Map map) {
        if (mourato.isJump_()) {
            if (mourato.getJumpVelocity_() >= 0) {
                Position positionBlock = new Position(mourato.getPosition().getX() + start, mourato.getPosition().getY() - 1);
                if(map_[positionBlock.getX()][positionBlock.getY()] == '!') {
                    map.createPowerup(new Position(positionBlock.getX(), positionBlock.getY()-1));
                    mourato.setCountJump_(mourato.getJumpHeight_());
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
                    default: // Empty space
                        break;
                }
            }
        }
        objectsReceived = true;
    }
}