package fr.umlv;

import fr.umlv.learn.Analyser;
import fr.umlv.learn.visitor.OptimizerClassVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * The type Agent executed before the client JAR.
 * Visit the client code and insert DB code in bytecode.
 */
public class Agent {
    /**
     * Premain.
     * Transform only the client class and initiate a Class writer / Class reader / Class Visitor
     * And inspect unused field in class
     *
     * @param agentArgs the agent args
     * @param inst      the inst
     */
    @SuppressWarnings("unused")
    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader classLoader, String className, Class<?> aClass, ProtectionDomain protectionDomain, byte[] bytes) {
                if (protectionDomain != null && !protectionDomain.getPermissions().implies(new RuntimePermission("accessSystemModules"))) {
                    ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
                    OptimizerClassVisitor optimizerClassVisitor = new OptimizerClassVisitor(classWriter);
                    new ClassReader(bytes).accept(optimizerClassVisitor, 0);
                    /*Analyser.detectUnusedMethod(optimizerClassVisitor.getDefaultClass());*/
                    Analyser.detectUnusedField(optimizerClassVisitor.getDefaultClass());
                    return classWriter.toByteArray();
                }
                return bytes;
            }
        });
    }
}
