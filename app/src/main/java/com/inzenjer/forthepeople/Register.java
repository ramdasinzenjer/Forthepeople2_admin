package com.inzenjer.forthepeople;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.inzenjer.forthepeople.support_classes.constx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.inzenjer.forthepeople.support_classes.idgen.idgenerator;

public class Register extends AppCompatActivity {
    Spinner spinner2;
    List<String> list = new ArrayList<String>();
    EditText username, mobile, password, id, constituency, portfolio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register");

        username = (EditText) findViewById(R.id.reg_username);
        mobile = (EditText) findViewById(R.id.reg_mobile);
        password = (EditText) findViewById(R.id.reg_password);
        id = (EditText) findViewById(R.id.id_reg);
        constituency = (EditText) findViewById(R.id.constituency_reg);
        portfolio = (EditText) findViewById(R.id.port_reg);

        spinner2 = (Spinner) findViewById(R.id.category_selector_spin);
        list.add("");
        list.add("Ministers");
        list.add("Mps");
        list.add("Mlas");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getSelectedItem().toString();
                SharedPreferences sh = getSharedPreferences("abc", MODE_PRIVATE);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("st", item);
                ed.commit();
                if (item.equalsIgnoreCase("Ministers")) {
                    mins();
                }
                if (item.equalsIgnoreCase("Mps")) {
                    mp();
                }
                if (item.equalsIgnoreCase("Mlas")) {
                    mla();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void mins() {
        id.setVisibility(View.VISIBLE);
        constituency.setVisibility(View.VISIBLE);
        portfolio.setVisibility(View.VISIBLE);
        id.setHint("Enter ministral id");
        portfolio.setHint("Enter ministral Portfolio");
        constituency.setHint("Enter ministral constituency");
    }

    public void mla() {
        portfolio.setVisibility(View.GONE);
        id.setVisibility(View.VISIBLE);
        constituency.setVisibility(View.VISIBLE);
        id.setHint("Enter Mla id");
        constituency.setHint("Enter Mla constituency");
    }

    public void mp() {
        portfolio.setVisibility(View.GONE);
        id.setVisibility(View.VISIBLE);
        constituency.setVisibility(View.VISIBLE);
        id.setHint("Enter MP id");
        constituency.setHint("Enter Mp constituency");
    }

    public void reg(View v) {
        final String name = username.getText().toString();
        final String sconstituency = constituency.getText().toString();

        final String sportfolio = portfolio.getText().toString();
        final String spassword = password.getText().toString();
        final String sidd = id.getText().toString();


        SharedPreferences sh = getSharedPreferences("abc", MODE_PRIVATE);
        String mn = sh.getString("st", "");

        String ssd = mseectr(mn);
        final String rid = idgenerator(ssd);
        RequestQueue queue = Volley.newRequestQueue(Register.this);
        String response = "";
        final String finalResponse = response;
        //TODO url
        String S_URL = urseectr();
        Toast.makeText(this, S_URL, Toast.LENGTH_SHORT).show();
        StringRequest postRequest = new StringRequest(Request.Method.POST, S_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Toast.makeText(Register.this, response, Toast.LENGTH_SHORT).show();
                            if(response.equalsIgnoreCase("Data Submit Successfully"))
                            {
                                SharedPreferences sha = getSharedPreferences("id",MODE_PRIVATE);
                                SharedPreferences.Editor ed = sha.edit();
                                ed.putString("name",name);
                                ed.putString("password",spassword);
                                ed.putString("id",rid);
                                ed.commit();
                                Intent i = new Intent(Register.this , Registration_suc.class);
                                startActivity(i);

                            }


                        } catch (Exception e) {

                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.e("ErrorResponse", finalResponse);
                        Toast.makeText(Register.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();


                params.put("name", name);
                params.put("password", spassword);
                params.put("constituency", sconstituency);
                params.put("portfolio", sportfolio);
                params.put("id", rid);
                params.put("sid", sidd);


                return params;
            }

        };

        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);

        Toast.makeText(this, "dcsh,vdhbb", Toast.LENGTH_SHORT).show();
    }

    private String urseectr() {
        SharedPreferences sh = getSharedPreferences("abc", MODE_PRIVATE);
        String n = sh.getString("st", "");
        if (n.contains("Minister")) {
            return constx.locahst + "/mla/admin/registrationmin.php";
        }
        if (n.contains("Mps")) {
            return constx.locahst + "/mla/admin/registrationmp.php";
        }
        if (n.contains("Mla")) {
            return constx.locahst + "/mla/admin/registrationmla.php";
        }
        return null;
    }

    public String mseectr(String n) {
        if (n.contains("Minister")) {
            return "Mn";
        }
        if (n.contains("Mps")) {
            return "Mp";
        }
        if (n.contains("Mla")) {
            return "Mla";
        }
        return "";
    }
}