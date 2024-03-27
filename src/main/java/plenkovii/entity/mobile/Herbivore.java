package plenkovii.entity.mobile;

import plenkovii.Coordinates;
import plenkovii.Map;
import plenkovii.entity.Entity;
import plenkovii.entity.immobile.Grass;

public class Herbivore extends Creature {
    public final int HP_FOR_EATING_GRASS = 20;

    public Herbivore(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public void interact(Map map) {
        this.eatGrass(map);
    }

    @Override
    public Class<? extends Entity> getTargetEntity() {
        return Grass.class;
    }


    private void eatGrass(Map map) {
        Coordinates grassCoordinate = this.getNearCoordinateWithClass(map, Grass.class);
        if (grassCoordinate != null) {
            map.getEntities().remove(grassCoordinate);
            this.increaseHP(HP_FOR_EATING_GRASS);
        }
    }

    @Override
    public Entity createNew(Coordinates coordinates) {
        return new Herbivore(coordinates);
    }


}
