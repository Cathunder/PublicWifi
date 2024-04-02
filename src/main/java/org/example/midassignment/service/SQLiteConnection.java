package org.example.midassignment.service;

import java.sql.*;

public class SQLiteConnection {

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Connection getConnect() {

//        final String dbPath = "/Users/ttekkeollug/Documents/zerobase/과제/중간과제";
//        final String fileLocation = dbPath + "/DB.db";

        // SQLite connection string
        String url = "jdbc:sqlite:/Users/ttekkeollug/Documents/zerobase/과제/중간과제/DB";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }

    protected void close(ResultSet rs, PreparedStatement preparedStatement, Connection connection) {

        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
