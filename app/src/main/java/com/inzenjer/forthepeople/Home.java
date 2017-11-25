package com.inzenjer.forthepeople;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Home extends AppCompatActivity {
TextView complaintListHome,Suggetions ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        complaintListHome = findViewById(R.id.complaintlist_home);
        complaintListHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,ComplaintList.class);
                startActivity(i);
            }
        });

        Suggetions=findViewById(R.id.sugget);
        Suggetions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home.this,Suggetion.class);
                startActivity(i);
            }
        });

    }
}
