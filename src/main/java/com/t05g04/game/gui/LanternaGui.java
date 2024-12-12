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
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LanternaGui implements GUI{
    private final Screen screen;

    public LanternaGui(Screen screen) {
        this.screen = screen;
    }

    public Screen getScreen() {
        return screen;
    }

    public LanternaGui(int width, int height) throws IOException, URISyntaxException, FontFormatException {
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

    public Terminal createTerminal(int width, int height) throws IOException, FontFormatException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("fonts/square.ttf");
        assert resource != null;
        File fontFile = new File(resource.toURI());
        Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        DefaultTerminalFactory factory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height));

        Font loadedFont = font.deriveFont(Font.PLAIN, 25);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        factory.setTerminalEmulatorFontConfiguration(fontConfig);
        factory.setForceAWTOverSwing(true);

        Terminal terminal;
        terminal = factory.createTerminal();

        return terminal;
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

    public void displayMessage(Screen screen, String message, int x, int y) throws IOException {
        // Get TextGraphics from the Screen
        TextGraphics textGraphics = screen.newTextGraphics();

        // Set colors if desired
        textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        // Write the message at the specified position
        textGraphics.putString(x, y, message);

        // Refresh the screen to show changes
        screen.refresh();
    }

}
