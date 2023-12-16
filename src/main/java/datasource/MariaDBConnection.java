package datasource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MariaDBConnection {
    private static Connection conn = null;

    public static Connection getInstance() {
        if (conn==null) {
            // connect if necessary
            try {
                conn = DriverManager.getConnection(
                        "jdbc:mariadb://localhost:3306/currency?user=appuser&password=password");
            } catch (SQLException e) {
                System.out.println("Connection failed.");
                e.printStackTrace();
            }
            return conn;
        }
        else {
            return conn;
        }
    }

    public static void terminate() {
        try {
            getInstance().close();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

}
