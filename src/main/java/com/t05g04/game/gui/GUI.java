package com.t05g04.game.gui;

import java.io.IOException;


public interface GUI {
    ACTION getNextAction() throws IOException;
    void clear();
    void refresh() throws IOException;
    void close() throws IOException;
    enum ACTION {UP, DOWN, LEFT, RIGHT, NONE, QUIT, SELECT}
}
