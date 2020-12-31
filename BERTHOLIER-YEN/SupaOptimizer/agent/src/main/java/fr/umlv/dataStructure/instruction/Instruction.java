package fr.umlv.dataStructure.instruction;

/**
 * The interface Instruction.
 */
public interface Instruction {
    String toString();

    /**
     * Translate opcode to string representation.
     *
     * @param opcode the opcode
     * @return the string representation
     */
    @SuppressWarnings("unused")
    default String translateOpcode(int opcode) {
        return "undefined opcode implementation";
    }

    /**
     * Translate instructions to readable string representation.
     *
     * @return the string representation
     */
    String toReadableInstruction();
}
