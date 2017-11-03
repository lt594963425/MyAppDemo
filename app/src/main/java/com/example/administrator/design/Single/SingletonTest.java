package com.example.administrator.design.Single;

/**
 * 单例设计模式 1,
 * $name 将创建和getInstance()分开,单独为创建加synchronized关键字
 * Created by ${LiuTao} on 2017/9/26/026.
 */

public class SingletonTest {
    private static volatile SingletonTest instance = null;

    private SingletonTest() {
    }

    public static SingletonTest getInstance() {
        if (instance == null) {
            systic();
        }
        return instance;
    }

    public static synchronized void systic() {
        if (instance == null)
            instance = new SingletonTest();
    }
}
