package fr.umlv.supaoptimizer.api;

import org.objectweb.asm.*;

import java.io.IOException;
import java.nio.file.Files;

public class RewriteBytecode {

    /**
     * Classvisitor
     */
    class OptimizerClassVisitor extends ClassVisitor {
        private ClassVisitor cv;

        public OptimizerClassVisitor(ClassVisitor cv) {
            super(Opcodes.ASM7, cv);
            this.cv = cv;
        }

        @Override
        public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
            System.out.println(" class: " + name);
            super.visit(version, access, name, signature, superName, interfaces);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
            if (mv != null && !name.equals("<init>")) {
                mv = new OptimizerMethodVisitor(mv);
            }
            return mv;
        }
    }

    /**
     * MethodVisitor that can insert a static method in the class we want to modify (config.pathtoclass..)
     * The static method inserted in this example is located in the TestSideEffect class
     */
    class OptimizerMethodVisitor extends MethodVisitor {
        public OptimizerMethodVisitor(MethodVisitor mv) {
            super(Opcodes.ASM7, mv);
        }

        @Override
        public void visitInsn(int opcode) {
            if (opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN) {
                mv.visitInsn(Opcodes.DUP);
                mv.visitMethodInsn(Opcodes.INVOKESTATIC,
                        "fr/umlv/supaoptimizer/api/Optimize/Test",
                        "print",
                        "()V", false);
            }
            mv.visitInsn(opcode);
        }
    }

    /**
     * @throws IOException
     */
    public void changeBytecode() throws IOException {
        // Read
        try (var input = Files.newInputStream(Config.pathToClass)) {
            ClassReader classReader = new ClassReader(input);
            ClassWriter cw = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
            var classVisitor = new OptimizerClassVisitor(cw);
            classReader.accept(classVisitor, 0);
            // rewrite
            byte[] bytes = cw.toByteArray();
            try (var stream = Files.newOutputStream(Config.pathToClass)) {
                stream.write(bytes);
            } catch (Exception e) {
                throw new IOException();
            }
        } catch (Exception e) {
            throw new IOException();
        }
    }

    /*
    public static void main(String[] args) throws IOException {
        var optimizer = new RewriteBytecode();
        optimizer.changeBytecode();
        if (Config.DEBUG == true) {
            DebugUtils.printBytecode(Config.pathToClass);
            DebugUtils.generateHelloWorld();
        }
    }
     */
}
