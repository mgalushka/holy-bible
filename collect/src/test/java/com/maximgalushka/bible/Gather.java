package com.maximgalushka.bible;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Arrays;
import java.util.Random;

import static com.maximgalushka.bible.Constants.Command.*;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 4/18/14
 */
public class Gather {

    private static String WRONG = "Please try again.";
    private static String SPLIT = "\\s?\\d+\\.\\s?";
    private static String SPLIT_VERSE = "\\s?\\d+\\s?";

    private BibleApi api;
    private String[] oldTestament;
    private String[] newTestament;

    private Random r;

    public Gather() throws UnirestException {
        this.api = new BibleApi();
        this.r = new Random(System.currentTimeMillis());
        init();
    }

    private void init() throws UnirestException {
        JsonNode nd = api.request(GET_BOOKS);
        newTestament = nd.getObject().get("The_New_Testament").toString().split(SPLIT);
        oldTestament = nd.getObject().get("The_Old_Testament").toString().split(SPLIT);
        System.out.println(Arrays.toString(newTestament));
        System.out.println(Arrays.toString(oldTestament));
    }

    public String random() throws UnirestException {
        int t = r.nextInt(2);
        String[] testament = (t == 0) ? oldTestament : newTestament;
        String book = testament[r.nextInt(testament.length - 1) + 1];

        int chapter = r.nextInt(200);
        while (true) {
            JsonNode chapters = api.request(
                    String.format("%s?Book=%s&chapter=%d", GET_CHAPTER, book, chapter));
            String ch = chapters.getObject().get("Output").toString();
            if (!ch.contains(WRONG)) {
                String[] tokens = ch.split(SPLIT_VERSE);
                return tokens[r.nextInt(tokens.length)];
            } else {
                chapter /= 2;
            }
        }
    }

    public static void main(String[] args) throws UnirestException {
        Gather g = new Gather();
        System.out.println(g.random());
        System.out.println(g.random());
        System.out.println(g.random());
        System.out.println(g.random());
    }

}
