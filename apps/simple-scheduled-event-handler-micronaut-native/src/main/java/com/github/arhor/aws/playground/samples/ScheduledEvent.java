package com.github.arhor.aws.playground.samples;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Data
@Introspected
public class ScheduledEvent {

    private String id;
    private String region;
    private String source;
    private String account;
    private String detailType;
    private ZonedDateTime time;
    private List<String> resources;
    private Map<String, Object> detail;
}
