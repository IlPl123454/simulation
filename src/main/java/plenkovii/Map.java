package plenkovii;

import plenkovii.entity.*;

import java.util.HashMap;
import java.util.Random;

public class Map {
    public static final int height = 10;
    public static final int length = 15;
    public HashMap<Coordinates, Entity> entities = new HashMap<Coordinates, Entity>();

    public void setRandomEntities() {
        Random random = new Random();
        // randomly set number of trees (25-50)
        int treesNumber = 10 + random.nextInt(5);
        //set trees
        setNewEntitiesAtRandomCoordinate(Tree.class, treesNumber);
        setNewEntitiesAtRandomCoordinate(Rock.class, treesNumber);
        setNewEntitiesAtRandomCoordinate(Grass.class, treesNumber);
        setNewEntitiesAtRandomCoordinate(Predator.class, treesNumber);
    }


    public void setNewEntitiesAtRandomCoordinate(Class<? extends Entity> entityClass, int amount) {
        Random random = new Random();

        for (int i = 0; i < amount; i++) {
            Coordinates coordinates = new Coordinates(1 + random.nextInt(height), 1 +random.nextInt(length));

            if (entities.containsKey(coordinates)) {
                i--;
            } else {
                try {
                    Entity entity = entityClass.getDeclaredConstructor(Coordinates.class).newInstance(coordinates);
                    entities.put(coordinates, entity);
                } catch (Exception e) {
                    System.out.println("Возникло исключение при создании объекта");
                }
            }
        }
    }

    public void setEntity(Entity entity) {
        entities.put(entity.coordinates, entity);
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public void moveCreature(Coordinates from, Coordinates to) {
        Entity entity = getEntity(from);

        entities.remove(from);
        entities.put(to,entity);


    }
}
