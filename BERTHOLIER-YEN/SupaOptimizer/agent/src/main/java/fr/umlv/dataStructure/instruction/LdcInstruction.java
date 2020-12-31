package fr.umlv.dataStructure.instruction;

/**
 * The type Ldc instruction.
 */
public record LdcInstruction(Object value) implements Instruction {

    @Override
    public String toString() {
        return "LDC "+value;
    }

    @Override
    public String toReadableInstruction() {
        return value.toString();
    }
}
