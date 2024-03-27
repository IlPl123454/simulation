package plenkovii;

import plenkovii.action.MoveAction;
import plenkovii.action.init.EntityGenerateAction;
import plenkovii.entity.Rock;
import plenkovii.entity.Tree;
import plenkovii.entity.immobile.Grass;
import plenkovii.entity.mobile.Herbivore;
import plenkovii.entity.mobile.Predator;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Simulation {
    private Map map = new Map();
    private MapConsoleRenderer renderer = new MapConsoleRenderer();
    private EntityGenerateAction entityGenerator = new EntityGenerateAction();
    private MoveAction moveAction = new MoveAction();

    private Scanner scanner = new Scanner(System.in);
    private int moveCount = 1;


    public void initSimulation() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        entityGenerator.perform(map, Grass.class);
        entityGenerator.perform(map, Tree.class);
        entityGenerator.perform(map, Rock.class);
        entityGenerator.perform(map, Herbivore.class);
        entityGenerator.perform(map, Predator.class);
    }

    public void nextTurn() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        printMoveCount();
        creaturesMove();
        renderMap();
        generateNewGrass();
    }


    public void creaturesMove() {
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
        System.out.println("Ход симуляции - " + moveCount);
    }

    public void renderMap() {
        renderer.render(map);
    }

    public boolean isSimulationStopped() {
        return map.getNumberOfEntityOnMapByClass(Herbivore.class) == 0
                || map.getNumberOfEntityOnMapByClass(Predator.class) == 0;
    }

    public void printResult() {
        System.out.println("Симуляция закончена");
        if (map.getNumberOfEntityOnMapByClass(Herbivore.class) == 0) {
            System.out.println("Все травоядные были съедены или погибки от голода");
        }
        if (map.getNumberOfEntityOnMapByClass(Predator.class) == 0) {
            System.out.println("Все хищники погибли от голода");
        }
    }

    public void chooseSimulationType() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String input;
        do {
            System.out.print("Введите 1 для симуляции одного хода или 2 для запуска цикла симуляции: ");
            input = scanner.next();

            if (input.charAt(0) == '1') {
                nextTurn();
            } else if (input.charAt(0) != '2') {
                chooseSimulationType();
            }
        } while (input.charAt(0) != '2');


    }

}
