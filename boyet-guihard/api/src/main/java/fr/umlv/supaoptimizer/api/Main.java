package fr.umlv.supaoptimizer.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            read();
        } catch (IOException e) {
            System.out.println("Error while trying to SuperOptimize");
        }
    }

    /**
     * Read the path to the .class file given by the user.
     *
     * @throws IOException if something went wrong while optimizing
     */
    private static void read() throws IOException {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter the class you want to optimize");
            String input = scanner.next();
            InputStream inputStream = input(input);
            OutputStream outputStream = output(input);
            Learn.optimizedClass(inputStream, outputStream);
        }
    }

    /**
     * Create an InputStream with the String in argument. Throw an error if the file doesn't exist
     *
     * @param input String representing a path
     * @return InputStream of the file
     */
    private static InputStream input(String input) {
        try {
            return Files.newInputStream(Path.of(input));
        } catch (IOException e) {
            throw new IllegalArgumentException("File doesn't exist");
        }
    }


    /**
     * Create an OutputStream with the String in argument. Throw an error if it's impossible to create a new file
     *
     * @param output String representing a path
     * @return OutputStream of the file
     */
    private static OutputStream output(String output) {
        try {
            return Files.newOutputStream(Path.of("Optimize" + output));
        } catch (IOException e) {
            throw new IllegalStateException("Impossible to create a new file for the output");
        }
    }
}
