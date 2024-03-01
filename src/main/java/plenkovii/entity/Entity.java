package plenkovii.entity;

import plenkovii.Coordinates;

public abstract class Entity {
    public Coordinates coordinates;

    public Entity(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public abstract Entity createNew(Coordinates coordinates);

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
