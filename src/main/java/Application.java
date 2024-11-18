import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
       
            map.processKey(key);
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
    private Mourato mourato=new Mourato(new Position(0,8));
    private List<Coin> coins;
    Map(int width, int height) {
        width_ = width;
        height_ = height;
        coins=createCoins();
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
    private List<Coin> createCoins() {
        List<Coin> coins = new ArrayList<>();
        coins.add(new Coin(new Position(3,8)));
        coins.add(new Coin(new Position(3,5)));
        coins.add(new Coin(new Position(4,5)));
        coins.add(new Coin(new Position(5,5)));
        coins.add(new Coin(new Position(6,5)));
        coins.add(new Coin(new Position(8,8)));
        coins.add(new Coin(new Position(14,8)));
        coins.add(new Coin(new Position(27,8)));
        return coins;
    }


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
        mourato.draw(graphics);
        for (Coin coin : coins) {
            coin.draw(graphics);
        }
    }

    public void processKey(KeyStroke key)throws IOException {
        if (key.getKeyType() == KeyType.ArrowUp) {
            moveMourato(mourato.moveUp());
        }
        if (key.getKeyType() == KeyType.ArrowDown) {
            moveMourato(mourato.moveDown());
        }
        if (key.getKeyType() == KeyType.ArrowLeft) {
            moveMourato(mourato.moveLeft());
        }
        if (key.getKeyType() == KeyType.ArrowRight) {
            moveMourato(mourato.moveRight());
        }
    }

    public void retrieveCoins(Position position){
        coins.removeIf(coin -> coin.getPosition().equals(position));
    }

    private boolean canMouratoMove(Position position){
        boolean isWindowLimit =position.getX() > 0 && position.getY() > 0 && position.getX() < width_-1 && position.getY() < height_-1;
        boolean isNotObject=true;
        if(map[position.getX()][position.getY()]=='#'){
            isNotObject=false;
        }
        return isWindowLimit && isNotObject;
    }
    private void moveMourato(Position position){
        if (canMouratoMove(position))
            mourato.getPosition().setPosition(position);
            retrieveCoins(position);
    }
}

class Position{
    private int x_;
    private int y_;
    Position(int x,int y) {
        x_=x;
        y_=y;
    }
    public int getX(){
        return x_;
    }
    public int getY(){
        return y_;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Position p = (Position) o;
        return x_ == p.getX() && y_ == p.getY();
    }
    public void setPosition(Position position) {
        x_=position.getX();
        y_=position.getY();
    }
}

abstract class Element{
    protected Position position_;
    public Element(Position position) {
        position_ = position;
    }
    Position getPosition() {
        return position_;
    }
    abstract void draw(TextGraphics graphics);
}

class Coin extends Element {
    public Coin(Position position) {
        super(position);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFD700"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position_.getX(), position_.getY()), "C");
    }
}
class Mourato extends Element{

    Mourato(Position position) {
        super(position);
    }
    public Position moveUp() {
        return new Position(position_.getX(), position_.getY()-1);
    }
    public Position moveDown(){
        return new Position(position_.getX(), position_.getY()+1);
    }
    public Position moveLeft(){
        return new Position(position_.getX()-1, position_.getY());
    }
    public Position moveRight(){
        return new Position(position_.getX()+1, position_.getY());
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position_.getX(),
                position_.getY()), "M");
    }
}
