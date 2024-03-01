package plenkovii.entity;

import plenkovii.Coordinates;
import plenkovii.Map;

import java.util.Set;

public class Herbivore extends Creature{
    public Herbivore(Coordinates coordinates) {
        super(coordinates);
    }

    public Coordinates getNearCoordinateWithGrass(Map map) {
        Set<Coordinates> coordinatesSet = this.coordinates.getSurroundCoordinates();
        for (Coordinates coordinates : coordinatesSet) {
            if (map.getEntity(coordinates) != null && map.getEntity(coordinates).getClass() == Grass.class) {
                return coordinates;
            }
        }
        return null;
    }

    public void eatGrass(Map map) {
        Coordinates grassCoordinate = this.getNearCoordinateWithGrass(map);
        if (this.getNearCoordinateWithGrass(map) != null) {
            this.makeMove(map, grassCoordinate);
        }
    }

    @Override
    public Entity createNew(Coordinates coordinates) {
        return new Herbivore(coordinates);
    }


}
