package fr.umlv.dataStructure.instruction;

/**
 * The type Int instruction.
 */
public record IntInstruction(int opcode, int operand) implements Instruction {

    @Override
    public String toString() {
        return opcode + " " + operand;
    }

    @Override
    public String toReadableInstruction() {
        return "IntInstruction{" +
                "opcode=" + opcode +
                ", operand=" + operand +
                '}';
    }
}
