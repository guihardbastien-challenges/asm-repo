package fr.umlv.learn.visitor;

import fr.umlv.dataStructure.DefaultClass;
import fr.umlv.dataStructure.Field;
import fr.umlv.dataStructure.Method;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * The type Optimizer class visitor.
 * Store all information about target class in defaultClass.
 */
public class OptimizerClassVisitor extends ClassVisitor implements Opcodes {
    private DefaultClass defaultClass;

    /**
     * Instantiates a new Optimizer class visitor.
     *
     * @param classVisitor the class visitor
     */
    public OptimizerClassVisitor(ClassVisitor classVisitor) {
        super(Opcodes.ASM9, classVisitor);
    }

    /**
     * Gets default class.
     *
     * @return the default class
     */
    public DefaultClass getDefaultClass() {
        return defaultClass;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.defaultClass = new DefaultClass(version, access, name, signature, superName, interfaces);
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);
        Method method = new Method(access, name, desc, signature, exceptions, defaultClass);
        this.defaultClass.addMethod(method);
        return new OptimizerMethodVisitor(methodVisitor, method);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        this.defaultClass.addField(new Field(access, name, desc, signature, value));
        return super.visitField(access, name, desc, signature, value);
    }


}
