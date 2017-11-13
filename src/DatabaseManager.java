import java.sql.*;
import java.util.Scanner;


public class DatabaseManager {

    Scanner scanner = new Scanner(System.in);
    Connection conn = null;

    public void getConnection(){

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

    public void readData(){
        String query = "SELECT bugid, summary,status,dependent,duplicate,blocks FROM bug";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()){
                String bugid = rs.getString("bugid");
                String summary = rs.getString("summary");
                String dependent = rs.getString("dependent");
                String duplicate = rs.getString("duplicate");
                String status = rs.getString("status");
                String blocks = rs.getString("blocks");

                System.out.format("%s, %s, %s, %s, %s, %s\n", bugid, summary, dependent, duplicate, status, blocks);
            }

            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}