import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.TerminalScrollController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
    private Thread jumpThread;
    private long lastKoopaMoveTime = System.currentTimeMillis(); // Controla o tempo de movimento do Koopa
    private static final long KOOPA_MOVE_INTERVAL = 1000; // 1 segundo (1000 ms)

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
            long startTime = System.currentTimeMillis();

            // Verifica se já passou 1 segundo (1000 ms)
            if (System.currentTimeMillis() - lastKoopaMoveTime >= KOOPA_MOVE_INTERVAL) {
                map.KoopaMove(map.getKoopa()); // Mover Koopa
                lastKoopaMoveTime = System.currentTimeMillis(); // Atualiza o tempo do último movimento
            }

            draw(); // Redesenha a tela

            KeyStroke key = screen.pollInput();
            if (key != null) {
                if ((key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')||(map.getMourato().getPosition().getY()>= map.height_-1)
                ||(map.getKoopa()!=null &&map.getMourato().getPosition().equals(map.getKoopa().getPosition()))) {
                    screen.stopScreen();
                    endTerminal = true;
                }
                handleInput(key); // Lida com o input do jogador
            }

            long elapsedTime = System.currentTimeMillis() - startTime;
            try {
                Thread.sleep(Math.max(0, 100 - elapsedTime)); // Ajusta para que o loop tenha uma duração constante
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void handleInput(KeyStroke key) throws IOException {
        Mourato mourato = map.getMourato();

        if (key.getKeyType() == KeyType.ArrowUp && !mourato.isJump_()) {
            startJumpThread(); // Inicia o salto
        } else if (key.getKeyType() == KeyType.ArrowLeft || key.getKeyType() == KeyType.ArrowRight || key.getKeyType() == KeyType.ArrowDown) {
            map.processKey(key);// Processa movimento lateral
        }
    }
    private void startJumpThread() {
        Mourato mourato = map.getMourato();
        mourato.setJump_(true); // Inicia o salto

        jumpThread = new Thread(() -> {
            try {
                while (mourato.isJump_()) {
                    map.updateJump(mourato); // Apenas atualiza o salto
                    draw(); // Atualiza a tela
                    Thread.sleep(500); // Controla a velocidade do salto
                }
            } catch (InterruptedException | IOException e) {
                Thread.currentThread().interrupt();
            }
        });

        jumpThread.start();
    }






    private void draw() throws IOException {
        screen.clear();
        map.draw(screen.newTextGraphics());
        map.updateJump(map.getMourato());
        screen.refresh();
    }
}

class Map {
    int height_;
    int width_;
    private Mourato mourato = new Mourato(new Position(0, 8), false, 1, 4, 0);
    private List<Coin> coins;
    private CopyOnWriteArrayList<Koopa> koopas;


    Map(int width, int height) {
        width_ = width;
        height_ = height;
        coins = createCoins();
        koopas = new CopyOnWriteArrayList<>(createKoopas());
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
        coins.add(new Coin(new Position(3, 8)));
        coins.add(new Coin(new Position(3, 5)));
        coins.add(new Coin(new Position(4, 5)));
        coins.add(new Coin(new Position(5, 5)));
        coins.add(new Coin(new Position(6, 5)));
        coins.add(new Coin(new Position(8, 8)));
        coins.add(new Coin(new Position(14, 8)));
        coins.add(new Coin(new Position(27, 8)));
        return coins;
    }

    private List<Koopa> createKoopas() {
        List<Koopa> koopas = new ArrayList<>();
        koopas.add(new Koopa(new Position(19, 8), 1));
        return koopas;
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
        synchronized (koopas) {
            for (Koopa koopa : koopas) {
                koopa.draw(graphics);
            }
        }
        for (Coin coin : coins) {
            coin.draw(graphics);
        }
    }

    public void processKey(KeyStroke key) throws IOException {
        if (key.getKeyType() == KeyType.ArrowUp) {
            if (!mourato.isJump_()) {
                mourato.setJump_(true);
            }
        }
        if (key.getKeyType() == KeyType.ArrowDown) {
            moveMourato(mourato.moveDown());
        }
        if (key.getKeyType() == KeyType.ArrowLeft) {
            moveMourato(mourato.moveLeft());
            checkAndFall(mourato);
        }
        if (key.getKeyType() == KeyType.ArrowRight) {
            moveMourato(mourato.moveRight());
            checkAndFall(mourato);
        }
    }


    public void retrieveCoins(Position position) {
        coins.removeIf(coin -> coin.getPosition().equals(position));
    }

    private boolean canMouratoMove(Position position) {
        // Verificar se a posição está dentro dos limites
        if (position.getX() < 0 || position.getY() < 0 || position.getX() >= width_ || position.getY() >= height_) {
            return false; // Fora dos limites
        }

        // Verificar se a posição não colide com objetos
        char tile = map[position.getX()][position.getY()];
        return tile!='#' && tile!='H';
    }


    private void moveMourato(Position position) {
        if (canMouratoMove(position)) {
            mourato.getPosition().setPosition(position);
            retrieveCoins(position);
        }
    }

    private boolean canKoopaMove(Position position) {
        if (map[position.getX()][position.getY()] == '#') {
            return false;
        }
        return true;
    }

    public void KoopaMove(Koopa koopa) {
        if (koopa == null) {
            return;
        }
        synchronized (koopas) {
            int nextX = koopa.getPosition().getX() + koopa.getVelocity_();
            int nextY = koopa.getPosition().getY();
            Position nextPosition = new Position(nextX, nextY);
            if (canKoopaMove(nextPosition)) {
                koopa.move();
            } else {
                koopa.setVelocity_(-koopa.getVelocity_());
                koopa.move();
            }
        }
    }

    public Koopa getKoopa() {
        if (koopas != null && !koopas.isEmpty()) {
            return koopas.get(0); // Retorna o primeiro elemento da lista
        }
        return null; // Retorna null caso não haja Koopas
    }


    public Mourato getMourato() {
        return mourato;
    }


    public void updateJump(Mourato mourato) {
        if (!mourato.isJump_()) return;

        int velocity = mourato.getJumpVelocity_();
        int jumpHeight = mourato.getJumpHeight_();
        int jumpProgress = mourato.getCountJump_();
        if (jumpProgress < jumpHeight) { // verifica se o mourato ainda está em momento ascendente do salto
            boolean blockBroken = breakBlock(mourato);
            if (blockBroken) {        // se o bloco for partido, força o mourato ir para sentido contrário
                jumpProgress = jumpHeight;
                mourato.setCountJump_(jumpProgress);
            }

        }

        // Atualiza a posição para subir ou descer
        int newY = mourato.getPosition().getY() + ((jumpProgress < jumpHeight) ? -velocity : velocity);
        Position newPosition = new Position(mourato.getPosition().getX(), newY);

        if(newY>=height_){
            System.exit(0);
        }

        if (canMouratoMove(newPosition)) {
            mourato.getPosition().setPosition(newPosition);
            retrieveCoins(newPosition); // Coleta moedas
            mourato.setCountJump_(jumpProgress + 1);
            if (jumpProgress >= jumpHeight) {
                destroyKoopaIfHit(mourato); // mata o koopa em caso de velocidade <0
            }
        } else {
            mourato.setJump_(false); // Termina o salto em caso de colisão
        }

        // Verifica se atingiu o chão
        if (!canMouratoMove(new Position(mourato.getPosition().getX(), mourato.getPosition().getY() + 1))) {
            mourato.setJump_(false); // Termina o salto ao atingir o chão
            mourato.setCountJump_(0); // Reseta o progresso
        }
    }
    private void checkAndFall(Mourato mourato) {
        if (mourato.isJump_()) {
            return; // Se está no meio do salto, não aplica a lógica de queda
        }

        Position currentPosition = mourato.getPosition();
        int x = currentPosition.getX();
        int y = currentPosition.getY();
        while (y + 1 < height_ && canMouratoMove(new Position(x, y + 1))) {
            mourato.getPosition().setPosition(new Position(x, y + 1));
            y++;
            destroyKoopaIfHit(mourato);
        }
    }

    private void destroyKoopaIfHit (Mourato mourato){
        Position mouratoPosition = mourato.getPosition();
        synchronized (koopas) {
            for (Koopa koopa : koopas) {
                Position koopaPosition = koopa.getPosition();

                // Verifica se Mourato está na mesma posição ou imediatamente acima do Koopa
                if (mouratoPosition.getX() == koopaPosition.getX() && mouratoPosition.getY() == koopaPosition.getY() - 1) {
                    koopas.remove(koopa); // Remove o Koopa atingido
                    break; // Sai após destruir o Koopa
                }
            }
        }
    }
    private boolean breakBlock(Mourato mourato) {
        if(mourato.isJump_()) {
            if(mourato.getJumpVelocity_()>=0){// caso mourato esteja em salto em momento ascendente
                Position positionblock = new Position(mourato.getPosition().getX(), mourato.getPosition().getY()-1);
                if(map[positionblock.getX()][positionblock.getY()] == 'H'){
                    map[positionblock.getX()][positionblock.getY()]='.';//parte o bloco
                    mourato.setCountJump_(mourato.getJumpHeight_()); //mete o contador de salto no maximo para provocar momento descendente
                    return true;
                }
            }
        }
        return false;
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
    private boolean jump_;
    private int jumpVelocity_;
    private int jumpHeight_;
    private int countJump_;
    Mourato(Position position, boolean jump, int jumpVelocity, int jumpHeight,int countJump) {
        super(position);
        jump_ = jump;
        jumpVelocity_ = jumpVelocity;
        jumpHeight_ = jumpHeight;
        countJump_ = countJump;
    }

    public int getJumpVelocity_() {
        return jumpVelocity_;
    }

    public int getJumpHeight_() {
        return jumpHeight_;
    }
    public int getCountJump_() {
        return countJump_;
    }
    public void setCountJump_(int countJump) {
        countJump_ = countJump;
    }
    public void setJumpVelocity_(int jumpVelocity) {
        jumpVelocity_ = jumpVelocity;
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
    public boolean isJump_() {
        return jump_;
    }

    public void setJump_(boolean jump) {
        jump_ = jump;
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position_.getX(),
                position_.getY()), "M");
    }
}

class Koopa extends Element {
    private int velocity_;
    public Koopa(Position position,int velocity) {
        super(position);
        velocity_=velocity;
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#013220"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position_.getX(), position_.getY()), "K");
    }
    public void move() {
        int newX = getPosition().getX() + velocity_;
        position_=new Position(newX, getPosition().getY());
    }

    public int getVelocity_() {
        return velocity_;
    }
    public void setVelocity_(int velocity) {
        velocity_ = velocity;
    }
}