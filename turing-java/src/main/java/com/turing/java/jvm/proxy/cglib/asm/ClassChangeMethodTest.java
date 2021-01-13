package com.turing.java.jvm.proxy.cglib.asm;

import com.turing.java.jvm.proxy.TestQuestion;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;

import static jdk.internal.org.objectweb.asm.ClassWriter.COMPUTE_MAXS;

public class ClassChangeMethodTest {
    public static void main(String[] args) throws Exception {
        ClassWriter cw = new ClassWriter(COMPUTE_MAXS);
        ClassReader cr = new ClassReader(TestQuestion.class.getName());

        ClassChangeAdapter adapter = new ClassChangeAdapter(cw);

        cr.accept(adapter, ClassReader.SKIP_FRAMES);

        byte[] bytes = cw.toByteArray();

        File file = new File("./TestQuestion.class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(bytes);
        fout.close();

        MyClassLoader classLoader = new MyClassLoader();
        Class loadedClass = classLoader.defineClass(TestQuestion.class.getName(), bytes);

        loadedClass.newInstance();
    }
}
