package fr.umlv.learn.visitor;

import fr.umlv.database.Database;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptimizerClassVisitorTest {

    private final OptimizerClassVisitor optimizerClassVisitor;

    public OptimizerClassVisitorTest() throws IOException {
        new Database().createTables();
        ClassReader classReader = new ClassReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("DefaultClass.class")));
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        optimizerClassVisitor = new OptimizerClassVisitor(classWriter);
        classReader.accept(optimizerClassVisitor, 0);
        Files.delete(Path.of("db.mv.db"));
    }

    @Test
    void canVisitClass() {
        assertEquals("DefaultClass", optimizerClassVisitor.getDefaultClass().getClassName());
    }

    @Test
    void canVisitMethod() {
        assertEquals("<init>", optimizerClassVisitor.getDefaultClass().getMethods().get(0).getName());
    }

}