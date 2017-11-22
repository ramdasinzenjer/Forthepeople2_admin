package com.inzenjer.forthepeople;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Registration_suc extends AppCompatActivity {
    String name, password, id;
    TextView uname, upassword, uid, welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_suc);


        SharedPreferences sh = getSharedPreferences("id", MODE_PRIVATE);
        name = sh.getString("name", "");
        password = sh.getString("password", "");
        id = sh.getString("id", "");
        welcome = (TextView) findViewById(R.id.regsuc_very);
        uname = (TextView) findViewById(R.id.regsuc_username);
        uid = (TextView) findViewById(R.id.regsuc_id);
        upassword = (TextView) findViewById(R.id.regsuc_pass);
        welcome.setText(welcome.getText() + " " + name);
        uname.setText(uname.getText() + " " + name);
        upassword.setText(upassword.getText() + " " + password);
        uid.setText("user id = " + id);

    }
}