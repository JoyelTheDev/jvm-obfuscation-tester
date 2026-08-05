package dev.sim0n.app.test.factory;

import dev.sim0n.app.test.TestRepository;

public enum SimpleTestRepositoryFactory {
    INSTANCE;

    public TestRepository build() {
        return new TestRepository(() -> System.out.println("Building test repository"));
    }
}
