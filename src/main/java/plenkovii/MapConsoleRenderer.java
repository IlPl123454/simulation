package plenkovii;


import plenkovii.entity.Rock;
import plenkovii.entity.Tree;
import plenkovii.entity.immobile.Grass;
import plenkovii.entity.mobile.Herbivore;
import plenkovii.entity.mobile.Predator;

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

    private static void printSprite(Map map, Coordinates coordinates) {
        if (map.getEntities().get(coordinates) instanceof Tree) {
            System.out.print(EMOJI_TREE);
        } else if (map.getEntities().get(coordinates) instanceof Grass) {
            System.out.print(EMOJI_GRASS);
        } else if (map.getEntities().get(coordinates) instanceof Rock) {
            System.out.print(EMOJI_ROCK);
        } else if (map.getEntities().get(coordinates) instanceof Herbivore) {
            System.out.print(EMOJI_HERBIVORE);
        } else if (map.getEntities().get(coordinates) instanceof Predator) {
            System.out.print(EMOJI_PREDATOR);
        }
    }

    public void render(Map map) {
        for (int x = 0; x <= map.HEIGHT + 1; x++) {
            if (x != 1) {
                System.out.print(EMOJI_WALL);
            } else {
                System.out.print(EMOJI_BARRIER);
            }
            for (int y = 1; y <= map.LENGTH; y++) {

                if (x == 0 || x == map.HEIGHT + 1) {
                    System.out.print(EMOJI_WALL);
                    continue;
                }
                Coordinates coordinates = new Coordinates(x, y);
                if (!map.getEntities().containsKey(coordinates)) {
                    System.out.print(EMOJI_EMPTY_FIELD);
                } else {
                    printSprite(map, coordinates);
                }
            }
            System.out.println(EMOJI_WALL + x);
        }
    }

    public void renderPath(Map map, Set<Coordinates> path1, Set<Coordinates> path2) {
        for (int x = 0; x <= map.HEIGHT + 1; x++) {

            if (x != 1) {
                System.out.print(EMOJI_WALL);
            } else {
                System.out.print(EMOJI_BARRIER);
            }
            for (int y = 1; y <= map.LENGTH; y++) {

                if (x == 0 || x == map.HEIGHT + 1) {
                    System.out.print(EMOJI_WALL);
                    continue;
                }
                Coordinates coordinates = new Coordinates(x, y);
                if (!map.getEntities().containsKey(coordinates)) {
                    if (path1.contains(coordinates)) {
                        System.out.print("\uD83D\uDFE7");
                    } else if (path2.contains(coordinates)) {
                        System.out.print("\uD83D\uDFE9");
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
}
