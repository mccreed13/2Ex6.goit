package com.goit;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseInitService {
    public static void main(String[] args) throws SQLException {
        Database database = Database.getInstance();
        InputStream inputStream = DatabaseInitService.class.getClassLoader()
                .getResourceAsStream("sql/init_db.sql");
        Scanner scanner = new Scanner(inputStream);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine());
        }
        database.execute(sb.toString());
    }
}
