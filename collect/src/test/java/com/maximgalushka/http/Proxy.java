package com.maximgalushka.http;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 4/18/14
 */
public final class Proxy {

    public static HttpClient getHttpClient(){
        HttpClientBuilder b = HttpClientBuilder.create();
        b.setProxy(new HttpHost("localhost", 4545));
        return b.build();
    }
}
