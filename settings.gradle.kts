pluginManagement {
    plugins {
        fun prop(name: String): String = extra[name].toString()

        // @formatter:off
        id("com.github.johnrengelman.shadow") version prop("versions.gradle-shadow-plugin")
        // @formatter:on
    }
}

rootProject.name = "aws-playground-jvm-samples"

include(":apps:simple-scheduled-event-handler")
