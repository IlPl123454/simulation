package plenkovii;

import plenkovii.entity.*;
import plenkovii.entity.Rock;
import plenkovii.entity.Tree;
import plenkovii.entity.immobile.Grass;
import plenkovii.entity.mobile.Creature;
import plenkovii.entity.mobile.Herbivore;
import plenkovii.entity.mobile.Predator;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Map {
    public  final int HEIGHT = 10;
    public  final int LENGTH = 15;
    public  final double GRASS_SPAWN_RATE = 0.2;
    public  final double TREE_SPAWN_RATE = 0.1;
    public  final double ROCK_SPAWN_RATE = 0.1;
    public  final double HERBIVORE_SPAWN_RATE = 0.1;
    public  final double PREDATOR_SPAWN_RATE = 0.02;

    private int grassCounter;
    private int treeCounter;
    private int rockCounter;
    private int herbivoreCounter;
    private int predatorCounter;
    private List<Creature> creaturesOnMap;

    private HashMap<Coordinates, Entity> entities = new HashMap<Coordinates, Entity>();


    public HashMap<Coordinates, Entity> getEntities() {
        return entities;
    }

    public void addEntity(Coordinates coordinates, Entity entity) {
        entities.put(coordinates, entity);
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public List<Creature> getCreaturesOnMap() {
        return creaturesOnMap;
    }

    public void moveCreature(Coordinates from, Coordinates to) {
        Entity entity = getEntity(from);

        entities.remove(from);
        entities.put(to,entity);
    }

    public double getSpawnRateByClass(Class<? extends Entity> targetClass) {
        if (Grass.class.equals(targetClass)) {
            return GRASS_SPAWN_RATE;
        } else if (Rock.class.equals(targetClass)) {
            return ROCK_SPAWN_RATE;
        } else if (Tree.class.equals(targetClass)) {
            return TREE_SPAWN_RATE;
        } else if (Herbivore.class.equals(targetClass)) {
            return HERBIVORE_SPAWN_RATE;
        } else if (Predator.class.equals(targetClass)) {
            return PREDATOR_SPAWN_RATE;
        }
        return 0;
    }

    public double getNumberOfEntityOnMapByClass(Class<? extends Entity> targetClass) {
        if (Grass.class.equals(targetClass)) {
            return grassCounter;
        } else if (Rock.class.equals(targetClass)) {
            return rockCounter;
        } else if (Tree.class.equals(targetClass)) {
            return treeCounter;
        } else if (Herbivore.class.equals(targetClass)) {
            return herbivoreCounter;
        } else if (Predator.class.equals(targetClass)) {
            return predatorCounter;
        }
        return 0;
    }

    public Coordinates getRandomEmptyCoordinate() {
        Random random = new Random();
        Coordinates coordinates;
        do {
            coordinates = new Coordinates(1 + random.nextInt(HEIGHT), 1 +random.nextInt(LENGTH));
        } while (entities.containsKey(coordinates));
        return coordinates;
    }

    private void clearData() {
        grassCounter = 0;
        treeCounter = 0;
        rockCounter = 0;
        herbivoreCounter = 0;
        predatorCounter = 0;

        creaturesOnMap.clear();
    }

    public void updateMapData() {
        clearData();

        for (Entity entity : entities.values()) {
            increaseCounter(entity);

            if (Herbivore.class.equals(entity.getClass())) {
                creaturesOnMap.add((Creature) entity);
            } else if (Predator.class.equals(entity.getClass())) {
                creaturesOnMap.add((Creature) entity);
            }
        }
    }

    public void increaseCounter(Entity entity) {
        if (Grass.class.equals(entity.getClass())) {
            grassCounter++;
        } else if (Rock.class.equals(entity.getClass())) {
            rockCounter++;
        } else if (Tree.class.equals(entity.getClass())) {
            treeCounter++;
        } else if (Herbivore.class.equals(entity.getClass())) {
            herbivoreCounter++;
        } else if (Predator.class.equals(entity.getClass())) {
            predatorCounter++;
        }
    }
}
