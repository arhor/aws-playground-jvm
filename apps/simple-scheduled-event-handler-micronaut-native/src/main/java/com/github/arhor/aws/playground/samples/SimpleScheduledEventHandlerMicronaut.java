package com.github.arhor.aws.playground.samples;

import io.micronaut.function.aws.MicronautRequestHandler;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class SimpleScheduledEventHandlerMicronaut extends MicronautRequestHandler<ScheduledEvent, Void> {

    // URL of the site to check, stored in the site environment variable
    private static final String SITE_URL = System.getenv("site_url");

    // URL of the site to check, stored in the site environment variable
    private static final String EXPECTED = System.getenv("expected");

    @Override
    public Void execute(final ScheduledEvent event) {
        log.info("Checking {} at {}...", SITE_URL, event.getTime());

        try {
            var req =
                HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(SITE_URL))
                    .header("User-Agent", "AWS Lambda")
                    .build();

            var res =
                HttpClient.newHttpClient()
                    .send(req, HttpResponse.BodyHandlers.ofString());

            if (res.body().contains(EXPECTED)) {
                log.info("Check success");
            } else {
                log.error("Check failure");
                throw new RuntimeException("");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            log.info("Check complete at {}", DateTime.now());
        }

        return null;
    }
}
