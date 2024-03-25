package plenkovii.entity;

import plenkovii.Coordinates;

public class Rock extends Entity {
    public Rock(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public Entity createNew(Coordinates coordinates) {
        return new Rock(coordinates);
    }
}
