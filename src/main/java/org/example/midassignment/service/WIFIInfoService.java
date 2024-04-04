package org.example.midassignment.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;
import org.example.midassignment.dao.WIFIInfoDAO;

import java.net.URL;

public class WIFIInfoService {

    private final WIFIInfoDAO wifiInfoDAO;

    public WIFIInfoService(WIFIInfoDAO wifiInfoDAO) {
        this.wifiInfoDAO = wifiInfoDAO;
    }

    // json 공공와이파이정보 파싱
    public void getWIFIInfo() {
        int totalCnt = getWIFITotalCnt();
        int start = 1;
        int end = 1;

        try {
            for (int i = 0; i < totalCnt / 1000; i++) {
                start = i * 1000 + 1;
                end = (i + 1) * 1000;

                String baseURL = "http://openapi.seoul.go.kr:8088/6c4177444b74616b3434504f694c7a/json/TbPublicWifiInfo/";
                URL url = new URL(baseURL + start + "/" + end);
                OkHttpClient client = new OkHttpClient.Builder().build();
                Request request = new Request.Builder().url(url).get().build();
                Response response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    ResponseBody body = response.body();

                    if (body != null) {
                        JsonElement element = JsonParser.parseString(body.string());
                        JsonObject obj = element.getAsJsonObject();
                        JsonArray jsonArr = obj
                                .get("TbPublicWifiInfo").getAsJsonObject()
                                .get("row").getAsJsonArray();

                        wifiInfoDAO.saveAllWIFI(jsonArr);
                    }
                } else {
                    System.out.println("response fail");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 공공와이파이 전체개수 구하기
    public int getWIFITotalCnt() {
        int totalCnt = 0;

        try {
            String baseURL = "http://openapi.seoul.go.kr:8088/6c4177444b74616b3434504f694c7a/json/TbPublicWifiInfo/";
            URL url = new URL(baseURL + "1/1");
            OkHttpClient client = new OkHttpClient.Builder().build();
            Request request = new Request.Builder().url(url).get().build();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                ResponseBody body = response.body();

                if (body != null) {
                    JsonElement element = JsonParser.parseString(body.string());
                    JsonObject obj = element.getAsJsonObject();
                    totalCnt = obj
                            .get("TbPublicWifiInfo").getAsJsonObject()
                            .get("list_total_count").getAsInt();
                }
            } else {
                System.out.println("response fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalCnt;
    }
}
