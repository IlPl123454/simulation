package plenkovii;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Simulation simulation = new Simulation();
        simulation.initSimulation();
        System.out.println("Добро пожаловать в симулицию!");
        simulation.renderMap();

        simulation.chooseSimulationType();

        while (!simulation.isSimulationStopped()) {
            simulation.nextTurn();
        }

        simulation.printResult();
    }
}
