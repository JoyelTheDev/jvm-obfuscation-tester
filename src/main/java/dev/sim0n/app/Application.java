package dev.sim0n.app;

import dev.sim0n.app.test.TestRepository;
import dev.sim0n.app.test.factory.SimpleTestRepositoryFactory;
import dev.sim0n.app.util.BoxPrinter;

import java.util.Arrays;

public class Application {
    private final TestRepository testRepository = SimpleTestRepositoryFactory.INSTANCE.build();

    public void run() {
        BoxPrinter.printBox(" JVM Obfuscation Tester", Arrays.asList(
            "  Verifies bytecode correctness after obfuscation.",
            "  Fibonacci gate : fib(9) == 34",
            "  Tests          : Behavioral + Opcode suites"
        ));
        System.out.println();

        if (fibRecursion(9) == 34) {
            this.testRepository.run();
        } else {
            throw new IllegalStateException("Fibonacci sequence is incorrect!");
        }
    }

    public int fibRecursion(int n) {
        if (n <= 1) return n;
        if (n == 2) return 1;
        return fibRecursion(n - 1) + fibRecursion(n - 2);
    }
}
