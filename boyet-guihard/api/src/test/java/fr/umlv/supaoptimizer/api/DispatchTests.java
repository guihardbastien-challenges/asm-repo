package fr.umlv.supaoptimizer.api;


import java.util.Objects;

import static fr.umlv.supaoptimizer.api.SupaOptimizer.dispatch;

public class DispatchTests {
    private static void check(Object expected, Object value) {
        if (!Objects.equals(expected, value)) {
            throw new AssertionError("expected " + expected + " but was value " + value);
        }
    }

    sealed interface Itf {
        int m(int i);

        record A() implements Itf {
            public int m(int i) {
                return i + 1;
            }
        }

        record B() implements Itf {
            public int m(int i) {
                return i + 2;
            }
        }
    }

    sealed interface Sole {
        double m(double d);

        record Impl() implements Sole {
            public double m(double d) {
                return d + 2.0;
            }
        }
    }

    public static void testDispatchA() {
        Itf itf = new Itf.A();
        check(11, dispatch(itf).m(10));
    }

    public static void testDispatchB() {
        Itf itf = new Itf.B();
        check(12, dispatch(itf).m(10));
    }

    public static void testDispatchSole() {
        Sole sole = new Sole.Impl();
        check(2.0, dispatch(sole).m(0.0));
    }


    public static void main(String[] args) {
        testDispatchA();
        testDispatchB();
        testDispatchSole();
    }
}