package plenkovii;

import plenkovii.entity.Creature;
import plenkovii.entity.Entity;

import java.util.*;

public class Search {
    public static Deque<PathNode> findPath(Map map, Creature sourceCreature, Class<? extends Entity> targetClass) {
        // create source node
        PathNode sourceNode = new PathNode(sourceCreature.coordinates, null);
        // create set of visited coordinates
        Set<Coordinates> visitedCoordinates = new HashSet<>();
        visitedCoordinates.add(sourceNode.getCoordinates());
        // create queue to set next nodes
        Deque<PathNode> queue = new ArrayDeque<>();
        // add surrounded squares
        List<PathNode> surroundPathNodes = sourceNode.getSurroundPathNodes();
        for (PathNode surroundPathNode : surroundPathNodes) {
            if (surroundPathNode.isAvailableToMove(map,targetClass)) {
                queue.add(surroundPathNode);
            }
        }
        // search
        while (!queue.isEmpty()) {
            // poll first coordinate
            PathNode node = queue.poll();
            Coordinates coordinates = node.getCoordinates();
            visitedCoordinates.add(coordinates);
            Entity entity = map.getEntity(coordinates);
            if (map.getEntity(coordinates) != null && entity.getClass() == targetClass) {
                return node.getPath(sourceNode);
            } else {
                List<PathNode> surroundNodes = node.getSurroundPathNodes();
                for (PathNode surroundNode : surroundNodes) {
                    if (!visitedCoordinates.contains(surroundNode.getCoordinates())
                            && !queue.contains(surroundNode)
                            && surroundNode.isAvailableToMove(map, targetClass)) {
                        queue.add(surroundNode);
                    }
                }
            }
        }
        return null;
    }

    private Deque<PathNode> BTSSearch ()
}
