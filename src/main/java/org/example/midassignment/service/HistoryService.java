package org.example.midassignment.service;

import org.example.midassignment.dto.History;

import java.sql.*;

public class HistoryService extends SQLiteConnection {

    public void selectDB() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnect();

            String sql = "SELECT * from History";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                double x = rs.getDouble("valueX");
                double y = rs.getDouble("valueY");
                String date = rs.getString("date");

                System.out.println(id + ", " + x + ", " + y + ", " + date);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs, pstmt, conn);
        }
    }

    public void insertDB(History history) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnect();

            String sql = "INSERT INTO History (valueX , valueY , date) " +
                    "VALUES (?, ?, (SELECT strftime('%Y-%m-%dT%H:%M:%S', 'now', 'localtime')))";

            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, history.getValueX());
            pstmt.setDouble(2, history.getValueY());

            int affected = pstmt.executeUpdate();
            if (affected > 0) {
                System.out.println("DB 저장 성공");
            } else {
                System.out.println("DB 저장 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs, pstmt, conn);
        }
    }

    public void updateDB() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int idValue = 3;
        double y = 111.1111112; // 경도

        try {
            conn = getConnect();

            String sql = "UPDATE History " +
                    "SET valueY = ? " +
                    "WHERE id = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, y);
            pstmt.setDouble(2, idValue);

            int affected = pstmt.executeUpdate();
            if (affected > 0) {
                System.out.println("DB 수정 성공");
            } else {
                System.out.println("DB 수정 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs, pstmt, conn);
        }
    }

    public void deleteDB(History history) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnect();

            String sql = "DELETE FROM History " +
                    "WHERE id = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, history.getId());

            int affected = pstmt.executeUpdate();
            if (affected > 0) {
                System.out.println("DB 삭제 성공");
            } else {
                System.out.println("DB 삭제 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs, pstmt, conn);
        }
    }
}
