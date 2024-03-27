package plenkovii.entity;

import plenkovii.Coordinates;

public class Tree extends Entity {

    public Tree(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public Entity createNew(Coordinates coordinates) {
        return new Tree(coordinates);
    }
}
