package fr.umlv.dataStructure.instruction;

import org.objectweb.asm.Opcodes;

/**
 * The type Method instruction.
 */
public record MethodInstruction(int opcode, String owner, String name, String descriptor,
                                boolean isInterface) implements Instruction {


    @Override
    public String translateOpcode(int opcode) {
        return switch (opcode) {
            case Opcodes.INVOKEDYNAMIC -> "INVOKEDYNAMIC";
            case Opcodes.INVOKEINTERFACE -> "INVOKEINTERFACE";
            case Opcodes.INVOKESPECIAL -> "INVOKESPECIAL";
            case Opcodes.INVOKESTATIC -> "INVOKESTATIC";
            case Opcodes.INVOKEVIRTUAL -> "INVOKEVIRTUAL";
            default -> "unrecognized opcode MethodInstruction";
        };
    }

    @Override
    public String toReadableInstruction() {
        return translateOpcode(opcode) + " " + owner + " " + name + " " + descriptor;
    }

    @Override
    public String toString() {
        return opcode + " " + owner + " " + name + " " + descriptor;
    }
}
