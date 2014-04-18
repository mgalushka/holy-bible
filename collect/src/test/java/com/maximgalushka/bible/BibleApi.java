package com.maximgalushka.bible;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.maximgalushka.http.Proxy;

import static com.maximgalushka.bible.Constants.BIBLE_API;
import static com.maximgalushka.bible.Constants.BIBLE_API_KEY;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 4/18/14
 */
public class BibleApi {

    public BibleApi() {
        Unirest.setHttpClient(Proxy.getHttpClient());
    }

    public JsonNode request(String call) throws UnirestException {
        String url = String.format("%s/%s", BIBLE_API, call);
        System.out.printf("Requesting: [%s]\n", url);
        HttpResponse<JsonNode> request =
                Unirest.get(url).header("X-Mashape-Authorization", BIBLE_API_KEY).asJson();
        return request.getBody();
    }
}
