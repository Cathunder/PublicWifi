package org.example.midassignment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpenApiTest {

    @Test
    void get() {
        String result = OpenApi.get(1, 2);
        System.out.println(result);


    }
}