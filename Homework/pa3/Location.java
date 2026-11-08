package pa3;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a Location in a map. This could be a landmark in a
 * city, a city in a country, or a subway station. Such a location is then
 * connected to other locations using Connections. This Connection is used to
 * implement the StevensMaps shortest path algorithm.
 *
 * @author Dmitry Paramonov
 */
public class Location {
    private String name;
    private List<Connection> outgoingConnections;

    /**
     * Creates a Location with no Connections and the given name.
     *
     * @param name The name associated to this Location. This should be unique
     *             in a given map.
     */
    public Location(String name) {
        this.name = name;
        this.outgoingConnections = new LinkedList<>();
    }

    /**
     * Gives the name associated to this Location.
     * @return The name of this Location.
     */
    public String getName() {
        return name;
    }

    /**
     * Adds a Connection to this Location, linking it to another Location.
     * This mutates the existing Location, but does not affect the destination.
     * <p>
     * If the given Connection does not begin at this Location, an Exception
     * is thrown.
     * @param connection The Connection to be added to this Location.
     */
    public void addConnection(Connection connection) {
        if (connection.getStart() != this) {
            throw new EdgeConsistencyException(
                    String.format("Connection added to Location %s does not originate there!", this.name),
                    connection);
        }
        this.outgoingConnections.add(connection);
    }

    /**
     * Gives a view of the Connections out of this Location in the form of an
     * Iterable. There are no guarantees on the ordering of these Connections.
     * @return An Iterable containing all Connections added to this Location.
     */
    public Iterable<Connection> getOutgoingConnections() {
        return outgoingConnections;
    }
}
