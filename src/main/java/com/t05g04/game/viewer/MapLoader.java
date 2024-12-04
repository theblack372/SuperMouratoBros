package com.t05g04.game.viewer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MapLoader {
    private static char[][] map;

    public static void main(String[] args) {
        map = loadMap("maps/map1.txt");
    }

    public char[][] getMap() {
        return map;
    }

    public static char[][] loadMap(String resourcePath) {
        List<char[]> mapList = new ArrayList<>();

        try (InputStream inputStream = MapLoader.class.getClassLoader().getResourceAsStream(resourcePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                mapList.add(line.toCharArray());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return mapList.toArray(new char[0][]);
    }
}
