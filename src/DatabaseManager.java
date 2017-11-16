import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DatabaseManager {

    Scanner scanner = new Scanner(System.in);
    Connection conn = null;
    List<String[]> bugsRow;
    ArrayList<String> bugsList;

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

    public void readBugsData(){
        String selectQuery = "SELECT id,summary,status,dependent,duplicate,blocks FROM bugs";
        String insertQuery;

        try {
            Statement st = conn.createStatement();
            // Disable foreign keys check
            st.execute("SET FOREIGN_KEY_CHECKS=0");

            ResultSet rs = st.executeQuery(selectQuery);

            while (rs.next()){
                String bugid = rs.getString("id");
                String summary = rs.getString("summary");
                String dependent = rs.getString("dependent");
                String duplicate = rs.getString("duplicate");
                String status = rs.getString("status");
                String blocks = rs.getString("blocks");

                insertQuery = "INSERT INTO bugs.bugs (bug_id," +
                        "assigned_to," +
                        "bug_file_loc," +
                        "bug_severity," +
                        "bug_status," +
                        "creation_ts," +
                        "delta_ts," +
                        "short_desc," +
                        "op_sys," +
                        "priority," +
                        "product_id," +
                        "rep_platform," +
                        "reporter," +
                        "version," +
                        "component_id," +
                        "resolution," +
                        "target_milestone," +
                        "qa_contact," +
                        "status_whiteboard," +
                        "lastdiffed," +
                        "everconfirmed," +
                        "reporter_accessible," +
                        "cclist_accessible," +
                        "estimated_time," +
                        "remaining_time," +
                        "deadline) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                        "?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                        "?, ?, ?, ?, ?, ?)";

                System.out.print(insertQuery);

                PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
                preparedStatement.setString(1, bugid); //bug_id
                preparedStatement.setInt(2, 1); //assigned_to
                preparedStatement.setString(3, "PLACEHLDER"); //bug_file_loc
                preparedStatement.setString(4, "PLACEHLDER"); //bug_severity
                preparedStatement.setString(5, "CONFIRMED"); //bug_status
                preparedStatement.setString(6, "2017-04-11 14:44:45"); //creation_ts
                preparedStatement.setString(7, "2017-04-11 14:44:45"); //delta_ts
                preparedStatement.setString(8, summary); //short_desc
                preparedStatement.setString(9, "PLACEHLDER"); //op_sys
                preparedStatement.setString(10, "PLACEHLDER"); //priority
                preparedStatement.setInt(11, 1); //product_id
                preparedStatement.setString(12, "PLACEHLDER"); //rep_platform
                preparedStatement.setInt(13, 1); //reporter
                preparedStatement.setString(14, "PLACEHLDER"); //version
                preparedStatement.setInt(15, 1); //component_id
                preparedStatement.setString(16, "---"); //resolution
                preparedStatement.setString(17, "---"); //target_milestone
                preparedStatement.setInt(18, 1); //qa_contact
                preparedStatement.setString(19, "PLACEHLDER"); //status_whiteboard
                preparedStatement.setString(20, "2017-04-11 14:44:45");  //lastdiffed
                preparedStatement.setInt(21, 1); //everconfirmed
                preparedStatement.setInt(22, 1); //reporter_accessible
                preparedStatement.setInt(23, 1); //cclist_accesible
                preparedStatement.setDouble(24, 0.00); //estimated_time
                preparedStatement.setDouble(25, 0.00); //remaining_time
                preparedStatement.setString(26, "2017-04-11 14:44:45"); //deadline

                preparedStatement.executeUpdate();
                //System.out.println(bugid);
                //System.out.format("%s, %s, %s, %s, %s, %s\n", bugid, summary, dependent, duplicate, status, blocks);
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