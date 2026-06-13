package dev.sim0n.app;

import xyz.qreaj.tests.Tests;

/**
 * @author sim0n
 */
public class Main {
    public static void main(String[] args) {
        Application application = new Application();
        application.run();
        Tests.runTests();
    }
}
