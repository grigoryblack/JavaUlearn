import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException, FileNotFoundException, UnsupportedEncodingException {
        Database.connectionDb("jdbc:sqlite:src/main/resources/sportDB.db");
//        Database.addTable();
//        Parser.parseCsv().forEach(Database::addDataToTable);
        Database.getAnswers();
        Database.disconnectDB();
    }
}