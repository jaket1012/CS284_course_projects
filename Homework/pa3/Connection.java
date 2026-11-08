package pa3;

/**
 * A Connection in a map. This can be a road between two landmarks, a highway
 * between two cities, or even a subway line between two stations. Abstractly,
 * a Connection is just a direct link between two Locations, with an associated
 * travel cost, represented by a length.
 *
 * A Connection is immutable, meaning that any modifications have to take the
 * form of a newly created Connection.
 *
 * Connections are directional: They have a clear start and end. A Connection
 * going one way is not equivalent to a Connection going the other.
 *
 * @author Dmitry Paramonov
 */
public class Connection {
    private Location start;
    private Location end;
    private double length;

    /**
     * Creates a new Connection based on its full specifications.
     * @param start The starting Location for this Connection.
     * @param end The ending Location for this Connection.
     * @param length The length of this Connection.
     */
    public Connection(Location start, Location end, double length) {
        this.start = start;
        this.end = end;
        this.length = length;
    }

    /**
     * Creates a Connection corresponding to the reversal of this Connection.
     * This Connection has the same length as the current one,
     * but its start and endpoints are swapped.
     * @return The reversed Connection.
     */
    public Connection reverse() {
        return new Connection(this.end, this.start, this.length);
    }

    /**
     * Gives the starting point of this Connection.
     * @return The starting Location of this Connection.
     */
    public Location getStart() {
        return start;
    }

    /**
     * Gives the endpoint of this Connection.
     * @return The ending Location of this Connection.
     */
    public Location getEnd() {
        return end;
    }

    /**
     * Gives the length of this Connection.
     *
     * Note that the length might not be the physical length in some
     * circumstances. For example, in a map of a train network, the length
     * might represent the time it takes a train to get from one station to
     * another. In general, this represents the quantity you wish to minimize
     * as you travel through the map.
     * @return The length of this Connection.
     */
    public double getLength() {
        return length;
    }
}
