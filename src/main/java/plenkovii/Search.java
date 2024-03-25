package plenkovii;

import plenkovii.entity.Entity;
import plenkovii.entity.mobile.Creature;

import java.util.*;

public class Search {
    public static Deque<PathNode> findPathAStar(Map map, Creature sourceCreature, Class<? extends Entity> targetClass) {
        Coordinates targetCoordinates = findTargetCoordinatesBTSSearch(map, sourceCreature.coordinates, targetClass);
        if (targetCoordinates == null) {
            return null;
        }

        PriorityQueue<PathNode> nodes = new PriorityQueue<>();
        Set<Coordinates> openSet = new HashSet<>();
        Set<Coordinates> closeSet = new HashSet<>();
        PathNode sourceNode = new PathNode(sourceCreature.coordinates, null);
        nodes.add(sourceNode);
        openSet.add(sourceNode.coordinates);

        while (!nodes.isEmpty()) {
            PathNode node = nodes.poll();
            openSet.remove(node.coordinates);
            closeSet.add(node.coordinates);

            if (node.coordinates.equals(targetCoordinates)) {
                return node.getPath(sourceNode);
            } else {
                List<PathNode> surroundPathNodes = node.getSurroundPathNodes();

                for (PathNode pathNode : surroundPathNodes) {
                    if (pathNode.isAvailableToMove(map, targetClass)) {
                        if (closeSet.contains(pathNode.coordinates)) {
                            continue;
                        }
                        if (openSet.contains(pathNode.coordinates)) {
                            pathNode.updateDistanceFromSource();
                            pathNode.setDistanceToTarget(targetCoordinates);

                            PathNode nodeInOpenSet = Search.getNodeByCoordinates(nodes, pathNode.coordinates);
                            if (nodeInOpenSet == null) {
                                System.out.println("problem");
                            }

                            if (pathNode.getDistanceFromSource() < nodeInOpenSet.getDistanceFromSource()) {
                                nodes.remove(nodeInOpenSet);
                                nodes.add(pathNode);
                            }
                        } else {
                            pathNode.updateDistanceFromSource();
                            pathNode.setDistanceToTarget(targetCoordinates);
                            nodes.add(pathNode);
                            openSet.add(pathNode.coordinates);
                        }
                    }
                }
            }
        }
        return null;
    }

    private static PathNode getNodeByCoordinates(PriorityQueue<PathNode> nodes, Coordinates coordinates) {
        for (PathNode node : nodes) {
            if (coordinates.equals(node.coordinates)) {
                return node;
            }
        }
        return null;
    }


    public static Deque<PathNode> findPathBTS(Map map, Creature sourceCreature, Class<? extends Entity> targetClass) {
        // create source node
        PathNode sourceNode = new PathNode(sourceCreature.coordinates, null);
        // create set of visited coordinates
        Set<Coordinates> visitedCoordinates = new HashSet<>();
        visitedCoordinates.add(sourceNode.coordinates);
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
            Coordinates coordinates = node.coordinates;
            visitedCoordinates.add(coordinates);
            Entity entity = map.getEntity(coordinates);
            if (map.getEntity(coordinates) != null && entity.getClass() == targetClass) {
                return node.getPath(sourceNode);
            } else {
                List<PathNode> surroundNodes = node.getSurroundPathNodes();
                for (PathNode surroundNode : surroundNodes) {
                    if (!visitedCoordinates.contains(surroundNode.coordinates)
                            && !queue.contains(surroundNode)
                            && surroundNode.isAvailableToMove(map, targetClass)) {
                        queue.add(surroundNode);
                    }
                }
            }
        }
        return null;
    }

    public static Coordinates findTargetCoordinatesBTSSearch(Map map, Coordinates from, Class<? extends Entity> targetClass) {
// create source node
        PathNode sourceNode = new PathNode(from, null);
        // create set of visited coordinates
        Set<Coordinates> visitedCoordinates = new HashSet<>();
        visitedCoordinates.add(sourceNode.coordinates);
        // create queue to set next nodes
        Deque<PathNode> queue = new ArrayDeque<>();
        Set<Coordinates> coordinatesInQueue = new HashSet<>();
        // add surrounded squares
        List<PathNode> surroundPathNodes = sourceNode.getSurroundPathNodes();
        for (PathNode surroundPathNode : surroundPathNodes) {
            if (surroundPathNode.isAvailableToMove(map,targetClass)) {
                queue.add(surroundPathNode);
                coordinatesInQueue.add(surroundPathNode.coordinates);
            }
        }
        // search
        while (!queue.isEmpty()) {
            // poll first coordinate
            PathNode node = queue.poll();
            Coordinates coordinates = node.coordinates;
            visitedCoordinates.add(coordinates);
            Entity entity = map.getEntity(coordinates);
            if (map.getEntity(coordinates) != null && entity.getClass() == targetClass) {
                return node.coordinates;
            } else {
                List<PathNode> surroundNodes = node.getSurroundPathNodes();
                for (PathNode surroundNode : surroundNodes) {
                    if (!visitedCoordinates.contains(surroundNode.coordinates)
                            && !coordinatesInQueue.contains(surroundNode.coordinates)
                            && surroundNode.isAvailableToMove(map, targetClass)) {
                        queue.add(surroundNode);
                        coordinatesInQueue.add(surroundNode.coordinates);
                    }
                }
            }
        }
        return null;
    }
}


