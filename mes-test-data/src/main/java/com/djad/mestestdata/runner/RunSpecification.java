package com.djad.mestestdata.runner;

public class RunSpecification {
    public enum ExecutionStyle {
        SERIAL,
        PARALLEL
    }

    private ExecutionStyle executionStyle;
    private int runEverySeconds;

    public RunSpecification(ExecutionStyle executionStyle, int runEverySeconds) {
        this.executionStyle = executionStyle;
        this.runEverySeconds = runEverySeconds;
    }

    public RunSpecification(ExecutionStyle executionStyle) {
        this(executionStyle, 0);
    }

    public ExecutionStyle getExecutionStyle() {
        return executionStyle;
    }

    public void setExecutionStyle(ExecutionStyle executionStyle) {
        this.executionStyle = executionStyle;
    }

    public int getRunEverySeconds() {
        return runEverySeconds;
    }

    public void setRunEverySeconds(int runEverySeconds) {
        this.runEverySeconds = runEverySeconds;
    }
}
