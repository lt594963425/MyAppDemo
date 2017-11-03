package com.example.administrator.design.factory;



/**
 * $name
 * Created by ${LiuTao} on 2017/9/22/022.
 */

public class MailSender implements Sender {
    @Override
    public void send(SendListener listener) {
        listener.onSendEvent(P.FINISH,"发送邮件");
    }
}
