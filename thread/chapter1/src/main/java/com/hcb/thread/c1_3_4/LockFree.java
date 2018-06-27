package com.hcb.thread.c1_3_4;

import jdk.internal.org.objectweb.asm.commons.StaticInitMerger;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ChengBing Han
 * @date 9:27  2018/6/16
 * @description
 */
public class LockFree {

    private static AtomicInteger atomicInteger = new AtomicInteger(3);

    private static int localVar = 9;
    private static int index  = 0;

    public static void main(String[] args) {

        new Thread();
        while (!atomicInteger.compareAndSet(localVar, localVar + 1)) {
            localVar = atomicInteger.get();
            System.out.println("第 " + (++index) + " 循环" + atomicInteger);
        }
        System.out.println(atomicInteger);

    }
}
