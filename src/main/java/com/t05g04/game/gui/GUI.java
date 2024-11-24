package com.t05g04.game.gui;
import com.googlecode.lanterna.input.KeyStroke;
import com.t05g04.game.model.game.elements.Position;

import java.io.IOException;


public interface GUI {
    ACTION getNextAction() throws IOException;
    void drawMourato(Position position);
    void drawCoin(Position position);
    void drawKoopa(Position position);
    void clear();
    void refresh() throws IOException;
    void close() throws IOException;
    enum ACTION {UP, DOWN, LEFT, RIGHT, NONE, QUIT}
}
