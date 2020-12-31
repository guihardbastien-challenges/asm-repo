package fr.umlv.dataStructure.instruction;

/**
 * The type Iinc instruction.
 */
public record IincInstruction(int var, int increment) implements Instruction {

    @Override
    public String toString() {
        return  var + " " + increment;
    }

    @Override
    public String toReadableInstruction() {
        return "IINC " + var + " " + increment;
    }
}
