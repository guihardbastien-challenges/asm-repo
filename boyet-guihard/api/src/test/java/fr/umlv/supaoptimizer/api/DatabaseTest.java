package fr.umlv.supaoptimizer.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTest {
    private final DataBase db = new DataBase();

    /**
     * Test a simple query (show tables) to test if the connection to the database
     */
    @Test
    void simpleQuery() {
        db.query("show tables");
    }

    /**
     * Create a new table on the database, insert a value and query the value.
     * Delete the new table afterward.
     */
    @Test
    void queryOnNewTable() {
        db.execute("CREATE TABLE IF NOT EXISTS table_test (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100))");
        db.execute("INSERT INTO table_test (name) VALUES (?)", "foo");
        var user = db.query("SELECT id, name FROM table_test ORDER BY id ASC");
        db.execute("DROP TABLE table_test");
        assertEquals("foo", user.get(0).get("name"));
    }
}
