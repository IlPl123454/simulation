package plenkovii;

import plenkovii.entity.Entity;

import java.util.*;

public class PathNode implements Comparable<PathNode>{
    public final Coordinates coordinates;
    private PathNode previousNode;
    private  int distanceFromSource;
    private int distanceToTarget;


    public PathNode(Coordinates coordinates, PathNode previousNode) {
        this.coordinates = coordinates;
        this.previousNode = previousNode;
    }

    public PathNode(Coordinates coordinates, PathNode previousNode, int distanceFromTarget, int distanceToTarget) {
        this.coordinates = coordinates;
        this.previousNode = previousNode;
        this.distanceFromSource = distanceFromTarget;
        this.distanceToTarget = distanceToTarget;
    }


    public void setDistanceFromSource() {
            this.distanceFromSource = this.countDistanceFromSource();
    }

    public void updateDistanceFromSource() {
        if (distanceFromSource == 0) {
            setDistanceFromSource();
        }

        int newDistance = this.countDistanceFromSource();
        if (newDistance < this.distanceFromSource) {
            this.distanceFromSource = this.countDistanceFromSource();
        }
    }

    public int getDistanceFromSource() {
        return distanceFromSource;
    }

    public void setDistanceToTarget(Coordinates target) {
        this.distanceToTarget = getDistanceBetweenCoordinates(this.coordinates, target);
    }

    private int countDiagonalShifts() {
        int result = 0;
        PathNode node = this;
        while (node.previousNode != null) {
            if (this.isDiagonalShift()) {
                result++;
                node = node.previousNode;
            }
        }
        return result;
    }

    private int countDistanceFromSource() {
        if (previousNode == null) {
            return 0;
        } else {
            if (this.isDiagonalShift()) {
                if (this.countDiagonalShifts() % 2 == 1) {
                    return 5 + previousNode.getDistanceFromSource();
                } else {
                    return 10 + previousNode.getDistanceFromSource();
                }
            } else {
                return 5 + previousNode.getDistanceFromSource();
            }
        }
    }

    private boolean isDiagonalShift() {
        if (previousNode != null) {
            return this.coordinates.x != previousNode.coordinates.x && this.coordinates.y != previousNode.coordinates.y;
        } else {
            return false;
        }
    }

    public List<PathNode> getSurroundPathNodes() {
        List<PathNode> set = new ArrayList<>();
        int[][] shifts = new int[][] {
                {1, -1},
                {1, 1},
                {-1, -1},
                {-1, 1},
                {0, -1},
                {0, 1},
                {1, 0},
                {-1, 0}
        };

        for (int[] shift : shifts) {
            PathNode node = new PathNode(new Coordinates(
                    this.coordinates.x + shift[0],
                    this.coordinates.y + shift[1]),
                    this);
            set.add(node);
        }
        return set;
    }

    public boolean isAvailableToMove(Map map) {
        boolean isInMap = false;
        if ((this.coordinates.x > 0 && this.coordinates.y > 0)
                && (this.coordinates.x <= map.HEIGHT && this.coordinates.y <= map.LENGTH)) {
            isInMap = true;
        }

        boolean isCoordinateEmpty = true;
        if (map.getEntity(this.coordinates) != null) {
            isCoordinateEmpty = false;
        }

        return isCoordinateEmpty && isInMap;
    }

    public boolean isAvailableToMove(Map map, Class<? extends Entity> targetClass) {
        boolean isInMap = false;
        if ((this.coordinates.x > 0 && this.coordinates.y > 0)
                && (this.coordinates.x <= map.HEIGHT && this.coordinates.y <= map.LENGTH)) {
            isInMap = true;
        }

        boolean isCoordinateEmpty = true;
        if (map.getEntity(this.coordinates) != null && map.getEntity(this.coordinates).getClass() != targetClass) {
            isCoordinateEmpty = false;
        }

        return isCoordinateEmpty && isInMap;
    }


    public Deque<PathNode> getPath(PathNode source) {
        Deque<PathNode> path = new ArrayDeque<>();
        PathNode node = this.previousNode;
        while (node != source) {
            path.add(node);
            node = node.previousNode;
        }
        return path;
    }

    public int getDistanceBetweenCoordinates(Coordinates from, Coordinates to) {
        int dx = from.x - to.x;
        int dy = from.y - to.y;
//        double distance = Math.sqrt(Math.pow(dx * 5, 2) + Math.pow(dy * 5, 2));
//
//        return (int) Math.round(distance);


        return (Math.abs(dx) + Math.abs(dy)) * 5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PathNode node = (PathNode) o;

        if (!coordinates.equals(node.coordinates)) return false;
        return Objects.equals(previousNode, node.previousNode);
    }

    @Override
    public int hashCode() {
        int result = coordinates.hashCode();
        result = 31 * result + (previousNode != null ? previousNode.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(PathNode o) {
            return (this.distanceFromSource + this.distanceToTarget) - (o.distanceFromSource + o.distanceToTarget);
    }

}
