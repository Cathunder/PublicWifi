package org.example.midassignment;

import org.example.midassignment.temp.OpenApi;
import org.junit.jupiter.api.Test;

class OpenApiTest {

    @Test
    void get() {
        String result = OpenApi.get(1, 2);
        System.out.println(result);


    }
}