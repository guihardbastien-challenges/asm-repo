package fr.umlv.learn;

import fr.umlv.database.Database;
import fr.umlv.learn.visitor.OptimizerClassVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class AnalyserTest {

    void canDetectUnusedField() throws IOException {
        Database database = new Database();
        database.createTables();
        ClassReader classReader = new ClassReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("OptimizerMethodVisitor.class")));
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        OptimizerClassVisitor optimizerClassVisitor = new OptimizerClassVisitor(classWriter);
        classReader.accept(optimizerClassVisitor, 0);
        Analyser.detectUnusedField(optimizerClassVisitor.getDefaultClass());
        List<Map<String, Object>> data = database.get("FIELDUNUSED");
        System.out.println(data);
        assertAll(() -> assertEquals(1, data.get(0).get("id")),
                () -> assertEquals("fr/umlv/learn/visitor/OptimizerMethodVisitor", data.get(0).get("classname")),
                () -> assertEquals("instructions", data.get(0).get("fieldname")),
                () -> assertNull(data.get(0).get("desc")));
        Files.delete(Path.of("db.mv.db"));
    }
}