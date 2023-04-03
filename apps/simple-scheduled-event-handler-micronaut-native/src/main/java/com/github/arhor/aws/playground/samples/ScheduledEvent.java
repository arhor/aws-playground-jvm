package com.github.arhor.aws.playground.samples;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Data
@Introspected
public class ScheduledEvent {

    private String account;
    private String region;
    private Map<String, Object> detail;
    private String detailType;
    private String source;
    private String id;
    private ZonedDateTime time;
    private List<String> resources;
}
