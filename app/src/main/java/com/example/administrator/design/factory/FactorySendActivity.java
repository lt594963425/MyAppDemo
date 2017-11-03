package com.example.administrator.design.factory;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.base.BaseActivity;

/**
 * 凡是出现了大量的产品需要创建，并且具有共同的接口时
 * $name
 * Created by ${LiuTao} on 2017/9/22/022.
 */

public class FactorySendActivity extends BaseActivity implements SendListener {
    private static final String TAG = "MainActivity";
    private TextInputEditText pwd;
    private TextInputEditText account;
    private TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        setTitle("工程设计模式");
        tvShow = (TextView) findViewById(R.id.tvShow);
        pwd = (TextInputEditText) findViewById(R.id.pwd);
        account = (TextInputEditText) findViewById(R.id.account);
    }



    public void sendFactory(View view) {
        //1，普通工厂模式，非静态的调用方式，需要创建实例
        SendFactory sendFactory = new SendFactory();
        //单工厂
        String userAccount = account.getText().toString().trim();
        sendFactory.product(userAccount, this);
        //2，多工厂模式,方法名置为静态方法 static ,直接调用,静态工程不需要创建实例,一般选用这种模式
        SendFactory.produceMail(this);
        SendFactory.produceSMS(this);
        //3，抽象类工厂模式，其实这个模式的好处就是，符合开闭原则（对扩展开放；对修改封闭）
        // 如果你现在想增加一个功能：发及时信息，则只需做一个实现类，实现Sender接口，
        // 同时做一个工厂类，实现Provider接口，就OK了，无需去改动现成的代码。这样做，拓展性较好！
        SendMailFactory sendMailFactory = new SendMailFactory();
        sendMailFactory.produce(this);
    }

    @Override
    public void onSendEvent(int status, Object... obj) {
        String str = (String) obj[0];
        switch (status) {
            case P.FINISH:
                tvShow.setText(str);
                break;
            case P.ERROR:
                tvShow.setText(str);
                break;
        }
    }
}
