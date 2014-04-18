package com.maximgalushka.bible;

import com.mashape.unirest.http.JsonNode;

import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Arrays;

import static com.maximgalushka.bible.Constants.Command.*;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 4/18/14
 */
public class Gather {

    private static String WRONG = "Please try again.";

    public static void main(String[] args) throws UnirestException {
        BibleApi api = new BibleApi();
        JsonNode nd = api.request(GET_BOOKS);
        String[] newTestament = nd.getObject().get("The_New_Testament").toString().split("\\s?\\d+\\.\\s?");
        String[] oldTestament = nd.getObject().get("The_Old_Testament").toString().split("\\s?\\d+\\.\\s?");
        System.out.println(Arrays.toString(newTestament));
        System.out.println(Arrays.toString(oldTestament));

        for (String book : newTestament) {
            if ("".equals(book)) continue;
            boolean incorrect = false;
            int id = 1;
            while (!incorrect) {
                JsonNode chapter = api.request(
                        String.format("%s?Book=%s&chapter=%d", GET_CHAPTER, book, id++));
                if (chapter.getObject().get("Output").toString().contains(WRONG)) {
                    incorrect = true;
                } else {
                    System.out.printf("[%s]: %s \n", book, chapter);
                }
            }
        }
    }

}
