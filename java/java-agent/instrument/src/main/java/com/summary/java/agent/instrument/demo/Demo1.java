package com.summary.java.agent.instrument.demo;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

import java.io.IOException;

public class Demo1 {
    public static void main(String[] args) throws NotFoundException, IOException, CannotCompileException {
        ClassPool cp = ClassPool.getDefault();
        CtClass ctClass = cp.makeClass("Javassist.Hello");
        ctClass.writeFile();
    }
}
