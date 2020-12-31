package fr.umlv.learn;

import fr.umlv.dataStructure.DefaultClass;
import fr.umlv.dataStructure.Field;
import fr.umlv.dataStructure.Method;
import fr.umlv.dataStructure.instruction.Instruction;
import fr.umlv.database.Database;

/**
 * Class Analyser
 */
public class Analyser {
    /*public static void detectUnusedMethod(DefaultClass defaultClass) {
        Database database = new Database();
        for (Method method : defaultClass.getMethods()) {
            for (Instruction instruction : method.getInstructions()) {
                if (!instruction.toReadableInstruction().matches("INVOKE.*")) {
                    database.insertInMethodUnused("METHODUNUSED", defaultClass.getPackagePath() + defaultClass.getClassName(), method.getName(), method.getDesc());
                }
            }
        }
    }*/

    /**
     * Detect unused field from a class and stock them into table FIELDUNUSED
     *
     * @param defaultClass the default class
     */
    public static void detectUnusedField(DefaultClass defaultClass) {
        Database database = new Database();
        for (Field field : defaultClass.getFields()) {
            boolean used = false;
            for (Method method : defaultClass.getMethods()) {
                for (Instruction instruction : method.getInstructions()) {
                    if (instruction.toReadableInstruction().matches("GETFIELD " + defaultClass.getPackagePath() + defaultClass.getClassName() + "." + field.getName() + ".*")) {
                        used = true;
                        database.insertField(defaultClass.getPackagePath() + defaultClass.getClassName(), field.getName(), field.getDesc());
                    }
                }
            }
            if(!used) {
                database.insertInFieldUnused("FIELDUNUSED", defaultClass.getPackagePath() + defaultClass.getClassName(), field.getName(), field.getDesc());
            }
        }
    }
}
