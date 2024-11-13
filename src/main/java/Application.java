import com.googlecode.lanterna.TerminalSize;
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

    public Game() {
        try {
            // Set a fixed terminal size
            TerminalSize fixedSize = new TerminalSize(80, 24); // for example, 80 columns by 24 rows
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(fixedSize);

            screen = terminalFactory.createScreen();
            screen.startScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void run() throws IOException {
        while (!endTerminal) {
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.Character && key.getCharacter()== 'q') {
                screen.stopScreen();
                endTerminal = true;
            }
        }
    }
}