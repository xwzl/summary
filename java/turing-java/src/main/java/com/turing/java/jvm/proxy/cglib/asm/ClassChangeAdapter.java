package com.turing.java.jvm.proxy.cglib.asm;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;

import static jdk.internal.org.objectweb.asm.Opcodes.ASM4;

public class ClassChangeAdapter extends ClassVisitor {

    public ClassChangeAdapter(ClassVisitor classVisitor) {
        super(ASM4, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        // 如果当前访问到main方法就移除吊
        if (name.equals("main")) {
            return null;
        }
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        MethodChangeAdaper methodChangeAdaper = new MethodChangeAdaper(ASM4, mv, access, name, desc);
        return methodChangeAdaper;
    }

}
