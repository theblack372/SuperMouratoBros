package com.t05g04.game.gui;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.t05g04.game.model.game.Position;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class LanternaGui implements GUI{
    private final Screen screen;
    private final BufferedImage wall;
    private final BufferedImage breakableWall;
    private final BufferedImage flag;
    private final BufferedImage questionBlock;
    private final BufferedImage questionBlockCaught;
    private final BufferedImage coin;
    private final BufferedImage fireBall1;
    private final BufferedImage flower;
    private final BufferedImage powerUpFlower;
    private final BufferedImage koopa1;
    private final BufferedImage koopa2;
    private final BufferedImage mourato_idle;
    private final BufferedImage mourato_Lidle;
    private final BufferedImage mourato_jump;
    private final BufferedImage mourato_Ljump;
    private final BufferedImage mourato_run1;
    private final BufferedImage mourato_run2;
    private final BufferedImage mourato_Lrun1;
    private final BufferedImage mourato_Lrun2;
    private final BufferedImage deathMenu_exit;
    private final BufferedImage deathMenu_retry;
    private final BufferedImage endLevelMenu_continue;
    private final BufferedImage endLevelMenu_exit;
    private final BufferedImage endLevelMenu_retry;
    private final BufferedImage mapSelect_1;
    private final BufferedImage mapSelect_2;
    private final BufferedImage mapSelect_3;
    private final BufferedImage mapSelect_4;
    private final BufferedImage mapSelect_exit;
    private final BufferedImage startMenu_exit;
    private final BufferedImage startMenu_start;
    private final BufferedImage startMenu_instructions;
    private final BufferedImage instructionsMenu;
    private final BufferedImage one;
    private final BufferedImage two;
    private final BufferedImage three;
    private final BufferedImage four;
    private final BufferedImage five;
    private final BufferedImage six;
    private final BufferedImage seven;
    private final BufferedImage eight;
    private final BufferedImage nine;
    private final BufferedImage zero;


    public LanternaGui(Screen screen) throws IOException {
    this.screen = screen;
    this.wall =
        ImageIO.read(
            Objects.requireNonNull(
                LanternaGui.class.getClassLoader().getResource("sprites/blocks/#.png")));
    this.breakableWall =
        ImageIO.read(
            Objects.requireNonNull(
                LanternaGui.class.getClassLoader().getResource("sprites/blocks/H.png")));

    this.flag =
        ImageIO.read(
            Objects.requireNonNull(
                LanternaGui.class.getClassLoader().getResource("sprites/blocks/flag.png")));

    this.questionBlock =
        ImageIO.read(
            Objects.requireNonNull(
                LanternaGui.class.getClassLoader().getResource("sprites/blocks/questionBlock.png")));
    this.questionBlockCaught =
        ImageIO.read(
            Objects.requireNonNull(
                LanternaGui.class
                    .getClassLoader()
                    .getResource("sprites/blocks/questionBlockCaught.png")));

    this.coin =
            ImageIO.read(
                    Objects.requireNonNull(
                            LanternaGui.class
                                    .getClassLoader()
                                    .getResource("sprites/entities/coin.png")));

    this.fireBall1 =
        ImageIO.read(
            Objects.requireNonNull(
                LanternaGui.class.getClassLoader().getResource("sprites/entities/fireBall1.png")));
    this.flower =
        ImageIO.read(
            Objects.requireNonNull(
                LanternaGui.class.getClassLoader().getResource("sprites/entities/flower.png")));
    this.koopa1 =
        ImageIO.read(
            Objects.requireNonNull(
                LanternaGui.class.getClassLoader().getResource("sprites/entities/koopa1.png")));
    this.koopa2 =
        ImageIO.read(
            Objects.requireNonNull(
                LanternaGui.class.getClassLoader().getResource("sprites/entities/koopa2.png")));
    this.mourato_idle =
        ImageIO.read(
            Objects.requireNonNull(
                LanternaGui.class
                    .getClassLoader()
                    .getResource("sprites/entities/mourato_idle.png")));

    this.mourato_Lidle =
            ImageIO.read(
                    Objects.requireNonNull(
                            LanternaGui.class
                                    .getClassLoader()
                                    .getResource("sprites/entities/mourato_Lidle.png")));

    this.mourato_jump =
        ImageIO.read(
            Objects.requireNonNull(
                LanternaGui.class
                    .getClassLoader()
                    .getResource("sprites/entities/mourato_jump.png")));

    this.mourato_Ljump =
            ImageIO.read(
                    Objects.requireNonNull(
                            LanternaGui.class
                                    .getClassLoader()
                                    .getResource("sprites/entities/mourato_Ljump.png")));


        this.mourato_run1 =
        ImageIO.read(
            Objects.requireNonNull(
                LanternaGui.class
                    .getClassLoader()
                    .getResource("sprites/entities/mourato_run1.png")));
    this.mourato_run2 =
        ImageIO.read(
            Objects.requireNonNull(
                LanternaGui.class
                    .getClassLoader()
                    .getResource("sprites/entities/mourato_run2.png")));

    this.mourato_Lrun1 =
            ImageIO.read(
                    Objects.requireNonNull(
                            LanternaGui.class
                                    .getClassLoader()
                                    .getResource("sprites/entities/mourato_Lrun1.png")));
    this.mourato_Lrun2 =
            ImageIO.read(
                    Objects.requireNonNull(
                            LanternaGui.class
                                    .getClassLoader()
                                    .getResource("sprites/entities/mourato_Lrun2.png")));


        this.deathMenu_exit =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/deathMenu/DeathMenu_exit.png")));
        this.deathMenu_retry =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/deathMenu/DeathMenu_retry.png")));
        this.endLevelMenu_continue =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/endLevelMenu/endLevelMenu_continue.png")));
        this.endLevelMenu_exit =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/endLevelMenu/endLevelMenu_exit.png")));
        this.endLevelMenu_retry =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/endLevelMenu/endLevelMenu_retry.png")));
        this.mapSelect_1 =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/mapSelectionMenu/mapSelect_1.png")));
        this.mapSelect_2 =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/mapSelectionMenu/mapSelect_2.png")));
        this.mapSelect_3 =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/mapSelectionMenu/mapSelect_3.png")));
        this.mapSelect_4 =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/mapSelectionMenu/mapSelect_4.png")));
        this.mapSelect_exit =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/mapSelectionMenu/mapSelect_exit.png")));
        this.startMenu_exit =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/startMenu/startMenu_exit.png")));
        this.startMenu_start =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/startMenu/startMenu_start.png")));

        this.startMenu_instructions =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/startMenu/startMenu_instructions.png")));

        this.instructionsMenu =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/instructionMenu/instructionsMenu.png")));

        this.powerUpFlower =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/entities/powerUpFlower.png")));

        this.one =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/numbers/1.png")));

        this.two =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/numbers/2.png")));
        this.three =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/numbers/3.png")));
        this.four =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/numbers/4.png")));
        this.five =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/numbers/5.png")));
        this.six =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/numbers/6.png")));
        this.seven =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/numbers/7.png")));
        this.eight =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/numbers/8.png")));
        this.nine =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/numbers/9.png")));
        this.zero =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/numbers/0.png")));

  }

    public Screen getScreen() {
        return screen;
    }

    public LanternaGui(int width, int height) throws IOException, URISyntaxException, FontFormatException {
        Terminal terminal = createTerminal(width, height);
        this.screen = createScreen(terminal) ;
        this.wall =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/blocks/#.png")));
        this.breakableWall =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/blocks/H.png")));
        this.flag =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/blocks/flag.png")));

        this.questionBlock =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/blocks/questionBlock.png")));
        this.questionBlockCaught =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/blocks/questionBlockCaught.png")));
        this.coin =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/entities/coin.png")));
        this.fireBall1 =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/entities/fireBall1.png")));
        this.flower =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/entities/flower.png")));
        this.koopa1 =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/entities/koopa1.png")));
        this.koopa2 =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/entities/koopa2.png")));
        this.mourato_idle =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/entities/mourato_idle.png")));

        this.mourato_Lidle =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/entities/mourato_Lidle.png")));

        this.mourato_jump =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/entities/mourato_jump.png")));

        this.mourato_Ljump =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/entities/mourato_Ljump.png")));


        this.mourato_run1 =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/entities/mourato_run1.png")));
        this.mourato_run2 =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/entities/mourato_run2.png")));

        this.mourato_Lrun1 =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/entities/mourato_Lrun1.png")));
        this.mourato_Lrun2 =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/entities/mourato_Lrun2.png")));


        this.deathMenu_exit =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/deathMenu/DeathMenu_exit.png")));
        this.deathMenu_retry =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/deathMenu/DeathMenu_retry.png")));
        this.endLevelMenu_continue =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/endLevelMenu/endLevelMenu_continue.png")));
        this.endLevelMenu_exit =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/endLevelMenu/endLevelMenu_exit.png")));
        this.endLevelMenu_retry =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/endLevelMenu/endLevelMenu_retry.png")));
        this.mapSelect_1 =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/mapSelectionMenu/mapSelect_1.png")));
        this.mapSelect_2 =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/mapSelectionMenu/mapSelect_2.png")));
        this.mapSelect_3 =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/mapSelectionMenu/mapSelect_3.png")));
        this.mapSelect_4 =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/mapSelectionMenu/mapSelect_4.png")));
        this.mapSelect_exit =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/mapSelectionMenu/mapSelect_exit.png")));
        this.startMenu_exit =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/startMenu/startMenu_exit.png")));
        this.startMenu_start =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/startMenu/startMenu_start.png")));

        this.startMenu_instructions =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/startMenu/startMenu_instructions.png")));

        this.instructionsMenu =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class
                                        .getClassLoader()
                                        .getResource("sprites/menu/InstructionMenu/InstructionsMenu.png")));

        this.powerUpFlower =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/entities/powerUpFlower.png")));
        this.one =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/numbers/1.png")));

        this.two =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/numbers/2.png")));
        this.three =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/numbers/3.png")));
        this.four =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/numbers/4.png")));
        this.five =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/numbers/5.png")));
        this.six =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/numbers/6.png")));
        this.seven =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/numbers/7.png")));
        this.eight =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/numbers/8.png")));
        this.nine =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/numbers/9.png")));
        this.zero =
                ImageIO.read(
                        Objects.requireNonNull(
                                LanternaGui.class.getClassLoader().getResource("sprites/numbers/0.png")));
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

        Font loadedFont = font.deriveFont(Font.PLAIN, 3);
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
            if ((keyStroke.getKeyType() == KeyType.EOF) || (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'q')) {
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
            if (keyStroke.getKeyType() == KeyType.Enter) {
                return ACTION.SELECT;
            }
            if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'x') {
                return ACTION.SHOOT;
            }
        }
        return ACTION.NONE;
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

    public void drawPNG(Position position, BufferedImage sprites) throws IOException {
        TextGraphics textGraphics = screen.newTextGraphics();
        for (int x = 0; x < sprites.getWidth(); x++){
            for (int y = 0; y < sprites.getHeight(); y++){
                int a = sprites.getRGB(x, y);
                int alpha = (a >> 24) & 0xff;
                int red = (a >> 16) & 255;
                int green = (a >> 8) & 255;
                int blue = a & 255;

                if (alpha != 0) {
                    TextCharacter c = new TextCharacter(' ', new TextColor.RGB(red, green, blue), new TextColor.RGB(red, green, blue));
                    textGraphics.setCharacter(position.getX()*16+x,position.getY()*16+y, c);
                }
            }
        }
    }

    @Override
    public void draw_Wall(Position position) throws IOException {
        drawPNG(position, this.wall);
    }

    @Override
    public void draw_breakableWall(Position position) throws IOException {
        drawPNG(position, this.breakableWall);
    }

    @Override
    public void draw_questionBlock(Position position) throws IOException {
        drawPNG(position, this.questionBlock);
    }

    @Override
    public void draw_questionBlockCaught(Position position) throws IOException {
        drawPNG(position, this.questionBlockCaught);
    }

    @Override
    public void draw_fireBall1(Position position) throws IOException {
        drawPNG(position, this.fireBall1);
    }

    @Override
    public void draw_flower(Position position) throws IOException {
        drawPNG(position, this.flower);
    }

    @Override
    public void draw_powerUpFlower(Position position) throws IOException {
        drawPNG(position, this.powerUpFlower);
    }

    @Override
    public void draw_koopa1(Position position) throws IOException {
        drawPNG(position, this.koopa1);
    }

    @Override
    public void draw_koopa2(Position position) throws IOException {
        drawPNG(position, this.koopa2);
    }

    @Override
    public void draw_mourato_idle(Position position) throws IOException {
        drawPNG(position, this.mourato_idle);
    }

    @Override
    public void draw_mourato_jump(Position position) throws IOException {
        drawPNG(position, this.mourato_jump);
    }

    @Override
    public void draw_mourato_run1(Position position) throws IOException {
        drawPNG(position, this.mourato_run1);
    }

    @Override
    public void draw_mourato_run2(Position position) throws IOException {
    drawPNG(position, this.mourato_run2);
    }

    @Override
    public void draw_mourato_Lidle(Position position) throws IOException {
        drawPNG(position, this.mourato_Lidle);
    }

    @Override
    public void draw_mourato_Ljump(Position position) throws IOException {
        drawPNG(position, this.mourato_Ljump);
    }

    @Override
    public void draw_mourato_Lrun1(Position position) throws IOException {
        drawPNG(position, this.mourato_Lrun1);
    }

    @Override
    public void draw_mourato_Lrun2(Position position) throws IOException {
        drawPNG(position, this.mourato_Lrun2);
    }

    @Override
    public void draw_deathMenu_exit(Position position) throws IOException {
        drawPNG(position, this.deathMenu_exit);}

    @Override
    public void draw_deathMenu_retry(Position position) throws IOException {
        drawPNG(position, this.deathMenu_retry);}

    @Override
    public void draw_endLevelMenu_continue(Position position) throws IOException {
        drawPNG(position, this.endLevelMenu_continue);}

    @Override
    public void endLevelMenu_continue(Position position) throws IOException {
        drawPNG(position, this.endLevelMenu_continue);}

    @Override
    public void draw_endLevelMenu_exit(Position position) throws IOException {
        drawPNG(position, this.endLevelMenu_exit);}

    @Override
    public void draw_endLevelMenu_retry(Position position) throws IOException {
        drawPNG(position, this.endLevelMenu_retry);}

    @Override
    public void draw_mapSelect_1(Position position) throws IOException {
        drawPNG(position, this.mapSelect_1);}

    @Override
    public void draw_mapSelect_2(Position position) throws IOException {
        drawPNG(position, this.mapSelect_2);}

    @Override
    public void draw_mapSelect_3(Position position) throws IOException {
        drawPNG(position, this.mapSelect_3);}

    @Override
    public void draw_mapSelect_4(Position position) throws IOException {
        drawPNG(position, this.mapSelect_4);}

    @Override
    public void draw_mapSelect_exit(Position position) throws IOException {
        drawPNG(position, this.mapSelect_exit);}

    @Override
    public void draw_startMenu_exit(Position position) throws IOException {
        drawPNG(position, this.startMenu_exit);}

    @Override
    public void draw_startMenu_start(Position position) throws IOException {
        drawPNG(position, this.startMenu_start);}

    @Override
    public void draw_startMenu_instructions(Position position) throws IOException {
        drawPNG(position, this.startMenu_instructions);
    }

    @Override
    public void draw_instructionsMenu(Position position) throws IOException {
        drawPNG(position, this.instructionsMenu);
    }

    public void draw_flag() throws IOException {
        drawPNG(new Position(31,0), this.flag);
    }
    public void draw_coin(Position position) throws IOException {
        drawPNG(position, this.coin);
    }

    public void draw_1(Position position) throws IOException {
        drawPNG(position, this.one);
    }

    public void draw_2(Position position) throws IOException {
        drawPNG(position, this.two);
    }

    public void draw_3(Position position) throws IOException {
        drawPNG(position, this.three);
    }

    public void draw_4(Position position) throws IOException {
        drawPNG(position, this.four);
    }

    public void draw_5(Position position) throws IOException {
        drawPNG(position, this.five);
    }

    public void draw_6(Position position) throws IOException {
        drawPNG(position, this.six);
    }

    public void draw_7(Position position) throws IOException {
        drawPNG(position, this.seven);
    }

    public void draw_8(Position position) throws IOException {
        drawPNG(position, this.eight);
    }

    public void draw_9(Position position) throws IOException {
        drawPNG(position, this.nine);
    }

    public void draw_0(Position position) throws IOException {
        drawPNG(position, this.zero);
    }


    public void draw_curentCoins(int coins) throws IOException {
        // Start position for the coins display
        int x = 2; // X-coordinate for the coin icon
        int y = 2; // Y-coordinate for both the icon and the digits

        // Draw the coin icon
        Position coinIconPosition = new Position(x, y);
        draw_coin(coinIconPosition);

        // Draw the digits for the coins
        draw_digits(coins, x, y);
    }

    private void draw_digits(int number, int x, int y) throws IOException {
        String coinsStr = String.valueOf(number);
        for (int i = 0; i < coinsStr.length(); i++) { // Start from i = 0
            char digit = coinsStr.charAt(i);
            Position digitPosition = new Position(x + 1+i, y); // Adjust spacing (2 for icon, 4 for digit spacing)
            switch (digit) {
                case '0' -> draw_0(digitPosition);
                case '1' -> draw_1(digitPosition);
                case '2' -> draw_2(digitPosition);
                case '3' -> draw_3(digitPosition);
                case '4' -> draw_4(digitPosition);
                case '5' -> draw_5(digitPosition);
                case '6' -> draw_6(digitPosition);
                case '7' -> draw_7(digitPosition);
                case '8' -> draw_8(digitPosition);
                case '9' -> draw_9(digitPosition);
            }
        }
    }


    public void draw_currentBullets(int bullets) throws IOException {
        // Start position for the bullets display
        int x = 5; // X-coordinate for the fireball icon
        int y = 2; // Y-coordinate for both the icon and the digits

        // Draw the fireball icon
        Position fireballIconPosition = new Position(x, y);
        draw_fireBall1(fireballIconPosition);


        // Draw the digits for the bullets
        draw_digits(bullets, x, y);
    }
}


