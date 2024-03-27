package plenkovii;

import plenkovii.action.MoveAction;
import plenkovii.action.init.EntityGenerateAction;
import plenkovii.entity.Rock;
import plenkovii.entity.Tree;
import plenkovii.entity.immobile.Grass;
import plenkovii.entity.mobile.Herbivore;
import plenkovii.entity.mobile.Predator;

import java.lang.reflect.InvocationTargetException;

public class Simulation {
    private Map map = new Map();
    private MapConsoleRenderer renderer = new MapConsoleRenderer();
    private EntityGenerateAction entityGenerator = new EntityGenerateAction();
    private MoveAction moveAction = new MoveAction();
    private int moveCount = 0;


    public void initSimulation() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        entityGenerator.perform(map, Grass.class);
        entityGenerator.perform(map, Tree.class);
        entityGenerator.perform(map, Rock.class);
        entityGenerator.perform(map, Herbivore.class);
        entityGenerator.perform(map, Predator.class);
    }


    public void nextTurn(Map map) {
        moveAction.perform(map);
        map.updateMapData();
        moveCount++;
    }


    public void generateNewGrass() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        double grassRate = map.getNumberOfEntityOnMapByClass(Grass.class) / (map.HEIGHT * map.LENGTH);
        if (grassRate < map.getSpawnRateByClass(Grass.class)) {
            entityGenerator.perform(map, Grass.class);
        }
    }

    public void printMoveCount() {
        System.out.println(moveCount);
    }

}
