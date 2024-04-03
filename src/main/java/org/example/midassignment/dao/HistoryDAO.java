package org.example.midassignment.dao;

import org.example.midassignment.db.DBConnection;
import org.example.midassignment.dto.HistoryDTO;

import java.sql.*;

public class HistoryDAO extends DBConnection {

    public void selectDB() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnect();

            String sql = "SELECT * from history";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String x = rs.getString("lat");
                String y = rs.getString("lnt");
                String date = rs.getString("search_date");

                System.out.println(id + ", " + x + ", " + y + ", " + date);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs, pstmt, conn);
        }
    }

    public void insertDB(HistoryDTO historyDTO) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnect();

            String sql = "INSERT INTO history (lat , lnt , search_date) " +
                    "VALUES (?, ?, (SELECT strftime('%Y-%m-%dT%H:%M:%S', 'now', 'localtime')))";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, historyDTO.getLat());
            pstmt.setString(2, historyDTO.getLnt());

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
        String lnt = "111.1111112"; // 경도

        try {
            conn = getConnect();

            String sql = "UPDATE history " +
                    "SET lnt = ? " +
                    "WHERE id = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, lnt);
            pstmt.setInt(2, idValue);

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

    public void deleteDB(HistoryDTO historyDTO) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnect();

            String sql = "DELETE FROM history " +
                    "WHERE id = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, historyDTO.getId());

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
