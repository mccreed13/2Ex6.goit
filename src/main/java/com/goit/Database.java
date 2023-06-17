package com.goit;

import org.h2.Driver;

import java.sql.*;

public class Database implements AutoCloseable {
    private static final String URL = "jdbc:h2:~/test";
    private static final String LOGIN = "admin";
    private static final String PASSWORD = "admin";

    private static Database instance;
    private final Driver driver = new Driver();
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    private Database() throws SQLException {
        DriverManager.registerDriver(driver);
        openConnection();
    }

    public static Database getInstance() throws SQLException {
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }

    private void openConnection() throws SQLException {
        connection = DriverManager.getConnection(Database.URL, Database.LOGIN, Database.PASSWORD);
        statement = connection.createStatement();
    }
    public Connection getConnection() {
        return connection;
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public int executeUpdate(String sql) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        return preparedStatement.executeUpdate(sql);
    }

    public boolean execute(String sql) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        return preparedStatement.execute();
    }

    @Override
    public void close() throws Exception {
        statement.close();
        connection.close();
    }
}
