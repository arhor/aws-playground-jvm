package com.github.arhor.aws.playground.samples;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.function.aws.runtime.AbstractMicronautLambdaRuntime;

import java.net.MalformedURLException;

public class SimpleScheduledEventHandlerMicronautRuntime
    extends AbstractMicronautLambdaRuntime<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent, ScheduledEvent, Void> {

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
