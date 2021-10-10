package com.example;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import javax.inject.Inject;

@MicronautTest
public class SmokeTest {

    @Inject
    CommandController instance;

    @Test
    void testItWorks() {
        instance.put("foo", "bar");

        Assertions.assertEquals("bar", instance.get("foo").getValue());
    }
}
