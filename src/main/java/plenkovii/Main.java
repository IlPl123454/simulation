package plenkovii;

import plenkovii.entity.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map map = new Map();
        MapConsoleRenderer renderer = new MapConsoleRenderer();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int x = random.nextInt(Map.height) + 1;
        int y = random.nextInt(Map.length) + 1;

        map.setEntity(new Herbivore(new Coordinates(1,1)));
        map.setEntity(new Rock(new Coordinates(1, 3)));
        map.setRandomEntities();


//        map.setRandomEntities();

        String input;

        while (true) {
            Set<Coordinates> coordinates = new HashSet<>();
            ArrayList<Move> moves = new ArrayList<>();
            System.out.println("1");
            renderer.render(map);

            for (Entity entity : map.entities.values()) {
                if (entity.getClass() == Herbivore.class) {
                    Deque<PathNode> path = ((Herbivore)entity).findPath(map, Grass.class);
                    if (path != null) {
                        for (PathNode node : path) {
                            coordinates.add(node.getCoordinates());
                        }
                        Move move = new Move((Creature) entity, path);
                        moves.add(move);
                    } else {
                        System.out.println("Не могу найти путь к траве..");
                        System.exit(0);
                    }

                    Coordinates nearCoordinateWithGrass = ((Herbivore) entity).getNearCoordinateWithGrass(map);
                    if (nearCoordinateWithGrass != null) {
                        moves.add(new Move((Creature) entity, nearCoordinateWithGrass));
                    }
                }
            }
            System.out.println("2");
            renderer.renderPath(map,coordinates);

            if (!moves.isEmpty()) {
                for (Move move : moves) {
                    if (move.path != null) {
                        ((Creature)move.creature).makeMove(map, move.path);
                    } else if (move.coordinates != null) {
                        ((Creature)move.creature).makeMove(map, move.coordinates);
                    }
                }
            }
            int i = 0;
            for (Entity entity : map.entities.values()) {
                if (entity instanceof Grass) {
                    i++;
                }
            }
            if (i == 0) {
                map.setNewEntitiesAtRandomCoordinate(Grass.class, 2);
            }

            System.out.println("Начать новый цикл?");
            do {
                System.out.print("Введите Y или N: ");
                input = scanner.nextLine().toLowerCase();

            } while (!input.equals("y") && !input.equals("n"));

            if (input.equals("n")) {
                break;
            }
        }
    }
}
