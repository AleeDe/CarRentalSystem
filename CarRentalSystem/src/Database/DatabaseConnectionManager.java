package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    private static final String url = "jdbc:mysql://localhost:3306/carrentalsystem";
    private static final String username = "root";
    private static final String password = "adminali";

    private static DatabaseConnectionManager instance;
    private Connection connection;

    private DatabaseConnectionManager(){

    }

    public static DatabaseConnectionManager getInstance(){
        if (instance == null){
            synchronized (DatabaseConnectionManager.class){
                if (instance == null){
                    instance = new DatabaseConnectionManager();
                }
            }
        }
        return instance;
    }

    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        if(connection == null){
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return connection;
    }

    public void closeConnection(Connection conn){
        if(conn != null){
            try {

                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
