package org.example.midassignment.dao;

import org.example.midassignment.DBConnection;
import org.example.midassignment.dto.HistoryDTO;
import org.example.midassignment.dto.WIFIInfoDTO;

import java.sql.*;

public class WIFIInfoDAO extends DBConnection {

    public void selectDB() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnect();

            String sql = "SELECT * from WIFIInfo";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String mgrNo = rs.getString("mgrNo");
                String wrdOfc = rs.getString("wrdOfc");
                String mainNm = rs.getString("mainNm");
                String address1 = rs.getString("address1");
                String address2 = rs.getString("address2");
                String installFloor = rs.getString("installFloor");
                String installTy = rs.getString("installTy");
                String installMby = rs.getString("installMby");
                String svcSe = rs.getString("svcSe");
                String cmcwr = rs.getString("cmcwr");
                String cnstcYear = rs.getString("cnstcYear");
                String inOutDooCr = rs.getString("inOutDooCr");
                String remars3 = rs.getString("remars3");
                String lat = rs.getString("lat");
                String lnt = rs.getString("lnt");
                String workDate = rs.getString("workDate");

                System.out.println(id + ", " + mgrNo + ", " + wrdOfc + ", " + mainNm
                        + ", " + address1 + ", " + address2 + ", " + installFloor + ", " + installTy
                        + ", " + installMby + ", " + svcSe + ", " + cmcwr + ", " + cnstcYear
                        + ", " + inOutDooCr + ", " + remars3 + ", " + lat + ", " + lnt + ", " + workDate);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs, pstmt, conn);
        }
    }

//    public void insertDB(WIFIInfoDTO wifiInfoDTO) {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
////        try {
////            conn = getConnect();
////
////            String sql = "INSERT INTO history (lat , lnt , search_date) " +
////                    "VALUES (?, ?, (SELECT strftime('%Y-%m-%dT%H:%M:%S', 'now', 'localtime')))";
////
////            pstmt = conn.prepareStatement(sql);
////            pstmt.setString(1, historyDTO.getLat());
////            pstmt.setString(2, historyDTO.getLnt());
////
////            int affected = pstmt.executeUpdate();
////            if (affected > 0) {
////                System.out.println("DB 저장 성공");
////            } else {
////                System.out.println("DB 저장 실패");
////            }
////        } catch (SQLException e) {
////            throw new RuntimeException(e);
////        } finally {
////            close(rs, pstmt, conn);
////        }
//    }
}
