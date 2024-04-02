package org.example.midassignment.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WIFIInfoServiceTest {

    @Test
    void getWIFIInfoJson() {
        WIFIInfoService wifiInfoService = new WIFIInfoService();
        wifiInfoService.getWIFIInfoJson();
    }
}