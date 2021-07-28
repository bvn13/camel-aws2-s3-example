package com.bvn13.example.camel.aws2s3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CamelAws2S3ExampleApplicationTests {

    @Autowired
    private Aws2S3RouteBuilder routeBuilder;

    @Test
    void testCamel() {

        routeBuilder.sendToAws("test", "test-data");

    }

}
