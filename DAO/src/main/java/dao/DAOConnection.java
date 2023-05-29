package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOConnection {
    private static final String url = "jdbc:mysql://localhost:3306/positional-game";
    private static final String user = "root";
    private static final String password = "";
    private static Connection connection = null;

    public DAOConnection(){}

    public static Connection getConnection(){
        if(connection == null){
            createConnection();
        }
        return connection;
    }

    public static void createConnection(){
        if(connection == null){
            try {
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
                System.out.println("Connection created!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Connection failed!");
            }
        }
    }

    public static void closeConnection(){
        if(connection != null){
            try {
                connection.close();
                System.out.println("Connection closed!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
