package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class options {
    public static Connection get_connection() {
        /*
         * Connecting to my external DMBS (PostgreSQL) using Java
         */
        Connection connection=null;
        try {
            Class.forName("org.postgresql.Driver");
            connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc","postgres","shamiko");
            if (connection!=null){
                System.out.println("Connection OK");
            }else {
                System.out.println("Connection Failed");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return connection;
    }
    public static Connection connection;

}
