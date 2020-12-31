package fr.umlv;

import fr.umlv.database.Database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * The Main class for launch project.
 */
public class Main {

    /**
     * The entry point of application.
     * Create the database and the tables
     * Run the agent on client JAR
     *
     * @param args the input arguments
     * @throws IOException          the io exception
     * @throws InterruptedException the interrupted exception
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length < 1) {
            System.out.println("The first argument must be a JAR file");
            return;
        }
        Database database = new Database();
        database.createTables();
        Runtime runtime = Runtime.getRuntime();
        runAgent(runtime, args);
        showInfo(database);
        Files.deleteIfExists(Path.of("db.mv.db"));
    }

    /**
     * Show info of DB
     * @param database database
     */
    private static void showInfo(Database database){
        System.out.println("VALEUR DE RETURN DES METHODES");
        database.get("METHOD").forEach(System.out::println);
    }

    /**
     * Run the agent and show the error and input stream and wait the end of process
     *
     * @param runtime runtime
     * @param args args from main
     * @throws IOException IoException
     * @throws InterruptedException InterruptedException
     */
    private static void runAgent(Runtime runtime, String[] args) throws IOException, InterruptedException {
        Process exec = runtime.exec("java --enable-preview -javaagent:agent-1.0-SNAPSHOT.jar -jar " + args[0]);
        String line;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(exec.getErrorStream()))) {
            while ((line = in.readLine()) != null) System.out.println(line);
        }
        try (BufferedReader in = new BufferedReader(new InputStreamReader(exec.getInputStream()))) {
            while ((line = in.readLine()) != null) System.out.println(line);
        }
        exec.waitFor();
    }

}
