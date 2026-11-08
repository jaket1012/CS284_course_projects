package pa3;

public class EdgeConsistencyException extends RuntimeException {
    private Connection connection;
    public EdgeConsistencyException(String message, Connection connection) {
        super(message);
        this.connection = connection;
    }

    public Connection getRoad() {
        return connection;
    }
}
