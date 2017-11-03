package com.example.administrator.design.Proxy;

/**
 * 实每个模式名称就表明了该模式的作用，代理模式就是多一个代理类出来，替原对象进行一些操作
 * (感觉和装饰模式很像)
 * Created by ${LiuTao} on 2017/9/26/026.
 */

public class Proxy implements Sourceable {
    private Source source;
    public Proxy(){
        super();
        this.source = new Source();
    }
    @Override
    public void method() {
        before();
        source.method();
        atfer();
    }
    private void atfer() {
        System.out.println("after proxy!");
    }
    private void before() {
        System.out.println("before proxy!");
    }
}
