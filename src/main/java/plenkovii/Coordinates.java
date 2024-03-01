package plenkovii;

import java.util.HashSet;
import java.util.Set;

public class Coordinates {
    public int x;
    public int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Set<Coordinates> getSurroundCoordinates() {
        Set<Coordinates> set = new HashSet<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) continue;

                Coordinates newCoordinates = new Coordinates(this.x + x, this.y + y);
                if ((newCoordinates.x > 0 && newCoordinates.y > 0)
                        && (newCoordinates.x <= Map.height && newCoordinates.y <= Map.length)) {
                    set.add(newCoordinates);
                }
            }
        }
        return set;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
