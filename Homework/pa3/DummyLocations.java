package pa3;

import java.util.List;

/**
 * This class can be used to generate a small collection of Locations, so that
 * you can test the behaviour of your code using Locations and Connections.
 * This also provides you with example code showing how to construct maps of
 * your own.
 *
 * @author Dmitry Paramonov
 */
public class DummyLocations {
    /**
     * Generates a standard map (see the handout for details).
     * @return The standard demo map.
     */
    public static List<Location> dummyLocations() {
        Location a = new Location("Arts and Sciences Centre");
        Location b = new Location("Business School");
        Location c = new Location("City Center");
        Location d = new Location("Department of Computer Science");
        Location e = new Location("Entrance to Tunnel");
        Location f = new Location("Fairgrounds");
        Connection connection;
        connection = new Connection(a, b, 10.0);
        a.addConnection(connection); b.addConnection(connection.reverse());
        connection = new Connection(a, c, 20.0);
        a.addConnection(connection); c.addConnection(connection.reverse());
        connection = new Connection(a, d, 3.0);
        a.addConnection(connection); d.addConnection(connection.reverse());
        connection = new Connection(b, c, 5.0);
        b.addConnection(connection); c.addConnection(connection.reverse());
        connection = new Connection(b, d, 12.0);
        b.addConnection(connection); d.addConnection(connection.reverse());
        connection = new Connection(c, d, 8.0);
        c.addConnection(connection); d.addConnection(connection.reverse());
        connection = new Connection(c, e, 20.0);
        c.addConnection(connection); e.addConnection(connection.reverse());
        connection = new Connection(c, f, 25.0);
        c.addConnection(connection); f.addConnection(connection.reverse());
        connection = new Connection(e, f, 60.0);
        e.addConnection(connection); f.addConnection(connection.reverse());
        return List.of(a, b, c, d, e, f);
    }
}
