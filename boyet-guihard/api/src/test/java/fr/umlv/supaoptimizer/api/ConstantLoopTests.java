package fr.umlv.supaoptimizer.api;

import static fr.umlv.supaoptimizer.api.ConstantTests.*;

public class ConstantLoopTests {
    private static void loop(Runnable action) {
        for (var i = 0; i < 100_000; i++) {
            action.run();
        }
    }

    public static void main(String[] args) {
        // integers
        loop(() -> testConstantInteger());
        loop(() -> testConstantIntegerPlus());
        loop(() -> testConstantIntegerMethod());
        loop(() -> testConstantIntegerMethodWithArgs());

        // double
        loop(() -> testConstantDouble());
        loop(() -> testConstantDoublePlus());
        loop(() -> testConstantDoubleMethod());
        loop(() -> testConstantDoubleMethodWithArgs());

        // String
        loop(() -> testConstantString());
        loop(() -> testConstantStringPlus());
        loop(() -> testConstantStringMethod());
        loop(() -> testConstantStringMethodWithArgs());

        // Box
        loop(() -> testConstantBox());
        loop(() -> testConstantBoxPlus());
        loop(() -> testConstantBoxMethod());
        loop(() -> testConstantBoxMethodWithArgs());
    }
}