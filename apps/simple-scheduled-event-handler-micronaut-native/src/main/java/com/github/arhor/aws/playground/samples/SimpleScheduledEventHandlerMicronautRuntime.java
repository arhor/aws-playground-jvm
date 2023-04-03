package com.github.arhor.aws.playground.samples;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import io.micronaut.aop.Introduction;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.function.aws.runtime.AbstractMicronautLambdaRuntime;

import java.net.MalformedURLException;

@Introduction
public class SimpleScheduledEventHandlerMicronautRuntime
    extends AbstractMicronautLambdaRuntime<ScheduledEvent, Void, ScheduledEvent, Void> {

    public static void main(final String[] args) {
        try {
            new SimpleScheduledEventHandlerMicronautRuntime()
                .run(args);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Nullable
    protected RequestHandler<ScheduledEvent, Void> createRequestHandler(final String... args) {
        return new SimpleScheduledEventHandlerMicronaut();
    }
}
