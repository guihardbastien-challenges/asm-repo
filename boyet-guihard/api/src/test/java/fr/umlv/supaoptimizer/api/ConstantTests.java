package fr.umlv.supaoptimizer.api;

import static fr.umlv.supaoptimizer.api.SupaOptimizer.constant;

import java.util.Locale;
import java.util.Objects;

public class ConstantTests {
    private static void check(Object expected, Object value) {
        if (!Objects.equals(expected, value)) {
            throw new AssertionError("expected " + expected + " but was value " + value);
        }
    }

    public record Box(int x) {
        public Box plus(int v) {
            return new Box(x + v);
        }
    }

    private static int sum(int a, int b) {
        return a + b;
    }

    private static double sum(double a, double b) {
        return a + b;
    }

    private static String sum(String a, String b) {
        return a.concat(b);
    }

    private static Box sum(Box a, Box b) {
        return a.plus(b.x);
    }

    private static int constInteger() {
        return 8;
    }

    private static double constDouble() {
        return 8.0;
    }

    private static String constString() {
        return "8";
    }

    private static Box constBox() {
        return new Box(8);
    }

    public static void testConstantInteger() {
        check(42, constant(42));
    }

    public static void testConstantIntegerPlus() {
        var a = 10;
        check(17, constant(a + 7));
    }

    public static void testConstantIntegerMethod() {
        var a = 10;
        check(8, constant(constInteger()));
    }

    public static void testConstantIntegerMethodWithArgs() {
        var a = 10;
        check(13, constant(sum(a, 3)));
    }

    public static void testConstantDouble() {
        check(42.0, constant(42.0));
    }

    public static void testConstantDoublePlus() {
        var a = 10.0;
        check(17.0, constant(a + 7));
    }

    public static void testConstantDoubleMethod() {
        check(8.0, constant(constDouble()));
    }

    public static void testConstantDoubleMethodWithArgs() {
        var a = 10.0;
        check(13.0, constant(sum(a, 3)));
    }


    public static void testConstantString() {
        check("hello", constant("hello"));
    }

    public static void testConstantStringPlus() {
        var a = "Hello ";
        check("Hello Bob", constant(a + "Bob"));
    }

    public static void testConstantStringMethod() {
        check("8", constant(constString()));
    }

    public static void testConstantStringMethodWithArgs() {
        var a = "Hello ";
        check("Hello Bob", constant(sum(a, "Bob")));
    }


    public static void testConstantBox() {
        check(new Box(2), constant(new Box(2)));
    }

    public static void testConstantBoxPlus() {
        var a = new Box(10);
        check(new Box(17), constant(a.plus(7)));
    }

    public static void testConstantBoxMethod() {
        check(new Box(8), constant(constBox()));
    }

    public static void testConstantBoxMethodWithArgs() {
        var a = new Box(10);
        check(new Box(13), constant(sum(a, new Box(3))));
    }


    public static void testLocale() {
        check(Locale.getDefault(), constant(Locale.getDefault()));
    }


    public static void main(String[] args) {
        // integer
        testConstantInteger();
        testConstantIntegerPlus();
        testConstantIntegerMethod();
        testConstantIntegerMethodWithArgs();

        // double
        testConstantDouble();
        testConstantDoublePlus();
        testConstantDoubleMethod();
        testConstantDoubleMethodWithArgs();

        // String
        testConstantString();
        testConstantStringPlus();
        testConstantStringMethod();
        testConstantStringMethodWithArgs();

        // Box
        testConstantBox();
        testConstantBoxPlus();
        testConstantBoxMethod();
        testConstantBoxMethodWithArgs();

        // Locale
        testLocale();
    }
}