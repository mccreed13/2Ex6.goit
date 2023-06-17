package com.goit;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabasePopulateService {
    public static void main(String[] args) throws SQLException {
        Database database = Database.getInstance();
        InputStream inputStream = DatabaseInitService.class.getClassLoader()
                .getResourceAsStream("sql/populate_db.sql");
        Scanner scanner = new Scanner(inputStream);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine());
        }
        database.executeUpdate(sb.toString());
    }
}
