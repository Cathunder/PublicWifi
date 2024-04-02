package org.example.midassignment.db;

import org.example.midassignment.dto.History;
import org.example.midassignment.service.HistoryService;

import java.util.Scanner;

public class dbTest {
    public static void main(String[] args) {
        HistoryService dbc = new HistoryService();

        Scanner sc = new Scanner(System.in);
//        System.out.println("x: ");
//        double x = Double.parseDouble(sc.nextLine());
//        System.out.println("y: ");
//        double y = Double.parseDouble(sc.nextLine());

        System.out.println("삭제id값을입력: ");
        int id = Integer.parseInt(sc.nextLine());
        History history = new History();
        history.setId(id);
//        history.setValueX(x);
//        history.setValueY(y);

//        dbc.insertDB(history);
        dbc.deleteDB(history);
    }
}
