package fr.umlv;

/**
 * The type Supa optimizer.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public final class SupaOptimizer {
    private SupaOptimizer() {
        throw new AssertionError();
    }

    /**
     * Constant t.
     *
     * @param <T>   the type parameter
     * @param value the value
     * @return the t
     */
    public static <T> T constant(T value) {
        return value;
    }

    /**
     * Constant boolean.
     *
     * @param value the value
     * @return the boolean
     */
    @SuppressWarnings("BooleanParameter")
    public static boolean constant(boolean value) {
        return value;
    }

    /**
     * Constant byte.
     *
     * @param value the value
     * @return the byte
     */
    public static byte constant(byte value) {
        return value;
    }

    /**
     * Constant char.
     *
     * @param value the value
     * @return the char
     */
    public static char constant(char value) {
        return value;
    }

    /**
     * Constant short.
     *
     * @param value the value
     * @return the short
     */
    public static short constant(short value) {
        return value;
    }

    /**
     * Constant int.
     *
     * @param value the value
     * @return the int
     */
    public static int constant(int value) {
        return value;
    }

    /**
     * Constant long.
     *
     * @param value the value
     * @return the long
     */
    public static long constant(long value) {
        return value;
    }

    /**
     * Constant float.
     *
     * @param value the value
     * @return the float
     */
    public static float constant(float value) {
        return value;
    }

    /**
     * Constant double.
     *
     * @param value the value
     * @return the double
     */
    public static double constant(double value) {
        return value;
    }

    /**
     * Dispatch t.
     *
     * @param <T>   the type parameter
     * @param value the value
     * @return the t
     */
    public static <T> T dispatch(T value) {
        return value;
    }

    /**
     * The interface Value factory.
     *
     * @param <T> the type parameter
     * @param <X> the type parameter
     */
    @FunctionalInterface
    public interface ValueFactory<T, X extends Throwable> {
        /**
         * Create t.
         *
         * @return the t
         * @throws X the x
         */
        T create() throws X;
    }

    /**
     * The interface To int value factory.
     *
     * @param <X> the type parameter
     */
    @FunctionalInterface
    public interface ToIntValueFactory<X extends Throwable> {
        /**
         * Create int.
         *
         * @return the int
         * @throws X the x
         */
        int create() throws X;
    }

    /**
     * The interface To long value factory.
     *
     * @param <X> the type parameter
     */
    @FunctionalInterface
    public interface ToLongValueFactory<X extends Throwable> {
        /**
         * Create long.
         *
         * @return the long
         * @throws X the x
         */
        long create() throws X;
    }

    /**
     * The interface To double value factory.
     *
     * @param <X> the type parameter
     */
    @FunctionalInterface
    public interface ToDoubleValueFactory<X extends Throwable> {
        /**
         * Create double.
         *
         * @return the double
         * @throws X the x
         */
        double create() throws X;
    }

    /**
     * Lazy t.
     *
     * @param <T>     the type parameter
     * @param <X>     the type parameter
     * @param factory the factory
     * @return the t
     * @throws X the x
     */
    public static <T, X extends Throwable> T lazy(ValueFactory<? extends T, ? extends X> factory) throws X {
        return factory.create();
    }

    /**
     * Lazy to int int.
     *
     * @param <X>     the type parameter
     * @param factory the factory
     * @return the int
     * @throws X the x
     */
    public static <X extends Throwable> int lazyToInt(ToIntValueFactory<? extends X> factory) throws X {
        return factory.create();
    }

    /**
     * Lazy to long long.
     *
     * @param <X>     the type parameter
     * @param factory the factory
     * @return the long
     * @throws X the x
     */
    @SuppressWarnings("unused")
    public static <X extends Throwable> long lazyToLong(ToLongValueFactory<? extends X> factory) throws X {
        return factory.create();
    }

    /**
     * Lazy to double double.
     *
     * @param <X>     the type parameter
     * @param factory the factory
     * @return the double
     * @throws X the x
     */
    public static <X extends Throwable> double lazyToDouble(ToDoubleValueFactory<? extends X> factory) throws X {
        return factory.create();
    }
}