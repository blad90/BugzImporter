/**
 * @author Bladimir Baez
 * @version 1.0
 * @since 11/12/17
 */
public class Main {

    public static void main(String[] args){
        DatabaseManager dm = new DatabaseManager();
        dm.getConnection();
        //Read data from database...
        dm.readData();
        dm.closeConnection();


    }
}
