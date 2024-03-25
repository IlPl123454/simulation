package plenkovii.action.init;

import plenkovii.Coordinates;
import plenkovii.Map;
import plenkovii.action.Action;
import plenkovii.entity.Entity;

import java.lang.reflect.InvocationTargetException;

public class EntityGenerateAction extends Action {
    @Override
    public void perform(Map map, Class<? extends Entity> entityClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        double mapSize = map.HEIGHT * map.LENGTH;

        while (map.getNumberOfEntityOnMapByClass(entityClass) < map.getSpawnRateByClass(entityClass) * mapSize) {
            Coordinates coordinates = map.getRandomEmptyCoordinate();
            Entity entity = entityClass.getDeclaredConstructor(Coordinates.class).newInstance(coordinates);
            map.addEntity(coordinates, entity);
            map.increaseCounter(entity);
        }
    }
}



