package plenkovii.entity;

import plenkovii.Coordinates;
import plenkovii.Map;
import plenkovii.PathNode;
import plenkovii.Search;

import java.util.*;

public abstract class Creature extends Entity {
    private int speed;
    private int HP;

    public Creature(Coordinates coordinates) {
        super(coordinates);
    }

    public void makeMove(Map map, Deque<PathNode> path) {
        if (!path.isEmpty()) {
            Coordinates from = coordinates;
            Coordinates to = path.pollLast().getCoordinates();
            map.moveCreature(from, to);
            setCoordinates(to);
        }
    }

    public void makeMove(Map map, Coordinates to) {
        Coordinates from = this.coordinates;
        map.moveCreature(from, to);
        this.setCoordinates(to);
    }


    public Coordinates search(Map map, Entity targetEntity) {
        PathNode node = new PathNode(coordinates, null);

        Set<Coordinates> visitedCoordinates = new HashSet<>();
        visitedCoordinates.add(coordinates);

        Queue<Coordinates> queue = new ArrayDeque<>();
        queue.addAll(coordinates.getSurroundCoordinates());

        // search
        while (!queue.isEmpty()) {

            Coordinates coordinates = queue.poll();
            Entity entity = map.getEntity(coordinates);

            if (visitedCoordinates.contains(coordinates) || entity == null) continue;

            if (targetEntity.getClass().isInstance(entity)){
                return coordinates;
            } else {
                visitedCoordinates.add(coordinates);
                Set<Coordinates> surroundCoordinates = coordinates.getSurroundCoordinates();
                queue.addAll(surroundCoordinates);
            }
        }
        return null;
    }

    public Deque<PathNode> findPath(Map map, Class<? extends Entity> targetClass) {
        return Search.findPath(map,this, targetClass);
    }

}
