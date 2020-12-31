package fr.umlv.supaoptimizer.api;


import org.objectweb.asm.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Learn tends to find where the bytecode could be improve
 * <p>
 * It extends abstract ClassVisitor in order to run through the class
 */
public class Learn extends ClassVisitor {

    private final ClassVisitor classVisitor;


    /**
     * Constructor, admits a ClassVisitor
     *
     * @param classVisitor a ClassVisitor of the file to optimize
     */
    private Learn(ClassVisitor classVisitor) {
        super(Opcodes.ASM7, classVisitor);
        this.classVisitor = classVisitor;
    }


    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor method = classVisitor.visitMethod(access, name, desc, signature, exceptions);
        if (method != null && !name.equals("<init>") && !name.equals("main")) {
            return new Optimize(method).findingConstant();
        }
        return method;
    }

    /**
     * Create a .class from the inputStream optimized to the outputStream
     *
     * @param inputStream  The InputStream corresponding to the class to optimize
     * @param outputStream The OutputStream which will hold the optimized .class
     */
    public static void optimizedClass(InputStream inputStream, OutputStream outputStream) throws IOException {
        try (InputStream input = inputStream) {
            ClassReader classReader = new ClassReader(input);
            ClassWriter classWriter = new ClassWriter(classReader, 0);
            classReader.accept(new Learn(classWriter), 0);
            byte[] b1 = classWriter.toByteArray();

            try (OutputStream output = outputStream) {
                output.write(b1);
            } catch (IOException ioException) {
                throw new IOException();
            }
        } catch (IOException ioException) {
            throw new IOException();
        }
    }
}
