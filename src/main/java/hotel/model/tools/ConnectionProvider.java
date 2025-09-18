package hotel.model.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import lombok.Getter;
public  class ConnectionProvider {
    @Getter

    private static ConnectionProvider provider = new ConnectionProvider();
    private ConnectionProvider() {}

    public  Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "138067sh"
        );
    }
}
