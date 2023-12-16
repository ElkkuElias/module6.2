package dao;
import entity.currencies;
import java.sql.*;
import datasource.MariaDBConnection;
import java.util.*;

public class CurrencyDao {

public double find(String abbreviation) throws SQLException {
    Connection conn = MariaDBConnection.getInstance();
    if (conn == null) {
        return -0.999;
    }
    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM currencies WHERE abbreviation = ?");
    stmt.setString(1, abbreviation);
    ResultSet rs = stmt.executeQuery();
    if (rs.next()) {
        return rs.getDouble("rate");
    } else {
        throw new IllegalArgumentException("No currency found with the provided abbreviation");
    }




}}
