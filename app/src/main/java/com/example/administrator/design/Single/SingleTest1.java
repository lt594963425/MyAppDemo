package com.example.administrator.design.Single;

/**
 * 单例设计模式 内部类 来维护单例
 * JVM内部的机制能够保证当一个类被加载的时候，这个类的加载过程是线程互斥的
 * Created by ${LiuTao} on 2017/9/26/026.
 */

public class SingleTest1 {
    private SingleTest1() {
    }
    //静态工厂
    public static class singleFactory {
        private static SingleTest1 intance = new SingleTest1();
    }
    public  static  SingleTest1 getInstance (){
        return singleFactory.intance;

    }


    /* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
    public Object readResolve() {
        return getInstance();
    }
}
