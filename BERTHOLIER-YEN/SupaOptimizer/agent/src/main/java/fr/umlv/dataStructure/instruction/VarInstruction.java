package fr.umlv.dataStructure.instruction;

import org.objectweb.asm.Opcodes;

/**
 * The type Var instruction.
 */
public record VarInstruction(int opcode, int var) implements Instruction {

    @Override
    public String toString() {
        return opcode + " " + var;
    }

    @Override
    public String translateOpcode(int opcode) {
        return switch (opcode) {
            case Opcodes.ALOAD -> "ALOAD";
            case Opcodes.ILOAD -> "ILOAD";
            case Opcodes.LLOAD -> "LLOAD";
            case Opcodes.DLOAD -> "DLOAD";
            case Opcodes.ASTORE -> "ASTORE";
            case Opcodes.ISTORE -> "ISTORE";
            case Opcodes.LSTORE -> "LSTORE";
            case Opcodes.DSTORE -> "DSTORE";
            default -> "unrecognized opcode VarInstruction";
        };
    }

    @Override
    public String toReadableInstruction() {
        return translateOpcode(opcode) + " " + var;
    }
}
