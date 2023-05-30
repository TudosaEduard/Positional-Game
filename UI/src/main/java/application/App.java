package application;

import dao.DAOConnection;
import dao.User;
import gui.login.Login;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        DAOConnection.createConnection();
        Login login = new Login(null);
        User user = login.user;

        if(user != null){
            System.out.println("Successfully logged in of: " + user.getUsername() + " !");
        } else {
            System.out.println("Login canceled!");
        }
    }
}
