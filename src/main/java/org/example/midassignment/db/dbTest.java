package org.example.midassignment.db;

import org.example.midassignment.dto.HistoryDTO;
import org.example.midassignment.dao.HistoryDAO;

import java.util.Scanner;

public class dbTest {
    public static void main(String[] args) {
        HistoryDAO dbc = new HistoryDAO();

        Scanner sc = new Scanner(System.in);
//        System.out.println("x: ");
//        double x = Double.parseDouble(sc.nextLine());
//        System.out.println("y: ");
//        double y = Double.parseDouble(sc.nextLine());

        System.out.println("삭제id값을입력: ");
        int id = Integer.parseInt(sc.nextLine());
        HistoryDTO historyDTO = new HistoryDTO();
        historyDTO.setId(id);
//        history.setValueX(x);
//        history.setValueY(y);

//        dbc.insertDB(history);
        dbc.deleteDB(historyDTO);
    }
}
