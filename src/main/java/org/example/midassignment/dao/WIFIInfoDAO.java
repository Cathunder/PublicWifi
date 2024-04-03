package org.example.midassignment.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.example.midassignment.db.DBConnection;
import org.example.midassignment.dto.WIFIInfoDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WIFIInfoDAO extends DBConnection {

    public void findAllWIFI() {
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

    public void saveWIFIInfo(JsonArray jsonArray) {
        int batchCnt = 0;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnect();
            conn.setAutoCommit(false);

            String sql = "INSERT INTO WIFIInfo " +
                    "(" +
                    "MGR_NO, WRDOFC, MAIN_NM, ADDRESS1, ADDRESS2, " +
                    "INSTALL_FLOOR, INSTALL_TY, INSTALL_MBY, SVC_SE, CMCWR, " +
                    "CNSTC_YEAR, INOUT_DOOR, REMARS3, LAT, LNT, WORKDATE" +
                    ") " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql);

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject wifiInfo = jsonArray.get(i).getAsJsonObject();

                pstmt.setString(1, wifiInfo.get("X_SWIFI_MGR_NO").getAsString());
                pstmt.setString(2, wifiInfo.get("X_SWIFI_WRDOFC").getAsString());
                pstmt.setString(3, wifiInfo.get("X_SWIFI_MAIN_NM").getAsString());
                pstmt.setString(4, wifiInfo.get("X_SWIFI_ADRES1").getAsString());
                pstmt.setString(5, wifiInfo.get("X_SWIFI_ADRES2").getAsString());
                pstmt.setString(6, wifiInfo.get("X_SWIFI_INSTL_FLOOR").getAsString());
                pstmt.setString(7, wifiInfo.get("X_SWIFI_INSTL_TY").getAsString());
                pstmt.setString(8, wifiInfo.get("X_SWIFI_INSTL_MBY").getAsString());
                pstmt.setString(9, wifiInfo.get("X_SWIFI_SVC_SE").getAsString());
                pstmt.setString(10, wifiInfo.get("X_SWIFI_CMCWR").getAsString());
                pstmt.setString(11, wifiInfo.get("X_SWIFI_CNSTC_YEAR").getAsString());
                pstmt.setString(12, wifiInfo.get("X_SWIFI_INOUT_DOOR").getAsString());
                pstmt.setString(13, wifiInfo.get("X_SWIFI_REMARS3").getAsString());
                pstmt.setString(14, wifiInfo.get("LAT").getAsString());
                pstmt.setString(15, wifiInfo.get("LNT").getAsString());
                pstmt.setString(16, wifiInfo.get("WORK_DTTM").getAsString());

                pstmt.addBatch();
                batchCnt++;

                pstmt.clearParameters();

                if (batchCnt % 1000 == 0) {
                    pstmt.executeBatch();
                    pstmt.clearBatch();
                    conn.commit();
                }
            }

            pstmt.executeBatch();
            conn.commit();

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException sqlException) {
                throw new RuntimeException(sqlException);
            }
        } finally {
            close(rs, pstmt, conn);
        }
    }

    public List<WIFIInfoDTO> findNearestWIFI(String lat, String lnt) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<WIFIInfoDTO> result = new ArrayList<>();

        try {
            conn = getConnect();

            String sql = "SELECT *, " +
                    "round(6371*acos(cos(radians(?))*cos(radians(LAT))*cos(radians(LNT) " +
                    "-radians(?))+sin(radians(?))*sin(radians(LAT))), 4) " +
                    "AS DISTANCE " +
                    "FROM WIFIInfo " +
                    "ORDER BY DISTANCE " +
                    "LIMIT 20";

            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, Double.parseDouble(lat));
            pstmt.setDouble(2, Double.parseDouble(lnt));
            pstmt.setDouble(3, Double.parseDouble(lat));

            rs = pstmt.executeQuery();

            while (rs.next()) {
                WIFIInfoDTO wifiInfoDTO = new WIFIInfoDTO();
                wifiInfoDTO.setDistance(rs.getDouble("DISTANCE"));
                wifiInfoDTO.setMgrNo(rs.getString("MGR_NO"));
                wifiInfoDTO.setWrdOfc(rs.getString("WRDOFC"));
                wifiInfoDTO.setMainNm(rs.getString("MAIN_NM"));
                wifiInfoDTO.setAddress1(rs.getString("ADDRESS1"));
                wifiInfoDTO.setAddress2(rs.getString("ADDRESS2"));
                wifiInfoDTO.setInstallFloor(rs.getString("INSTALL_FLOOR"));
                wifiInfoDTO.setInstallTy(rs.getString("INSTALL_TY"));
                wifiInfoDTO.setInstallMby(rs.getString("INSTALL_MBY"));
                wifiInfoDTO.setSvcSe(rs.getString("SVC_SE"));
                wifiInfoDTO.setCmcwr(rs.getString("CMCWR"));
                wifiInfoDTO.setCnstcYear(rs.getString("CNSTC_YEAR"));
                wifiInfoDTO.setInOutDooCr(rs.getString("INOUT_DOOR"));
                wifiInfoDTO.setRemars3(rs.getString("REMARS3"));
                wifiInfoDTO.setLat(rs.getString("LAT"));
                wifiInfoDTO.setLnt(rs.getString("LNT"));
                wifiInfoDTO.setWorkDate(rs.getString("WORKDATE"));

                result.add(wifiInfoDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs, pstmt, conn);
        }

        return result;
    }
}
