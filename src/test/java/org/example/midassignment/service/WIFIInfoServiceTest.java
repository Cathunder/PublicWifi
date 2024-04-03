package org.example.midassignment.service;

import org.example.midassignment.dao.WIFIInfoDAO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WIFIInfoServiceTest {

    private WIFIInfoDAO wifiInfoDAO;

    @Test
    void getWIFIInfoJson() {
        WIFIInfoService wifiInfoService = new WIFIInfoService(wifiInfoDAO);
        wifiInfoService.getWIFIInfoJson();
    }
}