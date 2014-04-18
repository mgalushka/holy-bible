package com.maximgalushka;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Arrays;

import static com.maximgalushka.Constants.*;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 4/18/14
 */
public class Gather {

    public static void main(String[] args) throws UnirestException {
        Unirest.setHttpClient(Proxy.getHttpClient());
        HttpResponse<JsonNode> request =
                Unirest.get(BIBLE_API)
                        .header("X-Mashape-Authorization", BIBLE_API_KEY)
                        .asJson();

        JsonNode nd = request.getBody();
        String[] books = nd.getObject().get("The_New_Testament").toString().split("\\d+\\.\\s+");
        System.out.println(Arrays.toString(books));
    }
}
