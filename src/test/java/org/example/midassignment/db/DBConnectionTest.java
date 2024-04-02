package org.example.midassignment.db;

import org.example.midassignment.service.HistoryService;
import org.junit.jupiter.api.Test;

class DBConnectionTest {

    @Test
    void select() {
        HistoryService dbConnection = new HistoryService();
        dbConnection.selectDB();
//        dbConnection.DB_insert();
//        dbConnection.DB_update();
//        dbConnection.DB_delete();

    }
}