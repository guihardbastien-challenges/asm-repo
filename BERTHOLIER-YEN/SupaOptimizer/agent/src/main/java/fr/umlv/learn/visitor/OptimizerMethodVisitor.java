package fr.umlv.learn.visitor;

import fr.umlv.dataStructure.Method;
import fr.umlv.dataStructure.instruction.*;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * The type Optimizer method visitor.
 */
public class OptimizerMethodVisitor extends MethodVisitor implements Opcodes {
    private final Method method;

    /**
     * Instantiates a new Optimizer method visitor.
     *
     * @param methodVisitor the method visitor
     * @param method        the method
     */
    public OptimizerMethodVisitor(MethodVisitor methodVisitor, Method method) {
        super(Opcodes.ASM9, methodVisitor);
        this.method = method;
    }

    /**
     * Insert DB insertion in the bytecode before write *RETURN
     * @param opcode opcode
     */
    @Override
    public void visitInsn(int opcode) {
        method.addInstruction(new NopInstruction(opcode));
        if (opcode == ARETURN || opcode == IRETURN || opcode == DRETURN || opcode == LRETURN || opcode == FRETURN) {
            switch (opcode) {
                case IRETURN, ARETURN, FRETURN -> this.visitInsn(DUP);
                default -> this.visitInsn(DUP2);
            }
            switch (opcode) {
                case ARETURN -> this.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false);
                case IRETURN -> this.visitInvokeDynamicInsn("makeConcatWithConstants", "(I)Ljava/lang/String;", new Handle(H_INVOKESTATIC, "java/lang/invoke/StringConcatFactory", "makeConcatWithConstants", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;", false), "\u0001");
                case DRETURN -> this.visitInvokeDynamicInsn("makeConcatWithConstants", "(D)Ljava/lang/String;", new Handle(H_INVOKESTATIC, "java/lang/invoke/StringConcatFactory", "makeConcatWithConstants", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;", false), "\u0001");
                case LRETURN -> this.visitInvokeDynamicInsn("makeConcatWithConstants", "(J)Ljava/lang/String;", new Handle(H_INVOKESTATIC, "java/lang/invoke/StringConcatFactory", "makeConcatWithConstants", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;", false), "\u0001");
                case FRETURN -> this.visitInvokeDynamicInsn("makeConcatWithConstants", "(F)Ljava/lang/String;", new Handle(H_INVOKESTATIC, "java/lang/invoke/StringConcatFactory", "makeConcatWithConstants", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;", false), "\u0001");
            }
            this.visitLdcInsn(method.getParent().getPackagePath() + method.getParent().getClassName());
            this.visitLdcInsn(method.getName());
            this.visitLdcInsn(method.getDesc());
            this.visitMethodInsn(INVOKESTATIC, "fr/umlv/database/Database", "insertMethodReturnValue", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", false);
        }
        super.visitInsn(opcode);
    }

    @Override
    public void visitIntInsn(int opcode, int operand) {
        method.addInstruction(new IntInstruction(opcode, operand));
        super.visitIntInsn(opcode, operand);
    }

    @Override
    public void visitVarInsn(int opcode, int var) {
        method.addInstruction(new VarInstruction(opcode, var));
        super.visitVarInsn(opcode, var);
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        method.addInstruction(new FieldInstruction(opcode, owner, name, descriptor));
        super.visitFieldInsn(opcode, owner, name, descriptor);

    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        method.addInstruction(new MethodInstruction(opcode, owner, name, descriptor, isInterface));
        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object...
            bootstrapMethodArguments) {
        method.addInstruction(new InvokeDynamicInstruction(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments));
        super.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments);
    }

    @Override
    public void visitLdcInsn(Object value) {
        method.addInstruction(new LdcInstruction(value));
        super.visitLdcInsn(value);
    }

    @Override
    public void visitIincInsn(int var, int increment) {
        method.addInstruction(new IincInstruction(var, increment));
        super.visitIincInsn(var, increment);
    }

    @Override
    public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end,
                                   int index) {
        method.addInstruction(new LocalVariableInstruction(name, descriptor, signature, start, end, index));
        super.visitLocalVariable(name, descriptor, signature, start, end, index);
    }

}
