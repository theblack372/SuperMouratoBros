package com.t05g04.game.viewer;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t05g04.game.model.game.arena.Map;
import com.t05g04.game.model.game.elements.Coin;
import com.t05g04.game.model.game.elements.Koopa;
import com.t05g04.game.model.game.elements.Mourato;
import com.t05g04.game.model.game.Position;

public class Renderer {
    char[][] map_ = {
            ".........###".toCharArray(),
            ".........###".toCharArray(),
            ".........###".toCharArray(),
            "......H..###".toCharArray(),
            "...H..H..###".toCharArray(),
            "...H..H..###".toCharArray(),
            "......H..###".toCharArray(),
            ".........###".toCharArray(),
            ".........###".toCharArray(),
            "............".toCharArray(),
            "............".toCharArray(),
            ".........###".toCharArray(),
            ".........###".toCharArray(),
            ".........###".toCharArray(),
            ".........###".toCharArray(),
            ".........###".toCharArray(),
            ".........###".toCharArray(),
            ".........###".toCharArray(),
            ".......#####".toCharArray(),
            ".........###".toCharArray(),
            ".........###".toCharArray(),
            ".........###".toCharArray(),
            ".........###".toCharArray(),
            ".......#####".toCharArray(),
            ".........###".toCharArray(),
            ".........###".toCharArray(),
            ".........###".toCharArray(),
            ".........###".toCharArray(),
            ".........###".toCharArray(),
            ".........###".toCharArray(),
            ".........###".toCharArray(),
            "|||||||||###".toCharArray()

    };

    public char[][] getMap_() {
        return map_;
    }

    public void draw(TextGraphics graphics, Map map) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(map.getWidth_(), map.getHeight_()), ' ');
        for (int x = 0; x < map_.length; x++) {
            for (int y = 0; y < map_[x].length; y++) {
                char tile = map_[x][y];
                switch (tile) {
                    case '#': // Block
                        graphics.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
                        graphics.enableModifiers(SGR.BOLD);
                        graphics.putString(new TerminalPosition(x, y), "#");
                        break;
                    case 'H': // Question Block
                        graphics.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
                        graphics.enableModifiers(SGR.BOLD);
                        graphics.putString(new TerminalPosition(x, y), "H");
                        break;
                    case '|': // Flag
                        graphics.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
                        graphics.enableModifiers(SGR.BOLD);
                        graphics.putString(new TerminalPosition(x, y), "|");
                    default: // Empty space
                        break;
                }
            }
        }
        map.getMourato().draw(graphics);
        synchronized (map.getKoopas()) {
            for (Koopa koopa : map.getKoopas()) {
                koopa.draw(graphics);
            }
        }
        for (Coin coin : map.getCoins()) {
           coin.draw(graphics);
        }
    }

    public boolean breakBlock(Mourato mourato) {
        if(mourato.isJump_()) {
            if(mourato.getJumpVelocity_()>=0){// caso mourato esteja em salto em momento ascendente
                Position positionblock = new Position(mourato.getPosition().getX(), mourato.getPosition().getY()-1);
                if(map_[positionblock.getX()][positionblock.getY()] == 'H'){
                    map_[positionblock.getX()][positionblock.getY()]='.';//parte o bloco
                    mourato.setCountJump_(mourato.getJumpHeight_()); //mete o contador de salto no maximo para provocar momento descendente
                    return true;
                }
            }
        }
        return false;
    }
}
