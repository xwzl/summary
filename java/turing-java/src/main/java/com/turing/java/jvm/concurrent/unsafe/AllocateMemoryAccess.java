package com.turing.java.jvm.concurrent.unsafe;

import com.java.tool.concurrent.UnsafeInstance;
import sun.misc.Unsafe;

public class AllocateMemoryAccess {

    public static void main(String[] args) {
        Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();
        long oneHundred = 1193123491341341234L;
        byte size = 8;
        /*
         * 调用allocateMemory分配内存
         */
        long memoryAddress = unsafe.allocateMemory(size);
        System.out.println("address:->"+memoryAddress);
        /*
         * 将1写入到内存中
         */
        unsafe.putAddress(memoryAddress, oneHundred);
        /*
         * 内存中读取数据
         */
        long readValue = unsafe.getAddress(memoryAddress);

        System.out.println("value : " + readValue);

        unsafe.freeMemory(memoryAddress);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
