package org.example.midassignment.db;

import org.example.midassignment.dao.HistoryDAO;
import org.junit.jupiter.api.Test;

class DBConnectionTest {

    @Test
    void select() {
        HistoryDAO dbConnection = new HistoryDAO();
        dbConnection.selectDB();
//        dbConnection.DB_insert();
//        dbConnection.DB_update();
//        dbConnection.DB_delete();

    }
}