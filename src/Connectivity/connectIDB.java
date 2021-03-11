package Connectivity;

import java.sql.Connection;
import java.sql.SQLException;

public interface connectIDB {
    Connection getConnection() throws SQLException, ClassNotFoundException;
}
