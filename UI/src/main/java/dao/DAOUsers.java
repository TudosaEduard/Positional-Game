package dao;

import java.sql.Connection;
import java.sql.ResultSet;

public class DAOUsers {
    private Connection connection;

    public DAOUsers(Connection connection){
        try{
            this.connection = connection;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error creating AlbumsDAO!");
        }
    }

    public void createTable(){
        try{
            connection.createStatement().execute("CREATE TABLE IF NOT EXISTS users(" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "username VARCHAR(50) NOT NULL," +
                    "password VARCHAR(50) NOT NULL," +
                    "email VARCHAR(50) NOT NULL," +
                    "PRIMARY KEY(id))");
            System.out.println("Table created!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error creating table!");
        }
    }

    public Boolean existsTable(){
        try{
            ResultSet resultSet = connection.createStatement().executeQuery("SHOW TABLES LIKE 'users'");
            if(resultSet.next()){
                System.out.println("Table exists!");
                return true;
            } else {
                System.out.println("Table does not exist!");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error checking table!");
            return false;
        }
    }

    public void dropTable(){
        try{
            connection.createStatement().execute("DROP TABLE IF EXISTS users");
            System.out.println("Table dropped!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error dropping table!");
        }
    }

    public void printTable(){
        try{
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users");
            while(resultSet.next()){
                System.out.println("id: " + resultSet.getInt("id") +
                        ", username: " + resultSet.getString("username") +
                        ", password: " + resultSet.getString("password") +
                        ", email: " + resultSet.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error printing table!");
        }
    }

    public void insertUser(String username, String password, String email){
        try{
            connection.createStatement().execute("INSERT INTO users(username, password, email) VALUES('" + username + "', '" + password + "', '" + email + "')");
            connection.commit();
            System.out.println("User inserted!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error inserting user!");
        }
    }

    public void deleteUser(int id){
        try{
            connection.createStatement().execute("DELETE FROM users WHERE id = " + id);
            System.out.println("User deleted!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error deleting user!");
        }
    }

    public void updateUser(int id, String username, String password, String email){
        try{
            connection.createStatement().execute("UPDATE users SET username = '" + username + "', password = '" + password + "', email = '" + email + "' WHERE id = " + id);
            System.out.println("User updated!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error updating user!");
        }
    }

    public Boolean existsUser(String username, String email){
        try{
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users WHERE username = '" + username + "' OR email = '" + email + "'");
            if(resultSet.next()){
                System.out.println("User exists!");
                return true;
            } else {
                System.out.println("User does not exist!");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error checking user!");
            return false;
        }
    }

    public Boolean existsPassword(String password){
        try{
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users WHERE password = '" + password + "'");
            if(resultSet.next()){
                System.out.println("Password exists!");
                return true;
            } else {
                System.out.println("Password does not exist!");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error checking password!");
            return false;
        }
    }

    public Boolean checkLoginByUsername(String username, String password){
        try{
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'");
            if(resultSet.next()){
                System.out.println("Login successful!");
                return true;
            } else {
                System.out.println("Login failed!");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error checking login!");
            return false;
        }
    }

    public Boolean checkLoginByEmail(String email, String password){
        try{
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users WHERE email = '" + email + "' AND password = '" + password + "'");
            if(resultSet.next()){
                System.out.println("Login successful!");
                return true;
            } else {
                System.out.println("Login failed!");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error checking login!");
            return false;
        }
    }

    public String getUsernameByEmail(String email){
        try{
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users WHERE email = '" + email + "'");
            if(resultSet.next()){
                System.out.println("Username found!");
                return resultSet.getString("username");
            } else {
                System.out.println("Username not found!");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error checking username!");
            return null;
        }
    }

    public String getEmailByUsername(String username){
        try{
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users WHERE username = '" + username + "'");
            if(resultSet.next()){
                System.out.println("Email found!");
                return resultSet.getString("email");
            } else {
                System.out.println("Email not found!");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error checking email!");
            return null;
        }
    }
}
