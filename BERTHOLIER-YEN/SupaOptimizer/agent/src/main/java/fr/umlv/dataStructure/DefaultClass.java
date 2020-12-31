package fr.umlv.dataStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Default class.
 * A representation of a class
 */
public class DefaultClass {
    private final int version;
    private final String className;
    private final String signature;
    private final List<fr.umlv.dataStructure.Field> fields;
    private final List<fr.umlv.dataStructure.Method> methods;
    private final String[] interfaces;
    private final String parentClassName;
    private final int accessLevel;
    private final String packagePath;

    /**
     * Instantiates a new Default class.
     *
     * @param version         the version
     * @param accessLevel     the access level
     * @param className       the class name
     * @param signature       the signature
     * @param parentClassName the parent class name
     * @param interfaces      the interfaces
     */
    public DefaultClass(int version, int accessLevel, String className, String signature, String parentClassName, String[] interfaces) {
        this.version = version;
        this.accessLevel = accessLevel;
        this.interfaces = interfaces;
        this.signature = signature;
        this.parentClassName = parentClassName;
        this.fields = new ArrayList<>();
        this.methods = new ArrayList<>();
        if (className.contains("/")) {
            String[] split = className.split("/");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < split.length - 1; i++) {
                stringBuilder.append(split[i]).append("/");
            }
            this.className = split[split.length - 1];
            this.packagePath = stringBuilder.toString();
        } else {
            this.className = className;
            packagePath = "";
        }
    }

    /**
     * Gets package path.
     *
     * @return the package path
     */
    public String getPackagePath() {
        return packagePath;
    }

    /**
     * Gets class name.
     *
     * @return the class name
     */
    public String getClassName() {
        return className;
    }

    /**
     * Gets fields.
     *
     * @return the fields
     */
    public List<fr.umlv.dataStructure.Field> getFields() {
        return fields;
    }

    /**
     * Gets methods.
     *
     * @return the methods
     */
    public List<fr.umlv.dataStructure.Method> getMethods() {
        return methods;
    }

    /**
     * Add method.
     *
     * @param method the method to add
     */
    public void addMethod(Method method) {
        methods.add(method);
    }

    /**
     * Add field.
     *
     * @param field the field to add
     */
    public void addField(Field field) {
        fields.add(field);
    }

    @Override
    public String toString() {
        return "DefaultClass{" +
                "version=" + version +
                ", className='" + className + '\'' +
                ", signature='" + signature + '\'' +
                ", fields=" + fields +
                ", methods=" + methods +
                ", interfaces=" + Arrays.toString(interfaces) +
                ", parentClassName='" + parentClassName + '\'' +
                ", accessLevel=" + accessLevel +
                ", packagePath='" + packagePath + '\'' +
                '}';
    }
}
