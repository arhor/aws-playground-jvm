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
    annotationProcessor("io.micronaut:micronaut-http-validation")

    runtimeOnly("ch.qos.logback:logback-classic")

    compileOnly("org.graalvm.nativeimage:svm")

    implementation(platform(rootProject))
    implementation("com.amazonaws:aws-lambda-java-events")
    implementation("jakarta.annotation:jakarta.annotation-api")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-jackson-databind")
    implementation("io.micronaut:micronaut-validation")
    implementation("io.micronaut.aws:micronaut-function-aws")
    implementation("io.micronaut.aws:micronaut-function-aws-custom-runtime")

    testImplementation("io.micronaut:micronaut-http-client")
}

tasks.named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") {
    args(
        "-XX:MaximumHeapSizePercent=80",
        "-Dio.netty.allocator.numDirectArenas=0",
        "-Dio.netty.noPreferDirect=true"
    )
}
