import java.awt.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private static Connection connection;
    private static Statement statement;
    private static int k = 0;

    public static void connectionDb(String path) throws SQLException {
        connection = DriverManager.getConnection(path);
        statement = connection.createStatement();
    }

    public static void disconnectDB() throws SQLException {
        statement.close();
        connection.close();
    }

    public static void getAnswers() throws SQLException {
        TaskOne();
        TaskTwo();
        TaskThree();
    }

    public static void addTable() throws SQLException {
        statement.execute("""
                        CREATE TABLE IF NOT EXISTS Event (
                            participants INTEGER,
                            section TEXT,
                            year INTEGER,
                            country TEXT,
                            title TEXT,
                            subsection TEXT);
                        """);
    }

    public static void addDataToTable(SportEvent event) {
        String request = String.format(
                "INSERT INTO Event (participants, section, year, country, title, subsection)" +
                        "VALUES ('%s', '%s', '%s', '%s', '%s', '%s');",
                event.getParticipants(),
                event.getSection(),
                event.getYear(),
                event.getCountry(),
                event.getTitle(),
                event.getSubsection()
        );
        try {
            statement.execute(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(k++);
    }

    private static void TaskOne() throws SQLException {
        Map<String, Integer> countPeople = new HashMap<>();
        String request = "" +
                "SELECT DISTINCT section, SUM(participants) AS count " +
                "FROM Event " +
                "GROUP BY section;";

        ResultSet result = statement.executeQuery(request);

        while (result.next()) {
            countPeople.put(
                    result.getString("section"),
                    Integer.parseInt(result.getString("count")));
        }

        EventQueue.invokeLater(() -> {
            var plot = new Plot(countPeople);
            plot.setVisible(true);
        });
    }

    private static void TaskTwo() throws SQLException {
        String request = "" +
                "SELECT country, SUM(participants) AS count " +
                "FROM Event " +
                "WHERE year = 2008 " +
                "GROUP BY country;";

        ResultSet result = statement.executeQuery(request);
        while (result.next()) {
            System.out.println(String.format("В %s было %s участников",
                    result.getString("country"),
                    result.getString("count")));
        }
    }

    private static void TaskThree() throws SQLException {
        String request = "" +
                "SELECT title, MAX(participants) " +
                "FROM Event " +
                "WHERE subsection == 'Молодежный (резервный) состав' " +
                "    and section == 'Восточное боевое единоборство';";

        System.out.println(String.format(
                "\nСамое массовое мероприятие среди резервных составов в восточном боевом единоборстве: %s\n",
                statement.executeQuery(request).getString("title"))
        );
    }
}