package ru.javawebinar.topjava;

import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;


/**
 * Created by Johann Stolz 20.07.2018
 */
public class TestStopwatch extends Stopwatch {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(TestStopwatch.class);
    private Map<String, Long> testStopwatch = new TreeMap<>();

    @Override
    protected void finished(long nanos, Description description) {
        log.info("{} duration {} ms", description.getMethodName(), nanos / 1000000);
        testStopwatch.put("Test class " + description.getTestClass().getSimpleName() + " method name " + description.getMethodName(), nanos / 1000000);
    }

    public void printTiming() {
        testStopwatch.forEach((key, value) -> System.out.format("%s duration %s ms" + System.lineSeparator(), key, value));
    }
}
