package com.t05g04.game.gui;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;


import java.io.IOException;

public class LanternaGui implements GUI{
    private Screen screen;

    public LanternaGui(Screen screen) {
        this.screen = screen;
    }

    public Screen getScreen() {
        return screen;
    }

    public LanternaGui(int width, int height) throws IOException {
        Terminal terminal = createTerminal(width, height);
        this.screen = createScreen(terminal) ;
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        Screen screen;
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        return screen;
    }

    public Terminal createTerminal(int width, int height) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new
                DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        Terminal terminal;
        terminal = terminalFactory.createTerminal();

        return terminal;
    }

    private void drawCharacter(int x, int y, char character, String color) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(x,y,"" + character);
    }

    @Override
    public ACTION getNextAction() throws IOException {
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke != null) {
            if ((keyStroke.getKeyType() == KeyType.EOF) || keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'q') {
                return ACTION.QUIT;
            }
            if (keyStroke.getKeyType() == KeyType.ArrowUp) {
                return ACTION.UP;
            }
            if (keyStroke.getKeyType() == KeyType.ArrowDown) {
                return ACTION.DOWN;
            }
            if (keyStroke.getKeyType() == KeyType.ArrowLeft) {
                return ACTION.LEFT;
            }
            if (keyStroke.getKeyType() == KeyType.ArrowRight) {
                return ACTION.RIGHT;
            }
        }
        return null;
    }
    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
}
