# BOYET-GUIHARD
## Description
An attempt to optimize code using the ASM lib.
## Walk through
- Main: The idea was to have a main class that would have a different behaviour given the config class data
- DataBase: Contains all database related methods
- DebugUtils: 
    - This class contains a static method for generating a test classfile for degugging/training purpose.
    - Also, this class contains a ststic method for printing bytecode so we could witness changes
- RewriteBytecode: This class is dedicated to rewriting bytecode
- SupaOptimizer: TBA
## Overview
### Directory structure

```bash

.
├── README.md
├── api
│   ├── api.iml
│   ├── pom.xml
│   └── src
│       ├── main
│       │   └── java
│       │       └── fr
│       │           └── umlv
│       │               └── supaoptimizer
│       │                   └── api
│       │                       ├── Config.java                 -> Project config
│       │                       ├── DataBase.java               -> Database communications
│       │                       ├── DebugUtils.java             -> Static methods used for debugging
│       │                       ├── Learn.java                  -> Use to learn what could be improve
│       │                       ├── Optimize.java               -> Optimizing the class file
│       │                       ├── Main.java                   -> Main
│       │                       ├── RewriteBytecode.java        -> Class used for bytecode manipulation
│       │                       └── SupaOptimizer.java          -> TBA
│       └── test
│           └── java
│               └── fr
│                   └── umlv
│                       └── supaoptimizer
│                           └── api
│                               ├── ConstantLoopTests.java
│                               ├── ConstantTests.java
│                               ├── DatabaseTest.java
│                               ├── DispatchTests.java
│                               └── LazyTests.java
├── boyet-guihard.iml
├── out
│   └── AsmHelloWorld.class
└── pom.xml
```
## How to use ?
- create an `out` directory 
```bash
mkdir ./out
```
- Run the main method of the `Main` class
## Ressources
- `Subject`: http://monge.univ-mlv.fr/ens/IR/IR2/2020-2021/JavaAvance/project.php
- `Getting started article`: https://medium.com/@supun.setunga/introduction-to-java-bytecode-manipulation-with-asm-9ae71049c7e0
- `Bytecode crash course`: https://www.youtube.com/watch?v=e2zmmkc5xI0&t=995s
- `asm4 doc`: https://asm.ow2.io/asm4-guide.pdf
- `gentle introduction to ASM`: https://www.jrebel.com/blog/java-bytecode-tutorial#Part%20II:%20Getting%20Started%20with%20ASM
- https://www.codeflow.site/fr/article/java-asm
- `java.lang.instrument`: https://www.waitingforcode.com/java-instrumentation/java-instrumentation-example/read
- `instrumentation introduction`: https://www.codeflow.site/fr/article/java-instrumentation
- `paper on superoptimizers`: https://web.stanford.edu/class/cs343/resources/superoptimizer.pdf

