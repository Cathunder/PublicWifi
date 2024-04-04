package org.example.midassignment.dao;

import org.example.midassignment.db.DBConnection;
import org.example.midassignment.dto.HistoryDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDAO extends DBConnection {

    public List<HistoryDTO> findAllHistory() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<HistoryDTO> result = new ArrayList<>();

        try {
            conn = getConnect();

            String sql = "SELECT * from history " +
                    "ORDER BY id DESC;";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                HistoryDTO historyDTO = new HistoryDTO();
                historyDTO.setId(rs.getInt("id"));
                historyDTO.setLat(rs.getString("lat"));
                historyDTO.setLnt(rs.getString("lnt"));
                historyDTO.setSearchTime(rs.getString("search_date"));

                result.add(historyDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs, pstmt, conn);
        }

        return result;
    }

    public void saveHistory(String lat, String lnt) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnect();

            String sql = "INSERT INTO history (lat, lnt, search_date) " +
                    "VALUES (?, ?, (SELECT strftime('%Y-%m-%dT%H:%M:%S', 'now', 'localtime')))";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, lat);
            pstmt.setString(2, lnt);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs, pstmt, conn);
        }
    }

    public void deleteHistory(String idValue) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnect();

            String sql = "DELETE FROM history " +
                    "WHERE id = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(idValue));

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs, pstmt, conn);
        }
    }
}
