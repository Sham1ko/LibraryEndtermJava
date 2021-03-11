package Connectivity;

import java.sql.Connection;
import java.sql.SQLException;

public interface connectIDB {
    static Connection getConnection() throws SQLException, ClassNotFoundException {
        return null;
    }
}
