package dev.sim0n.app.test;

public class TestResult {
    private final String name;
    private final boolean passed;
    private final String failMessage;
    private final long durationMs;

    public TestResult(String name, boolean passed, String failMessage, long durationMs) {
        this.name = name;
        this.passed = passed;
        this.failMessage = failMessage;
        this.durationMs = durationMs;
    }

    public String getName()      { return name; }
    public boolean isPassed()    { return passed; }
    public String getFailMessage() { return failMessage; }
    public long getDurationMs()  { return durationMs; }
}
