package fr.umlv.dataStructure.instruction;

import org.objectweb.asm.Opcodes;

/**
 * The type Field instruction.
 */
public record FieldInstruction(int opcode, String owner, String name, String descriptor) implements Instruction {

    @Override
    public String translateOpcode(int opcode) {
        return switch (opcode) {
            case Opcodes.GETFIELD -> "GETFIELD";
            case Opcodes.PUTFIELD -> "PUTFIELD";
            case Opcodes.GETSTATIC -> "GETSTATIC";
            default -> "unrecognized opcode FieldInstruction";
        };
    }

    @Override
    public String toReadableInstruction() {
        return translateOpcode(opcode)+" "+owner+" "+name+" "+descriptor;
    }

    @Override
    public String toString() {
        return opcode+" "+owner+" "+name+" "+descriptor;
    }
}
