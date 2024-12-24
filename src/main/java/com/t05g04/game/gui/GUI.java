package com.t05g04.game.gui;

import com.t05g04.game.model.game.Position;

import java.io.IOException;


public interface GUI {
    ACTION getNextAction() throws IOException;
    void clear();
    void refresh() throws IOException;
    void close() throws IOException;

    void draw_Wall(Position position) throws IOException;

    void draw_breakableWall(Position position) throws IOException;

    void draw_questionBlock(Position position) throws IOException;

    void draw_questionBlockCaught(Position position) throws IOException;

    void draw_fireBall1(Position position) throws IOException;

    void draw_flower(Position position) throws IOException;

    void draw_powerUpFlower(Position position) throws IOException;

    void draw_koopa1(Position position) throws IOException;

    void draw_koopa2(Position position) throws IOException;

    void draw_mourato_idle(Position position) throws IOException;

    void draw_mourato_Lidle(Position position) throws IOException;

    void draw_mourato_jump(Position position) throws IOException;

    void draw_mourato_Ljump(Position position) throws IOException;

    void draw_mourato_run1(Position position) throws IOException;

    void draw_mourato_run2(Position position) throws IOException;

    void draw_mourato_Lrun1(Position position) throws IOException;

    void draw_mourato_Lrun2(Position position) throws IOException;

    void draw_deathMenu_exit(Position position) throws IOException;

    void draw_deathMenu_retry(Position position) throws IOException;

    void draw_endLevelMenu_continue(Position position) throws IOException;

    void endLevelMenu_continue(Position position) throws IOException;

    void draw_endLevelMenu_exit(Position position) throws IOException;

    void draw_endLevelMenu_retry(Position position) throws IOException;

    void draw_mapSelect_1(Position position) throws IOException;

    void draw_mapSelect_2(Position position) throws IOException;

    void draw_mapSelect_3(Position position) throws IOException;

    void draw_mapSelect_4(Position position) throws IOException;

    void draw_mapSelect_exit(Position position) throws IOException;

    void draw_startMenu_exit(Position position) throws IOException;

    void draw_startMenu_start(Position position) throws IOException;

    void draw_startMenu_instructions(Position position) throws IOException;

    void draw_instructionsMenu(Position position) throws IOException;

    enum ACTION {UP, DOWN, LEFT, RIGHT, NONE, QUIT, SELECT,SHOOT}
}
