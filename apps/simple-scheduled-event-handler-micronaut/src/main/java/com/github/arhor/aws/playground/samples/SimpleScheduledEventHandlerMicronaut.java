package com.github.arhor.aws.playground.samples;

import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import io.micronaut.function.aws.MicronautRequestHandler;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SimpleScheduledEventHandlerMicronaut extends MicronautRequestHandler<ScheduledEvent, Void> {

    // URL of the site to check, stored in the site environment variable
    private static final String SITE_URL = System.getenv("site_url");

    // URL of the site to check, stored in the site environment variable
    private static final String EXPECTED = System.getenv("expected");

    private static final Logger logger = LoggerFactory.getLogger(SimpleScheduledEventHandlerMicronaut.class);

    @Override
    public Void execute(final ScheduledEvent event) {
        logger.info("Checking {} at {}...", SITE_URL, event.getTime());

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
                logger.info("Check success");
            } else {
                logger.error("Check failure");
                throw new RuntimeException("");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            logger.info("Check complete at {}", DateTime.now());
        }

        return null;
    }
}
