package com.t05g04.game.viewer;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t05g04.game.model.game.arena.Map;
import com.t05g04.game.model.game.elements.Coin;
import com.t05g04.game.model.game.elements.Flower;
import com.t05g04.game.model.game.elements.Koopa;
import com.t05g04.game.model.game.elements.Mourato;
import com.t05g04.game.model.game.Position;

public class Renderer {
    int start;
    boolean moving= false;
    int terminalStart=32;

    public int getStart() {
        return start;
    }

    char[][] map_ = {
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "            H  ###".toCharArray(),
            "         H  H  ###".toCharArray(),
            "         H  H  ###".toCharArray(),
            "            H  ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "                  ".toCharArray(),
            "            H     ".toCharArray(),
            "            H     ".toCharArray(),
            "            H     ".toCharArray(),
            "            H     ".toCharArray(),
            "                  ".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "             #####".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),    // koopa will be there
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "             #####".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "         ?     ###".toCharArray(),
            "         ?     ###".toCharArray(),
            "         ?     ###".toCharArray(),
            "         ?     ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "              ####".toCharArray(),   // flower will be on top of this
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "              ####".toCharArray(),
            "             #####".toCharArray(),
            "            ######".toCharArray(),
            "           #######".toCharArray(),
            "          ########".toCharArray(),
            "         #########".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(), // koopa
            "               ###".toCharArray(),
            "            ######".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "              ####".toCharArray(),
            "             #####".toCharArray(),
            "            ######".toCharArray(),
            "           #######".toCharArray(),
            "          ########".toCharArray(),
            "         #########".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "               ###".toCharArray(),
            "|||||||||||||||###".toCharArray()
    };

    public char[][] getMap_() {
        return map_;
    }

    public void draw(TextGraphics graphics, Map map) {
        start = map.getStartX_();
        terminalStart=start+32;
        moving= map.isMouratoMiddle();
        if(start+32>map_.length){}
        System.out.println(start+32);
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
                    case 'H': // Question Block
                        graphics.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
                        graphics.enableModifiers(SGR.BOLD);
                        graphics.putString(new TerminalPosition(x - start, y), "H");
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
        //System.out.println(moving);

    }


    private void drawElements(TextGraphics graphics, Map map) {
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
            if (map.getFlower().isAppearing()) {
                flower.draw(graphics, flower.getPosition(), moving);
            }
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
}