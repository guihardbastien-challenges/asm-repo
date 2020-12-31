package fr.umlv.database;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DatabaseTest {

    @Test
    void insertAndSelectInDatabase() throws IOException {
        Database database = new Database();
        database.createTables();
        database.insert("CLASS", "java/util/Map");
        List<Map<String, Object>> classes = database.get("CLASS");
        assertAll(() -> assertEquals(1, classes.get(0).get("id")),
                () -> assertEquals("java/util/Map", classes.get(0).get("name"))
        );
        Files.delete(Path.of("db.mv.db"));
    }

    @Test
    void insertMethod() throws IOException {
        Database database = new Database();
        database.createTables();
        Database.insertMethodReturnValue("titi", "String","tutu", "toto");
        List<Map<String, Object>> data = database.get("METHOD");
        assertAll(() -> assertEquals(1, data.get(0).get("id")),
                () -> assertEquals("String", data.get(0).get("classname")),
                () -> assertEquals("toto", data.get(0).get("methoddesc")),
                () -> assertEquals("tutu", data.get(0).get("methodname")),
                () -> assertEquals("titi", data.get(0).get("value")));
        Files.delete(Path.of("db.mv.db"));
    }

}