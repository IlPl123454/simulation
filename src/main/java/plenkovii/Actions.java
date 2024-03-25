package plenkovii;

import plenkovii.entity.Entity;

import java.util.Random;

public class Actions {
    public void setNewEntitiesAtRandomCoordinate(Map map, Class<? extends Entity> entityClass, int amount) {
        Random random = new Random();

        for (int i = 0; i < amount; i++) {
            Coordinates coordinates = new Coordinates(1 + random.nextInt(map.HEIGHT), 1 +random.nextInt(map.LENGTH));

            if (map.getEntities().containsKey(coordinates)) {
                i--;
            } else {
                try {
                    Entity entity = entityClass.getDeclaredConstructor(Coordinates.class).newInstance(coordinates);
                    map.addEntity(coordinates,entity);
                } catch (Exception e) {
                    System.out.println("Возникло исключение при создании объекта");
                }
            }
        }
    }
}
