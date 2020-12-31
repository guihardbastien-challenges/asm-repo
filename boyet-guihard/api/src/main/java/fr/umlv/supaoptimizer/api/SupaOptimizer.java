package fr.umlv.supaoptimizer.api;

public final class SupaOptimizer {
    private SupaOptimizer() {
        throw new AssertionError();
    }

    public static <T> T constant(T value) {
        return value;
    }
    public static boolean constant(boolean value) {
        return value;
    }
    public static byte constant(byte value) {
        return value;
    }
    public static char constant(char value) {
        return value;
    }
    public static short constant(short value) {
        return value;
    }
    public static int constant(int value) {
        return value;
    }
    public static long constant(long value) {
        return value;
    }
    public static float constant(float value) {
        return value;
    }
    public static double constant(double value) {
        return value;
    }

    public static <T> T dispatch(T value) {
        return value;
    }

    @FunctionalInterface
    public interface ValueFactory<T, X extends Throwable> {
        T create() throws X;
    }
    @FunctionalInterface
    public interface ToIntValueFactory<X extends Throwable> {
        int create() throws X;
    }
    @FunctionalInterface
    public interface ToLongValueFactory<X extends Throwable> {
        long create() throws X;
    }
    @FunctionalInterface
    public interface ToDoubleValueFactory<X extends Throwable> {
        double create() throws X;
    }

    public static <T, X extends Throwable> T lazy(ValueFactory<? extends T, ? extends X> factory) throws X {
        return factory.create();
    }
    public static <X extends Throwable> int lazyToInt(ToIntValueFactory<? extends X> factory) throws X {
        return factory.create();
    }
    public static <X extends Throwable> long lazyToLong(ToLongValueFactory<? extends X> factory) throws X {
        return factory.create();
    }
    public static <X extends Throwable> double lazyToDouble(ToDoubleValueFactory<? extends X> factory) throws X {
        return factory.create();
    }
}