/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.jexl3;


import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * Helper class to test GC / reference interactions.
 * Dynamically creates a class by compiling generated source Java code and
 * load it through a dedicated class loader.
 */
public class ClassCreator {
    private final JexlEngine jexl;
    private final File base;
    private File packageDir = null;
    private int seed = 0;
    private String className = null;
    private String sourceName = null;
    private ClassLoader loader = null;
    public static final boolean canRun = true;//comSunToolsJavacMain();

    static final String GEN_PATH = "/org/apache/commons/jexl3/generated";
    static final String GEN_PACKAGE = "org.apache.commons.jexl3.generated";
    static final String GEN_CLASS = GEN_PACKAGE + ".";
    /**
     * Check if we can invoke Sun's java compiler.
     * @return true if it is possible, false otherwise
     */
    private static boolean comSunToolsJavacMain() {
        try {
            Class<?> javac = ClassCreatorTest.class.getClassLoader().loadClass("com.sun.tools.javac.Main");
            return javac != null;
        } catch (Exception xany) {
            return false;
        }
    }

    public ClassCreator(JexlEngine theJexl, File theBase) throws Exception {
        jexl = theJexl;
        base = theBase;
    }


    public void clear() {
        seed = 0;
        packageDir = null;
        className = null;
        sourceName = null;
        packageDir = null;
        loader = null;
    }

    public void setSeed(int s) {
        seed = s;
        className = "foo" + s;
        sourceName = className + ".java";
        packageDir = new File(base, seed + GEN_PATH);
        packageDir.mkdirs();
        loader = null;
    }

    public String getClassName() {
        return GEN_CLASS + className;
    }

    public Class<?> getClassInstance() throws Exception {
        return getClassLoader().loadClass(getClassName());
    }

    public ClassLoader getClassLoader() throws Exception {
        if (loader == null) {
            URL classpath = (new File(base, Integer.toString(seed))).toURI().toURL();
            loader = new URLClassLoader(new URL[]{classpath}, null);
        }
        return loader;
    }

    public Class<?> createClass() throws Exception {
        // generate, compile & validate
        generate();
        Class<?> clazz = compile();
        if (clazz == null) {
            throw new Exception("failed to compile foo" + seed);
        }
        Object v = validate(clazz);
        if (v instanceof Integer && ((Integer) v).intValue() == seed) {
            return clazz;
        }
        throw new Exception("failed to validate foo" + seed);
    }

    void generate() throws Exception {
        FileWriter aWriter = new FileWriter(new File(packageDir, sourceName), false);
        aWriter.write("package ");
        aWriter.write(GEN_PACKAGE);
        aWriter.write(";\n");
        aWriter.write("public class " + className + "{\n");
        aWriter.write("private int value =");
        aWriter.write(Integer.toString(seed));
        aWriter.write(";\n");
        aWriter.write(" public void setValue(int v) {");
        aWriter.write(" value = v;");
        aWriter.write(" }\n");
        aWriter.write(" public int getValue() {");
        aWriter.write(" return value;");
        aWriter.write(" }\n");
        aWriter.write(" }\n");
        aWriter.flush();
        aWriter.close();
    }

    Class<?> compile0() throws Exception {
        String source = packageDir.getPath() + "/" + sourceName;
        Class<?> javac = getClassLoader().loadClass("com.sun.tools.javac.Main");
        if (javac == null) {
            return null;
        }
        Integer r;
        try {
            r = (Integer) jexl.invokeMethod(javac, "compile", source);
            if (r.intValue() >= 0) {
                return getClassLoader().loadClass(GEN_CLASS + className);
            }
        } catch (JexlException xignore) {
            // ignore
        }
        r = (Integer) jexl.invokeMethod(javac, "compile", (Object) new String[]{source});
        if (r.intValue() >= 0) {
            return getClassLoader().loadClass(GEN_CLASS + className);
        }
        return null;
    }

    Class<?> compile() throws Exception {
        String source = packageDir.getPath() + "/" + sourceName;
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        Iterable<? extends JavaFileObject> compilationUnits = fileManager
                .getJavaFileObjectsFromStrings(Arrays.asList(source));
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null,
                null, compilationUnits);
        boolean success = task.call();
        fileManager.close();
        if (success) {
            return getClassLoader().loadClass(GEN_CLASS +  className);
        } else {
            return null;
        }
    }

    Object validate(Class<?> clazz) throws Exception {
        Class<?> params[] = {};
        Object paramsObj[] = {};
        Object iClass = clazz.newInstance();
        Method thisMethod = clazz.getDeclaredMethod("getValue", params);
        return thisMethod.invoke(iClass, paramsObj);
    }
}
