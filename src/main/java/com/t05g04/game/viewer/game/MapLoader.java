package com.t05g04.game.viewer.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MapLoader {
    public static char[][] loadMap(String fileName) {
        List<char[]> mapList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(MapLoader.class.getClassLoader().getResourceAsStream(fileName)), Charset.defaultCharset()))){
            String line;
            while ((line = reader.readLine()) != null) {
                mapList.add(line.toCharArray());
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return mapList.toArray(new char[0][]);
    }
}
