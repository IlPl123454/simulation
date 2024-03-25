package plenkovii;



import plenkovii.entity.mobile.Creature;

import java.util.Deque;

public class Move {
    public Creature creature;
    public Deque<PathNode> path;

    public Coordinates coordinates;

    public Move(Creature creature, Deque<PathNode> path) {
        this.creature = creature;
        this.path = path;
    }
    public Move(Creature creature, Coordinates coordinates) {
        this.creature = creature;
        this.coordinates = coordinates;
    }
}
