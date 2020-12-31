package fr.umlv.supaoptimizer.api;


import java.util.Locale;
import java.util.Objects;

import static fr.umlv.supaoptimizer.api.SupaOptimizer.*;

public class LazyTests {
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

    public static void testLazyInteger() {
        check(42, lazyToInt(() -> 42));
    }

    public static void testLazyIntegerPlus() {
        var a = 10;
        check(17, lazyToInt(() -> a + 7));
    }

    public static void testLazyIntegerMethod() {
        var a = 10;
        check(8, lazyToInt(() -> constInteger()));
    }

    public static void testLazyIntegerMethodWithArgs() {
        var a = 10;
        check(13, lazyToInt(() -> sum(a, 3)));
    }


    public static void testLazyDouble() {
        check(42.0, lazyToDouble(() -> 42.0));
    }

    public static void testLazyDoublePlus() {
        var a = 10.0;
        check(17.0, lazyToDouble(() -> a + 7));
    }

    public static void testLazyDoubleMethod() {
        check(8.0, lazyToDouble(() -> constDouble()));
    }

    public static void testLazyDoubleMethodWithArgs() {
        var a = 10.0;
        check(13.0, lazyToDouble(() -> sum(a, 3)));
    }


    public static void testLazyString() {
        check("hello", lazy(() -> "hello"));
    }

    public static void testLazyStringPlus() {
        var a = "Hello ";
        check("Hello Bob", lazy(() -> a + "Bob"));
    }

    public static void testLazyStringMethod() {
        check("8", lazy(() -> constString()));
    }

    public static void testLazyStringMethodWithArgs() {
        var a = "Hello ";
        check("Hello Bob", lazy(() -> sum(a, "Bob")));
    }


    public static void testLazyBox() {
        check(new Box(2), lazy(() -> new Box(2)));
    }

    public static void testLazyBoxPlus() {
        var a = new Box(10);
        check(new Box(17), lazy(() -> a.plus(7)));
    }

    public static void testLazyBoxMethod() {
        check(new Box(8), lazy(() -> constBox()));
    }

    public static void testLazyBoxMethodWithArgs() {
        var a = new Box(10);
        check(new Box(13), lazy(() -> sum(a, new Box(3))));
    }


    public static void testLocale() {
        check(Locale.getDefault(), lazy(() -> Locale.getDefault()));
    }


    public static void main(String[] args) {
        // integers
        testLazyInteger();
        testLazyIntegerPlus();
        testLazyIntegerMethod();
        testLazyIntegerMethodWithArgs();

        // double
        testLazyDouble();
        testLazyDoublePlus();
        testLazyDoubleMethod();
        testLazyDoubleMethodWithArgs();

        // String
        testLazyString();
        testLazyStringPlus();
        testLazyStringMethod();
        testLazyStringMethodWithArgs();

        // Box
        testLazyBox();
        testLazyBoxPlus();
        testLazyBoxMethod();
        testLazyBoxMethodWithArgs();

        // Locale
        testLocale();
    }
}