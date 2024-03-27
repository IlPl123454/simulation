package plenkovii.entity.mobile;

import plenkovii.Coordinates;
import plenkovii.Map;
import plenkovii.entity.Entity;

public class Predator extends Creature {
    public final int ATTACK_POWER = 15;

    public Predator(Coordinates coordinates) {
        super(coordinates);
    }

    private void attackHerbivore(Map map) {
        Coordinates herbivoreCoordinates = this.getNearCoordinateWithClass(map, Herbivore.class);
        if (herbivoreCoordinates != null) {
            Entity entity = map.getEntity(herbivoreCoordinates);
            ((Herbivore) entity).decreaseHP(ATTACK_POWER);
            if (((Herbivore) entity).getHP() == 0) {
                this.increaseHP(25);
            }
        }
    }

    @Override
    public void interact(Map map) {
        attackHerbivore(map);
    }

    @Override
    public Class<? extends Entity> getTargetEntity() {
        return Herbivore.class;
    }


    @Override
    public Entity createNew(Coordinates coordinates) {
        return new Predator((coordinates));
    }
}