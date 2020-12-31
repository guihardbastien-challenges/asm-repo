package fr.umlv.supaoptimizer.api;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {

    public static enum Mode {
        LEARN,
        REWRITE
    }

    public static final Path pathToClass = Paths.get("out/AsmHelloWorld.class");
    public static final boolean DEBUG = true;
    public static final Mode CURRENT_MODE = Mode.REWRITE;
}
