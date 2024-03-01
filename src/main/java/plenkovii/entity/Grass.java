package plenkovii.entity;

import plenkovii.Coordinates;

public class Grass extends Entity {
    public Grass(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public Entity createNew(Coordinates coordinates) {
        return new Grass(coordinates);
    }
}
