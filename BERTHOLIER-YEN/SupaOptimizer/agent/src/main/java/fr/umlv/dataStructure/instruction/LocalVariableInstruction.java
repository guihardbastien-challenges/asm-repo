package fr.umlv.dataStructure.instruction;

import org.objectweb.asm.Label;

/**
 * The type Local variable instruction.
 */
public record LocalVariableInstruction(String name, String descriptor, String signature, Label start, Label end,
                                       int index) implements Instruction {


    @Override
    public String toString() {
        return name + " " + descriptor + " " + signature + " " + start + " " + end + " " + index;
    }

    @Override
    public String toReadableInstruction() {
        return "LOCALVARIABLE " + name + " " + descriptor + " " + signature + " " + start + " " + end + " " + index;
    }
}
