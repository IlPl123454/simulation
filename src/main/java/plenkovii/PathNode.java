package plenkovii;

import plenkovii.entity.Entity;

import java.util.*;

public class PathNode {
    private final Coordinates coordinates;
    private final PathNode node;

    public PathNode(Coordinates coordinates, PathNode previousNode) {
        this.coordinates = coordinates;
        this.node = previousNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PathNode node = (PathNode) o;

        return Objects.equals(coordinates, node.coordinates);
    }

    @Override
    public int hashCode() {
        return coordinates != null ? coordinates.hashCode() : 0;
    }

    public PathNode getNode() {
        return this.node;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }


    public List<PathNode> getSurroundPathNodes() {
        List<PathNode> set = new ArrayList<>();
        int[][] shifts = new int[][] {
                {0, -1},
                {0, 1},
                {1, 0},
                {-1, 0},
                {1, -1},
                {1, 1},
                {-1, -1},
                {-1, 1}
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

    public boolean isAvailableToMove(Map map, Class<? extends Entity> targetClass) {
        boolean isInMap = false;
        if ((this.coordinates.x > 0 && this.coordinates.y > 0)
                && (this.coordinates.x <= Map.height && this.coordinates.y <= Map.length)) {
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
        PathNode node = this.node;
        while (node != source) {
            path.add(node);
            node = node.node;
        }
        return path;
    }
}
