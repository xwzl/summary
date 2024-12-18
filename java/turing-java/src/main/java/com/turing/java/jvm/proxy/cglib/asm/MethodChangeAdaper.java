// package com.turing.java.jvm.proxy.cglib.asm;
//
// import jdk.internal.org.objectweb.asm.MethodVisitor;
// import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;
//
// public class MethodChangeAdaper extends AdviceAdapter {
//     /**
//      * Creates a new {@link AdviceAdapter}.
//      *
//      * @param api
//      *            the ASM API version implemented by this visitor. Must be one of {@link Opcodes#ASM4} or
//      *            {@link Opcodes#ASM5}.
//      * @param mv
//      *            the method visitor to which this adapter delegates calls.
//      * @param access
//      *            the method's access flags (see {@link Opcodes}).
//      * @param name
//      *            the method's name.
//      * @param desc
//      *            the method's descriptor (see {@link Type Type}).
//      */
//     protected MethodChangeAdaper(int api, MethodVisitor mv, int access, String name, String desc) {
//         super(api, mv, access, name, desc);
//     }
//
//     @Override
//     public void visitCode() {
//         super.visitCode();
//
//         // 调用静态方法
//         mv.visitMethodInsn(INVOKESTATIC, "com/tuling/cglib/proxy/DaoAnotherProxy", "before", "()V", false);
//
//         // 创建新的局部变量表索引
//         // 将int型2推送至操作数栈顶
//         mv.visitInsn(ICONST_2);
//         /// 创建新的局部变量表索引
//         int newLocal = super.newLocal(jdk.internal.org.objectweb.asm.Type.INT_TYPE);
//         mv.visitIntInsn(ISTORE, newLocal);
//         mv.visitIincInsn(newLocal, 1);
//         mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//         mv.visitIntInsn(ILOAD, newLocal);
//         mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
//     }
// }
