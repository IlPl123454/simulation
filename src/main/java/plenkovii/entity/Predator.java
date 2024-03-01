package plenkovii.entity;

import plenkovii.Coordinates;
import plenkovii.Map;
import plenkovii.PathNode;

import java.util.Deque;
import java.util.Queue;

public class Predator extends Creature{
    int attackPower;
    public Predator(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public void makeMove(Map map, Deque<PathNode> path) {

    }


    @Override
    public Entity createNew(Coordinates coordinates) {
        return new Predator(coordinates);
    }
}
