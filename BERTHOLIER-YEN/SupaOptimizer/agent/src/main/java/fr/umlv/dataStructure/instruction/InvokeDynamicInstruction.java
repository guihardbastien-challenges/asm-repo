package fr.umlv.dataStructure.instruction;

import org.objectweb.asm.Handle;

import java.util.Arrays;

/**
 * The type Invoke dynamic instruction.
 */
public record InvokeDynamicInstruction(String name, String descriptor, Handle bootstrapMethodHandle, Object[] bootstrapMethodArguments) implements Instruction {

    @Override
    public String toString() {
        return "INVOKEDYNAMIC "+name+descriptor+"[\n    "+bootstrapMethodHandle+ Arrays.toString(bootstrapMethodArguments) +"\n    ]";
    }

    @Override
    public String toReadableInstruction() {
        return name+descriptor+"[\n    "+bootstrapMethodHandle+ Arrays.toString(bootstrapMethodArguments) +"\n    ]";
    }

}
