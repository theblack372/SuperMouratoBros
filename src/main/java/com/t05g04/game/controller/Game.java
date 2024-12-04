package com.t05g04.game.controller;
import com.t05g04.game.gui.LanternaGui;
import com.t05g04.game.model.game.arena.Map;
import com.t05g04.game.gui.GUI;
import java.io.IOException;

public class Game {
    private static final long KOOPA_MOVE_INTERVAL = 1000;
    private static final long Flower_APPEARING_INTERVAL = 3000;
    boolean endTerminal = false;
    LanternaGui gui;
    private long lastFlowerAppearingTime = System.currentTimeMillis();
    private long lastKoopaMoveTime = System.currentTimeMillis(); // Controla o tempo de movimento do Koopa
    private final Map map = new Map(32, 18);

    public Game() throws IOException {
        gui = new LanternaGui(map.getWidth_(), map.getHeight_());
    }

    public void run() throws IOException {
        int fps = 15;
        int frameTime = 1000 / fps;

        while (!endTerminal) {
            long startTime = System.currentTimeMillis();
            if(System.currentTimeMillis() - lastFlowerAppearingTime >= Flower_APPEARING_INTERVAL) {
                if(map.getFlower().isAppearing()){
                    map.getFlower().setAppearing(false);
                }else{
                    map.getFlower().setAppearing(true);
                }
                lastFlowerAppearingTime = System.currentTimeMillis();
            }
            // Verifica se já passou 1 segundo (1000 ms)
            if (System.currentTimeMillis() - lastKoopaMoveTime >= KOOPA_MOVE_INTERVAL) {
                map.KoopaMove(map.getKoopa()); // Mover Koopa
                lastKoopaMoveTime = System.currentTimeMillis(); // Atualiza o tempo do último movimento
            }
            GUI.ACTION action = gui.getNextAction();
            if (action== GUI.ACTION.QUIT || map.flagReach() || (map.getMourato().getPosition().getY()>= map.getHeight_()-1)
                    ||(map.getKoopa()!=null &&map.getMourato().getPosition().equals(map.getKoopa().getPosition()))||
                    (map.getFlower().isAppearing() && map.getMourato().getPosition().equals(map.getFlower().getPosition()))){
                endTerminal = true;
                gui.close();
            }
            draw();// redesenha a tela

            map.processKey(action);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;
            try {
                if (sleepTime>0){Thread.sleep(sleepTime);} // Ajusta para que o loop tenha uma duração constante
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


    private void draw() throws IOException {
        gui.clear();
        map.getRenderer().draw(gui.getScreen().newTextGraphics(),map);
        map.updateJump(map.getMourato());
        gui.refresh();
    }
}

