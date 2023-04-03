plugins {
    id("io.micronaut.application")
    id("com.github.johnrengelman.shadow")
}

java {
    project.property("versions.java")!!.let(JavaVersion::toVersion).let {
        sourceCompatibility = it
        targetCompatibility = it
    }
}

micronaut {
    runtime("lambda_provided")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.github.arhor.aws.playground.samples.*")
    }
}

application {
    mainClass.set("com.github.arhor.aws.playground.samples.SimpleScheduledEventHandlerMicronautRuntime")
}

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor(platform(rootProject))
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("io.micronaut:micronaut-http-validation")

    runtimeOnly("ch.qos.logback:logback-classic")

    compileOnly("org.graalvm.nativeimage:svm")
    compileOnly("org.projectlombok:lombok")

    implementation(platform(rootProject))
    implementation("com.google.code.findbugs:jsr305")
    implementation("jakarta.annotation:jakarta.annotation-api")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-jackson-databind")
    implementation("io.micronaut:micronaut-validation")
    implementation("io.micronaut.aws:micronaut-function-aws")
    implementation("io.micronaut.aws:micronaut-function-aws-custom-runtime")

    testImplementation("io.micronaut:micronaut-http-client")
}

tasks {
    dockerfileNative {
        // image used to compile should be the same as the AWS Lambda Runtime selected to deploy function
        baseImage("amazonlinux:2")
        args(
            "-XX:MaximumHeapSizePercent=80",
            "-Dio.netty.allocator.numDirectArenas=0",
            "-Dio.netty.noPreferDirect=true",
        )
    }
}
