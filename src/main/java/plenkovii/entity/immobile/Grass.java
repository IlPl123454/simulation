package plenkovii.entity.immobile;

import plenkovii.Coordinates;
import plenkovii.entity.Entity;

public class Grass extends Entity {
    public Grass(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public Entity createNew(Coordinates coordinates) {
        return new Grass(coordinates);
    }
}
