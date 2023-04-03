pluginManagement {
    plugins {
        fun prop(name: String): String = extra[name].toString()

        // @formatter:off
        id("com.github.johnrengelman.shadow") version prop("versions.gradle-shadow-plugin")
        id("io.micronaut.application")        version prop("versions.micronaut-plugin")
        id("io.micronaut.library")            version prop("versions.micronaut-plugin")
        // @formatter:on
    }
}

rootProject.name = "aws-playground-jvm-samples"

include(":apps:simple-scheduled-event-handler")
include(":apps:simple-scheduled-event-handler-micronaut")
include(":apps:simple-scheduled-event-handler-micronaut-native")
