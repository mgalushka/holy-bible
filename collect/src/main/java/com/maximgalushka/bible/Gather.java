package com.maximgalushka.bible;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Random;
import java.util.concurrent.*;

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

    private int buffer = 5;
    private BibleApi api;
    private String[] oldTestament;
    private String[] newTestament;

    private BlockingQueue<String> q;
    private ScheduledExecutorService exec;

    private Random r;

    public Gather(int buffer) {
        try {
            this.buffer = buffer;
            this.api = new BibleApi();
            this.r = new Random(System.currentTimeMillis());
            this.q = new LinkedBlockingQueue<String>();
            this.exec = Executors.newSingleThreadScheduledExecutor();
            init();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    private void init() throws UnirestException {
        JsonNode nd = api.request(GET_BOOKS);
        newTestament = nd.getObject().get("The_New_Testament").toString().split(SPLIT);
        oldTestament = nd.getObject().get("The_Old_Testament").toString().split(SPLIT);
        fillQueue();
        System.out.println("Initialized");
    }

    private void fillQueue() throws UnirestException {
        for (int i = 0; i < this.buffer; i++) {
            this.q.add(retrieveNextRandom());
        }
    }

    public String random() {
        this.exec.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Gather.this.q.add(retrieveNextRandom());
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            return this.q.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String retrieveNextRandom() throws UnirestException {
        int t = r.nextInt(2);
        String[] testament = (t == 0) ? oldTestament : newTestament;
        String book = testament[r.nextInt(testament.length - 1) + 1];

        int chapter = r.nextInt(49) + 1;
        while (true) {
            JsonNode chapters = api.request(
                    String.format("%s?Book=%s&chapter=%d", GET_CHAPTER, book, chapter));
            String ch = chapters.getObject().get("Output").toString();
            if (!ch.contains(WRONG)) {
                String[] tokens = ch.split(SPLIT_VERSE);
                String verse = tokens[r.nextInt(tokens.length)];
                if (verse != null && !"".equals(verse.trim())) {
                    return verse.trim();
                }
            } else {
                chapter /= 2;
                if (chapter == 0) {
                    book = testament[r.nextInt(testament.length - 1) + 1];
                }
            }
        }
    }

    public static void main(String[] args) throws UnirestException {
        Gather g = new Gather(5);
        for (int i = 0; i < 20; i++) {
            System.out.println(g.random());
        }
    }

}
