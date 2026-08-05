package dev.sim0n.app.test;

import dev.sim0n.app.test.impl.annotation.AnnotationTest;
import dev.sim0n.app.test.impl.crypttest.BlowfishTest;
import dev.sim0n.app.test.impl.enumtest.EnumConstantsTest;
import dev.sim0n.app.test.impl.evaluation.EvaluationTest;
import dev.sim0n.app.test.impl.fizzbuzz.FizzBuzzTest;
import dev.sim0n.app.test.impl.flow.OpaqueConditionTest;
import dev.sim0n.app.test.impl.flow.WeirdLoopTest;
import dev.sim0n.app.test.impl.inheritance.InheritanceTest;
import dev.sim0n.app.test.impl.interfaceoverlap.InterfaceOverlapTest;
import dev.sim0n.app.test.impl.numbers.NumberComparisonTest;
import dev.sim0n.app.test.impl.trycatch.TryCatchTest;
import dev.sim0n.app.test.impl.visitor.VisitorTest;
import dev.sim0n.app.util.BoxPrinter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestRepository {
    private static final String PASS = "[ PASS ]";
    private static final String FAIL = "[ FAIL ]";

    private final Runnable runnable;

    private final List<Test> tests = Arrays.asList(
            new AnnotationTest(),
            new OpaqueConditionTest(),
            new WeirdLoopTest(),
            new InheritanceTest(),
            new EnumConstantsTest(),
            new NumberComparisonTest(),
            new BlowfishTest(),
            new EvaluationTest(),
            new VisitorTest(),
            new FizzBuzzTest(),
            new InterfaceOverlapTest(),
            new TryCatchTest()
    );

    public TestRepository(Runnable runnable) {
        this.runnable = runnable;
    }

    public void run() {
        runnable.run();

        List<TestResult> results = new ArrayList<>();
        long suiteStart = System.currentTimeMillis();

        for (Test test : tests) {
            String name = test.getClass().getSimpleName();
            long start = System.currentTimeMillis();

            PrintStream original = System.out;
            ByteArrayOutputStream capture = new ByteArrayOutputStream();
            System.setOut(new PrintStream(capture));

            boolean passed = true;
            String failMsg = null;

            try {
                test.run();
            } catch (Throwable t) {
                passed = false;
                failMsg = t.getMessage();
            } finally {
                System.setOut(original);
            }

            long duration = System.currentTimeMillis() - start;
            results.add(new TestResult(name, passed, failMsg, duration));
        }

        long totalMs = System.currentTimeMillis() - suiteStart;

        List<String> rows = new ArrayList<>();
        int passed = 0;
        int failed = 0;

        for (TestResult r : results) {
            String status = r.isPassed() ? PASS : FAIL;
            String line = status + "  " + r.getName() + "  (" + r.getDurationMs() + "ms)";
            if (!r.isPassed() && r.getFailMessage() != null) {
                rows.add(line);
                rows.add("         -> " + r.getFailMessage());
            } else {
                rows.add(line);
            }
            if (r.isPassed()) passed++; else failed++;
        }

        BoxPrinter.printBox(" Behavioral Tests", rows);
        System.out.println();
        BoxPrinter.printSummaryBox(" Suite Summary", passed, failed, totalMs);

        if (failed > 0) {
            throw new IllegalStateException(failed + " test(s) failed.");
        }
    }
}
