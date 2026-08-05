package dev.sim0n.app.test.impl.evaluation;

import java.util.function.Consumer;

public class Evaluation<T extends Number> {
    private final T first, second;
    private final Consumer<T> evaluator;

    public Evaluation(T first, T second, Consumer<T> evaluator) {
        this.first = first;
        this.second = second;
        this.evaluator = evaluator;
    }

    public T getFirst()                { return first; }
    public T getSecond()               { return second; }
    public Consumer<T> getEvaluator()  { return evaluator; }
}
