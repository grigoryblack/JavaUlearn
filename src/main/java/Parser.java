import java.io.*;
import java.util.ArrayList;

public class Parser {
    public static ArrayList<SportEvent> parseCsv() throws UnsupportedEncodingException, FileNotFoundException {
        ArrayList<SportEvent> sportEvents = new ArrayList<>();
        InputStreamReader streamReader = new InputStreamReader(new FileInputStream("src/main/resources/sportData.csv"), "windows-1251");
        try (BufferedReader br = new BufferedReader(streamReader)) {
            br.readLine();
            while (br.ready()) {
                String row = br.readLine();
                int commaCount = 0, leftBorder = 0, rightBorder = 0, shift = 0;
                boolean flag = true;

                for (int i = 0; i < row.length(); i++) {
                    if (commaCount == 3 && flag) {
                        leftBorder = i;
                        flag = false;
                    } if (row.charAt(i) == ',' && flag)
                        commaCount++;
                    StringBuilder column = new StringBuilder();
                    for (int j = 0; j < 4; j++)
                        column.append(row.charAt(i + j));
                    if (column.toString().equals(",\"20") && !flag) {
                        rightBorder = i + 1;
                        break;
                    }
                }

                String[] newRow = String
                        .join("", row.substring(0, leftBorder), row.substring(rightBorder))
                        .replaceAll("\"", "")
                        .split(",");

                if (newRow.length == 9) shift = 1;
                if (newRow.length == 11) shift = 3;

                sportEvents.add(new SportEvent(
                        newRow[7 + shift].equals(" -") ? 0 : Integer.parseInt(newRow[7 + shift]),
                        newRow[0],
                        Integer.parseInt(newRow[4].split("-")[0]),
                        newRow[6].split(",")[0],
                        newRow[2],
                        newRow[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sportEvents;
    }
}
