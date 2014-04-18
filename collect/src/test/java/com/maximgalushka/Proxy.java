package com.maximgalushka;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.params.AuthPNames;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.ArrayList;
import java.util.List;

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
