package pa3;

import java.util.LinkedList;
import java.util.Comparator;import java.util.Iterator;

public class Path implements Iterable<Location> {
    private double length;
    private LinkedList<Location> path;
    private Location end;

    /**
     *
     * @param start the first location along the path
     */
    public Path(Location start) {
        path = new LinkedList<>();
        path.add(start);
        end = start;
        length = 0.0;
    }

    public Path extend(Connection connection) {
        if (connection.getStart() != end){
            throw new EdgeConsistencyException("Start of new connection does not equal end of the current path", connection);
        }
        Path new_path = new Path(path.getFirst());
        new_path.path = new LinkedList<>(path);
        new_path.path.add(connection.getEnd());
        new_path.length = length + connection.getLength();
        new_path.end = connection.getEnd();
        return new_path;
    }

    public double getLength() {
        return length;
    }

    public Location getEnd() {
        return end;
    }

    public Iterator<Location> iterator() {
        return path.iterator();
    }

    public static class PathComparator implements Comparator<Path> {
        @Override
        public int compare(Path p1, Path p2) {
            return Double.compare(p1.length, p2.length);

        }
    }
}
