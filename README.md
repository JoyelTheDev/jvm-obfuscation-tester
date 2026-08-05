# JVM Obfuscation Tester

A test suite for verifying JVM bytecode correctness after obfuscation.

> Forked from [sim0n](https://github.com/sim0n) — thanks to sim0n for the original work!

## What it does

Runs two suites of tests against the JVM to confirm that an obfuscator has not broken runtime behavior:

- **Behavioral tests** — annotations, control flow, crypto, enums, inheritance, interfaces, try/catch, visitor pattern, FizzBuzz, recursive Fibonacci
- **Opcode tests** — every major JVM instruction across int, long, float, double, array, and control flow categories

## Usage

```bash
java -jar obf-test.jar
```

## How it works

Run the jar before and after obfuscation. If all tests pass both times, the obfuscator preserved correct behavior. Any `[ FAIL ]` entry tells you exactly which bytecode pattern broke.
