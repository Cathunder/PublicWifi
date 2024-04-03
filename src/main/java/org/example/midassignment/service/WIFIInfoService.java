package org.example.midassignment.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;
import org.example.midassignment.dao.WIFIInfoDAO;

import java.net.URL;

public class WIFIInfoService {

    private WIFIInfoDAO wifiInfoDAO;

    public WIFIInfoService(WIFIInfoDAO wifiInfoDAO) {
        this.wifiInfoDAO = wifiInfoDAO;
    }

    public void getWIFIInfoJson() {
        try {
            String baseURL = "http://openapi.seoul.go.kr:8088/6c4177444b74616b3434504f694c7a/json/TbPublicWifiInfo/";
            URL url = new URL(baseURL + "1/1");
            OkHttpClient client = new OkHttpClient.Builder().build();
            Request request = new Request.Builder().url(url).get().build();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                ResponseBody body = response.body();

                if (body != null) {
                    System.out.println("Response: " + body.string());

                    JsonElement element = JsonParser.parseString(body.string());
                    JsonObject obj = element.getAsJsonObject();
                    JsonArray jsonArr = obj
                            .get("TbPublicWifiInfo").getAsJsonObject()
                            .get("row").getAsJsonArray();

                    //jsonArr를 dao의 insert메서드에 넣어주면됨?
                    wifiInfoDAO.insertDB(jsonArr);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
