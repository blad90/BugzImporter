import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;


public class DatabaseManager {

    Scanner scanner = new Scanner(System.in);

    public void getConnection(){

        Connection conn = null;
        String databaseName;
        String userName;
        String password;

        System.out.print("Database: ");
        databaseName = scanner.next();

        System.out.print("Username: ");
        userName = scanner.next();

        System.out.print("Password: ");
        password = scanner.next();
        scanner.close();

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ databaseName, userName,password);
            System.out.print("> Connected <");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}