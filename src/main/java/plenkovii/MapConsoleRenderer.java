package plenkovii;


import plenkovii.entity.*;

import java.util.Queue;
import java.util.Set;

public class MapConsoleRenderer {
    public static final String EMOJI_EMPTY_FIELD = "\uD83D\uDFEB";
    public static final String EMOJI_GRASS = "\uD83C\uDF31";
    public static final String EMOJI_TREE = "\uD83C\uDF33";
    public static final String EMOJI_ROCK = "\uD83C\uDF2B\uFE0F";
    public static final String EMOJI_HERBIVORE = "\uD83D\uDC39";
    public static final String EMOJI_PREDATOR = "\uD83D\uDC3A";
    public static final String EMOJI_WALL = "\uD83E\uDDF1";
    public static final String EMOJI_BARRIER = "\uD83E\uDE9C";




    public void render(Map map) {
        for (int x = 0; x <= Map.height + 1; x++) {

            if (x != 1) {
                System.out.print(EMOJI_WALL);
            } else {
                System.out.print(EMOJI_BARRIER);

            }


            for (int y = 1; y <= Map.length; y++) {

                if (x == 0 || x == Map.height + 1) {
                    System.out.print(EMOJI_WALL);
                    continue;
                }
                    Coordinates coordinates = new Coordinates(x, y);
                if (!map.entities.containsKey(coordinates)) {
                    System.out.print(EMOJI_EMPTY_FIELD);
                } else {
                    printSprite(map, coordinates);
                }
            }
            System.out.println(EMOJI_WALL + x);
        }
    }

    public void renderPath(Map map, Set<Coordinates> path) {
        for (int x = 0; x <= Map.height + 1; x++) {

            if (x != 1) {
                System.out.print(EMOJI_WALL);
            } else {
                System.out.print(EMOJI_BARRIER);

            }

            for (int y = 1; y <= Map.length; y++) {

                if (x == 0 || x == Map.height + 1) {
                    System.out.print(EMOJI_WALL);
                    continue;
                }
                Coordinates coordinates = new Coordinates(x, y);
                if (!map.entities.containsKey(coordinates)) {
                    if (path.contains(coordinates)) {
                        System.out.print("\uD83D\uDFE7");

                    } else {
                        System.out.print(EMOJI_EMPTY_FIELD);

                    }
                } else {
                    printSprite(map, coordinates);
                }
            }
            System.out.println(EMOJI_WALL + x);
        }
    }


    private static void printSprite(Map map, Coordinates coordinates) {
        if (map.entities.get(coordinates) instanceof Tree) {
            System.out.print(EMOJI_TREE);
        } else if (map.entities.get(coordinates) instanceof Grass) {
            System.out.print(EMOJI_GRASS);
        } else if (map.entities.get(coordinates) instanceof Rock) {
            System.out.print(EMOJI_ROCK);
        } else if (map.entities.get(coordinates) instanceof Herbivore) {
            System.out.print(EMOJI_HERBIVORE);
        } else if (map.entities.get(coordinates) instanceof Predator) {
            System.out.print(EMOJI_PREDATOR);
        }

    }
}
