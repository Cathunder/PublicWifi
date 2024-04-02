package org.example.midassignment;

import java.sql.*;

public class DBConnection {

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Connection getConnect() {
        final String dbPath = "/Users/ttekkeollug/Documents/zerobase/과제/중간과제/MidAssignment";
        final String fileLocation = dbPath + "/WIFIInfo.db";

        // SQLite connection string
        String url = "jdbc:sqlite:" + fileLocation;
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
