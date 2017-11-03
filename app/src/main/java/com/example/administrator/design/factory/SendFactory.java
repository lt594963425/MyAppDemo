package com.example.administrator.design.factory;

/**
 *  工厂设计模式
 * Created by ${LiuTao} on 2017/9/22/022.
 */

public class SendFactory {
    /**
     * 单工厂创建模式
     */
    public  void product(String type, SendListener listener) {
        switch (type) {
            case "mail":
                produceMail(listener);
              //  new MailSender().send(listener);
                break;
            case "sms":
                produceSMS(listener);
                //new SmsSender().send(listener);
                break;
            default:
                listener.onSendEvent(P.ERROR, "请输入正确的类型");

                break;
        }
    }

    /**
     * 多工厂设计模式
     * @param listener
     */
    public static void produceMail(SendListener listener) {
        new MailSender().send(listener);
    }

    public static void produceSMS(SendListener listener) {
        new SmsSender().send(listener);
    }
}
