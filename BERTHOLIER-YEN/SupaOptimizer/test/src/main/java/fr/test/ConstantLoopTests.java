package fr.test;

import static fr.test.ConstantTests.*;

public class ConstantLoopTests {
    private static void loop(Runnable action) {
        for(var i = 0; i < 10; i++) {
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
