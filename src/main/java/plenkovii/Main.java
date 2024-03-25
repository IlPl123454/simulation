package plenkovii;

import plenkovii.action.init.*;
import plenkovii.entity.*;
import plenkovii.entity.Rock;
import plenkovii.entity.Tree;
import plenkovii.entity.immobile.Grass;
import plenkovii.entity.mobile.Creature;
import plenkovii.entity.mobile.Herbivore;
import plenkovii.entity.mobile.Predator;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Map map = new Map();
        MapConsoleRenderer renderer = new MapConsoleRenderer();
        Actions action = new Actions();
        Scanner scanner = new Scanner(System.in);


//        action.setNewEntitiesAtRandomCoordinate(map, Tree.class, 10);
//        action.setNewEntitiesAtRandomCoordinate(map, Rock.class, 10);
//        action.setNewEntitiesAtRandomCoordinate(map, Grass.class, 10);
//        action.setNewEntitiesAtRandomCoordinate(map, Herbivore.class, 5);
//        action.setNewEntitiesAtRandomCoordinate(map, Predator.class, 2);

//        map.setEntity(new Herbivore(new Coordinates(1,1)));
//        map.setEntity(new Predator(new Coordinates(10,10)));

        GrassGenerateAction grassGenerateAction = new GrassGenerateAction();
        grassGenerateAction.perform(map, Grass.class);

        EntityGenerateAction entityGenerator = new EntityGenerateAction();
        entityGenerator.perform(map, Grass.class);
        entityGenerator.perform(map, Tree.class);
        entityGenerator.perform(map, Rock.class);
        entityGenerator.perform(map, Herbivore.class);
        entityGenerator.perform(map, Predator.class);


        String input;
        int num = 1;

        while (true) {
            ArrayList<Coordinates> coordinatesWithCreatures = new ArrayList<>();
//            renderer.render(map);
            Set<Coordinates> pathCoordinatesHerbivore = new HashSet<>();
            Set<Coordinates> pathCoordinatesPredator = new HashSet<>();

            for (Entity entity : map.getEntities().values()) {
                if (entity.getClass() == Predator.class || entity.getClass() == Herbivore.class) {
                    coordinatesWithCreatures.add(entity.coordinates);
                }
            }

            for (Coordinates coordinatesWithCreature : coordinatesWithCreatures) {
                Creature creature = (Creature) map.getEntity(coordinatesWithCreature);
                creature.decreaseHP(2);
                if (creature.getHP() == 0) {
                    creature.removeIfDead(map);
                    System.out.println("Животное умерло от голода или его убили");
                    continue;
                }
                if (creature.getClass() == Predator.class) {
                    pathCoordinatesPredator.add(creature.coordinates);
                    Deque<PathNode> path = creature.findPathAStar(map, Herbivore.class);

                    if (path == null) {
                        continue;
                    }
                    if (!path.isEmpty()) {
                        creature.moveCreature(map, path);
                    }
                    creature.interact(map);

                    for (PathNode node : path) {
                        pathCoordinatesPredator.add(node.coordinates);
                    }

                } else if (creature.getClass() == Herbivore.class) {
                    pathCoordinatesHerbivore.add(creature.coordinates);
                    Deque<PathNode> path = creature.findPathAStar(map, Grass.class);

                    if (path == null) {
                        continue;
                    }
                    if (!path.isEmpty()) {
                        creature.moveCreature(map, path);
                    }
                    creature.interact(map);

                    for (PathNode node : path) {
                        pathCoordinatesHerbivore.add(node.coordinates);
                    }
                }
            }
            renderer.renderPath(map, pathCoordinatesPredator, pathCoordinatesHerbivore);


            int grass = 0;
            int herbivore = 0;
            int predator = 0;
            for (Entity entity : map.getEntities().values()) {
                if (entity instanceof Grass) {
                    grass++;
                } else if (entity instanceof Herbivore) {
                    herbivore++;
                } else if (entity instanceof Predator) {
                    predator++;
                }
            }
            System.out.println(num);
            num++;

            if (grass < 5) {
                action.setNewEntitiesAtRandomCoordinate(map, Grass.class, 5);
            }
            if (herbivore == 0) {
                System.out.println("Все травоядные погибли");
                break;
//                action.setNewEntitiesAtRandomCoordinate(map, Herbivore.class, 2);
            }
            if (predator == 0) {
                System.out.println("Все хищники погибли от голода");
                break;
//                action.setNewEntitiesAtRandomCoordinate(map, Predator.class, 2);
            }

//            System.out.println("Начать новый цикл?");
//            do {
//                System.out.print("Введите Y или N: ");
//                input = scanner.nextLine().toLowerCase();
//
//            } while (!input.equals("y") && !input.equals("n"));
//
//            if (input.equals("n")) {
//                break;
//            }
        }
    }
}
