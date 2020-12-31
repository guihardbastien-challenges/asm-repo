package fr.umlv.dataStructure;

import fr.umlv.dataStructure.instruction.Instruction;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The type Method.
 */
public class Method {
    private final int access;
    private final String name;
    private final String desc;
    private final String signature;
    private final String[] exceptions;
    private final ArrayList<Instruction> instructions;
    private final DefaultClass parent;

    /**
     * Instantiates a new Method.
     *
     * @param access     the access
     * @param name       the name
     * @param desc       the desc
     * @param signature  the signature
     * @param exceptions the exceptions
     * @param parent     the parent
     */
    public Method(int access, String name, String desc, String signature, String[] exceptions, DefaultClass parent) {
        this.access = access;
        this.name = name;
        this.desc = desc;
        this.signature = signature;
        this.exceptions = exceptions;
        this.parent = parent;
        instructions = new ArrayList<>();
    }

    /**
     * Gets parent.
     *
     * @return the parent
     */
    public DefaultClass getParent() {
        return parent;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets desc.
     *
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Add instruction.
     *
     * @param instruction the instruction
     */
    public void addInstruction(Instruction instruction) {
        instructions.add(instruction);
    }

    /**
     * Gets instructions.
     *
     * @return the instructions
     */
    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    @Override
    public String toString() {
        return "Method{" +
                "access=" + access +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", signature='" + signature + '\'' +
                ", exceptions=" + Arrays.toString(exceptions) +
                ", instructions=" + instructions +
                '}';
    }
}
