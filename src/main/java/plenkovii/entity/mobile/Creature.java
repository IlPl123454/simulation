package plenkovii.entity.mobile;

import plenkovii.Coordinates;
import plenkovii.Map;
import plenkovii.PathNode;
import plenkovii.Search;
import plenkovii.entity.Entity;

import java.util.*;

public abstract class Creature extends Entity {
    public final int SPEED = 20;
    private int HP = 50;
    public final int MAX_HP = 50;

    public Creature(Coordinates coordinates) {
        super(coordinates);
    }

    public void increaseHP(int amount) {
        HP += amount;
        if (HP > MAX_HP) {
            HP = MAX_HP;
        }
    }

    public void decreaseHP(int amount) {
        HP -= amount;
        if (HP < 0) {
            HP = 0;
        }
    }

    public void removeIfDead(Map map) {
        if (HP == 0) {
            map.getEntities().remove(this.coordinates);
        }
    }

    public int getHP() {
        return HP;
    }


    public void moveCreature(Map map, Coordinates to) {
        Coordinates from = this.coordinates;
        map.moveCreature(from, to);
        this.setCoordinates(to);
    }

    public void moveCreature(Map map, Deque<PathNode> path) {
        if (!path.isEmpty()) {
            Coordinates from = this.coordinates;
            Coordinates to;
            while (path.size() > 1) {
                if (path.getFirst().getDistanceFromSource() <= this.SPEED) {
                    break;
                } else {
                    path.pollFirst();
                }
            }
            to = path.pollFirst().coordinates;
            map.moveCreature(from, to);
            this.setCoordinates(to);
        }
    }

    public void makeMove(Map map) {
        // decrease HP
        this.decreaseHP(2);
        // check if dead
        this.removeIfDead(map);
        // find path to goal
        Deque<PathNode> path = this.findPathAStar(map, getTargetEntity());
        // move creature
        this.moveCreature(map, path);
        // attack/eat grass
        this.interact(map);
        // тут еще надо передать данные в лист координат, для печати пути, надо подумать как это сделать
    }

    public abstract void interact(Map map);

    public abstract Class<? extends Entity> getTargetEntity();

    public Coordinates getNearCoordinateWithClass(Map map, Class<? extends Entity> targetClass) {
        Set<Coordinates> coordinatesSet = this.coordinates.getSurroundCoordinates(map);
        for (Coordinates coordinates : coordinatesSet) {
            if (map.getEntity(coordinates) != null && map.getEntity(coordinates).getClass() == targetClass) {
                return coordinates;
            }
        }
        return null;
    }


    public Deque<PathNode> findPath(Map map, Class<? extends Entity> targetClass) {
        return Search.findPathBTS(map, this, targetClass);
    }

    public Deque<PathNode> findPathAStar(Map map, Class<? extends Entity> targetClass) {
        return Search.findPathAStar(map, this, targetClass);
    }
}
