package com.github.arhor.aws.playground.samples;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import org.joda.time.DateTime;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class SimpleScheduledEventHandler implements RequestHandler<ScheduledEvent, Void> {

    // URL of the site to check, stored in the site environment variable
    private static final String SITE_URL = System.getenv("site_url");

    // URL of the site to check, stored in the site environment variable
    private static final String EXPECTED = System.getenv("expected");

    @Override
    public Void handleRequest(final ScheduledEvent event, final Context context) {
        System.out.printf("Checking %s at %s...%n", SITE_URL, event.getTime());

        try {
            var req =
                HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(SITE_URL))
                    .header("User-Agent", "AWS Lambda")
                    .build();

            var res =
                HttpClient.newHttpClient()
                    .send(req, BodyHandlers.ofString());

            if (res.body().contains(EXPECTED)) {
                System.out.println("Check success");
            } else {
                System.out.println("Check failure");
                throw new RuntimeException("");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.printf("Check complete at %s%n", DateTime.now());
        }

        return null;
    }
}
