package fr.umlv.dataStructure.instruction;

import org.objectweb.asm.Opcodes;

/**
 * The type Nop instruction.
 */
public record NopInstruction(int opcode) implements Instruction {

    @Override
    public String translateOpcode(int opcode) {
        return switch (opcode) {
            case Opcodes.NOP -> "NOP";
            case Opcodes.RETURN -> "RETURN";
            case Opcodes.DUP -> "DUP";
            case Opcodes.ICONST_0 -> "ICONST_0";
            case Opcodes.ARETURN -> "ARETURN";
            case Opcodes.ICONST_1 -> "ICONST_1";
            case Opcodes.ISUB -> "ISUB";
            case Opcodes.ARRAYLENGTH -> "ARRAYLENGTH";
            case Opcodes.AALOAD -> "AALOAD";
            case Opcodes.POP -> "POP";
            case Opcodes.IRETURN -> "IRETURN";
            case Opcodes.ACONST_NULL -> "ACONST_NULL";
            case Opcodes.AASTORE -> "AASTORE";
            case Opcodes.ATHROW -> "ATHROW";


            default -> "unrecognized opcode NopInstruction";
        };
    }

    @Override
    public String toReadableInstruction() {
        return translateOpcode(opcode);
    }

    @Override
    public String toString() {
        return String.valueOf(opcode);
    }
}
