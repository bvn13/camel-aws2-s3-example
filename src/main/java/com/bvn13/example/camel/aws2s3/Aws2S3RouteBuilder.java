package com.bvn13.example.camel.aws2s3;


import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.aws2.s3.AWS2S3Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class Aws2S3RouteBuilder extends RouteBuilder {

    @Value("${app.bucket-name}")
    private String bucketName;

    @Value("${app.access-key}")
    private String accessKey;

    @Value("${app.secret-key}")
    private String secretKey;

    @Value("${app.region}")
    private String region;

    @Override
    public void configure() {

        from("direct://send-to-aws")
                .to(format("aws2-s3://%s?accessKey=RAW(%s)&secretKey=RAW(%s)&region=%s",
                        bucketName, accessKey, secretKey, region.toUpperCase()));

    }


    public void sendToAws(String key, String data) {
        getContext().createFluentProducerTemplate()
                .to("direct://send-to-aws")
                .withHeader(AWS2S3Constants.KEY, key)
                .withBody(data)
                .send();
    }

}