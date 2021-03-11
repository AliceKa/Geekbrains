package serverside.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Singleton {

    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String DB = "jdbc:mysql://localhost:3306/authentication";
    static final String USER = "root";
    static final String PASSWORD = "root";
    static private Connection connection;
    private Singleton() {
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection == null) {
            connection = initConnection();
        }
        return connection;
    }

    public static Connection initConnection() throws SQLException, ClassNotFoundException {
        //Class.forName(DRIVER);
        return DriverManager.getConnection(DB, USER, PASSWORD);
    }
}
