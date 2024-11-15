import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.run();
    }
}

class Game{
    private boolean endTerminal=false;
    private Screen screen;
    private final Map map= new Map(32,12);
    public Game() {
        try {TerminalSize terminalSize = new TerminalSize(map.getWidth_(), map.getHeight_());
            DefaultTerminalFactory terminalFactory = new
                    DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();

            screen = new TerminalScreen(terminal);
            screen.startScreen(); // screens must be started
            screen.setCursorPosition(null); // we don't need a cursor
            screen.doResizeIfNecessary(); // resize screen if
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    void run() throws IOException {
        while (!endTerminal) {
            draw();
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.Character && key.getCharacter()== 'q') {
                screen.stopScreen();
                endTerminal = true;
            }
            draw();
        }
    }

    private void draw() throws IOException {
        screen.clear();
        map.draw(screen.newTextGraphics());
        screen.refresh();
    }
}

class Map {
    int height_;
    int width_;
    Map(int width, int height) {
        width_ = width;
        height_ = height;
    }

    public int getHeight_() {
        return height_;
    }
    public int getWidth_() {
        return width_;
    }

    char[][] map = {
            ".........###".toCharArray(),
            ".........###".toCharArray(),
            ".........###".toCharArray(),
            "......#..###".toCharArray(),
            "...#..#..###".toCharArray(),
            "...#..#..###".toCharArray(),
            "......#..###".toCharArray(),
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


    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width_, height_), ' ');

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                char tile = map[x][y];
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
    }
}