package fr.umlv.supaoptimizer.api;

import org.jdbi.v3.core.Jdbi;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * DataBase class aim to initialize, or create if not exists, a connection to H2 Relational Database.
 * <p>
 * This class allows you to request the DataBase using JDBI 3.
 */

public class DataBase {
    private static final String URL = "jdbc:h2:~/supraoptimizer_bd";
    private static final String USER = "admin";
    private static final String PASSWORD = "password";
    private final Jdbi jdbi;

    /**
     * By creating a new DataBase object, a Jdbi object is created.
     * If the DataBase's not existing, it creates it at the URL parameter.
     */
    public DataBase() {
        jdbi = Jdbi.create(URL, USER, PASSWORD);
    }

    /**
     * Open a connection and execute the request in operation.
     * It executes operation who doesn't need a return (such as insert or create).
     *
     * @param operation Correspond to a SQL request. Can not be null
     * @param values    Arguments corresponding to the values to insert
     */
    public void execute(String operation, Object... values) {
        Objects.requireNonNull(operation);
        jdbi.useHandle(handle -> handle.execute(operation, values));
    }

    /**
     * Connect to the database and query it by the request String and give you the result.
     *
     * @param request Correspond to a SQL request. Can not be null
     * @return Correspond to a List of row in the database, represented by a List of Map<String, Object> where String correspond to the column name and Object the value.
     */

    public List<Map<String, Object>> query(String request) {
        Objects.requireNonNull(request);
        return jdbi.withHandle(handle -> handle.createQuery(request).mapToMap().list());
    }
}
