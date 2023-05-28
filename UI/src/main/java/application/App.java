package application;

import gui.login.Login;
import gui.register.Register;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Login login = new Login(null);
        User user = login.user;

        if(user != null){
            System.out.println("Successfully logged in of: " + user.getUsername() + " !");
        } else {
            System.out.println("Login canceled!");
        }
    }
}
