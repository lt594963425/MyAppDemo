package com.example.administrator.design.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * 建造者模式
 * $name
 * Created by ${LiuTao} on 2017/9/26/026.
 */

public class Builder {
    List<Sender> list = new ArrayList<Sender>();

    public void produceMailSender(int count) {
        for (int i = 0; i < count; i++) {
            list.add(new MailSender());
        }
    }

    public void produceSmsSender(int count) {
        for (int i = 0; i < count; i++) {
            list.add(new SmsSender());

        }
    }
}
