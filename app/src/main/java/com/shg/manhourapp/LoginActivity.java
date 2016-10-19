package com.shg.manhourapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView Login_TV;
    private EditText UserName_ET;
    private EditText PassWord_ET;
    private CheckBox Auto_CB;
    private CheckBox RemB_CB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Login_TV = (TextView) findViewById(R.id.tv_login_in);

        Login_TV.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tv_login_in:
                Intent intent = new Intent();
                intent.setClass(this, DispatchListActivity.class);
                startActivity(intent);
                this.finish();

                break;
        }

    }
}
