package fr.umlv.dataStructure;

/**
 * The type Field.
 */
public class Field {
    private final int access;
    private final String name;
    private final String desc;
    private final String signature;
    private final Object value;

    /**
     * Instantiates a new Field.
     *
     * @param access    the access
     * @param name      the name
     * @param desc      the desc
     * @param signature the signature
     * @param value     the value
     */
    public Field(int access, String name, String desc, String signature, Object value) {
        this.access = access;
        this.name = name;
        this.desc = desc;
        this.signature = signature;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Field{" +
                "access=" + access +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", signature='" + signature + '\'' +
                ", value=" + value +
                '}';
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
}
