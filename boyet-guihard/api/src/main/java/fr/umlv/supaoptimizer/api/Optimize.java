package fr.umlv.supaoptimizer.api;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Objects;

/**
 * Aim to optimize the code in a MethodVisitor in the "SuperOptimizer" way.
 * It means that the optimized method could be wrong in it's execution, but it will do it faster.
 */
public class Optimize {

    private final MethodVisitor method;

    /**
     * Need a MethodVisitor to construct the class
     *
     * @param method MethodVisitor corresponding to a method to optimize
     */
    public Optimize(MethodVisitor method) {
        Objects.requireNonNull(method);
        this.method = method;
    }

    /**
     * MethodVisitor aims to finding constant in the code.
     *
     * @return MethodVisitor New implantation of the MethodVisitor
     */
    public MethodVisitor findingConstant() {
        return new MethodVisitor(Opcodes.ASM7) {

            @Override
            public void visitInsn(int opcode) {
                if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN) || opcode == Opcodes.ATHROW) {
                    method.visitInsn(Opcodes.DUP);
                    // Trying to add a System.out.println() for the test
                    method.visitMethodInsn(Opcodes.INVOKESTATIC, "fr/umlv/supaoptimizer/api/Optimize/Test", "print", "()V", false);
                }
                super.visitInsn(opcode);
            }
        };
    }


    /**
     * A simple Static Class which print nothing.
     * Use for a test in visitInsn
     */
    @SuppressWarnings("unused")
    public static class Test {
        public static void print() {
            System.out.println();
        }
    }

}
