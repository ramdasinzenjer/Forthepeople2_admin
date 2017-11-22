package com.inzenjer.forthepeople;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.inzenjer.forthepeople.support_classes.constx;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    String usernameOnLogin, userIdOnLogin, passwordOnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();


    }

    public void Login(View v) {
        EditText edtUserName = (EditText) findViewById(R.id.login_username);
        EditText edtPasswrd = (EditText) findViewById(R.id.login_password);
        userIdOnLogin = edtUserName.getText().toString();
        passwordOnLogin = edtPasswrd.getText().toString();
        int count = 1;
        if (userIdOnLogin.equalsIgnoreCase("")) {
            count = 0;
            Toast.makeText(this, "User Id empty", Toast.LENGTH_SHORT).show();
        }
        if (passwordOnLogin.equalsIgnoreCase("")) {
            count = 0;
            Toast.makeText(this, "Password empty", Toast.LENGTH_SHORT).show();
        }
        if (count == 1) {
            SharedPreferences share = getSharedPreferences("userdata", MODE_PRIVATE);
            SharedPreferences.Editor edt = share.edit();
            edt.putString("username", usernameOnLogin);
            edt.putString("password", passwordOnLogin);
            edt.putString("userid", userIdOnLogin);
            edt.commit();
            logonTask();
        }


    }

    private void logonTask() {
        RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
        String resposnse;
        String User_url = constx.locahst + "/mla/admin/adminLogin.php";
        StringRequest postrequest = new StringRequest(Request.Method.POST, User_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if (userIdOnLogin.contains("Mla")) {
                        /*AlertDialog alertDialog = (AlertDialog)
                        alertDialog.setCustomTitle("Welcome");*/
                    }
                    JSONObject json = new JSONObject(response);
                    JSONObject mjson = json.getJSONObject("Event");
                    JSONArray mn = mjson.getJSONArray("Details");
                    if (mn.isNull(0)) {
                        Toast.makeText(Login.this, "sorry you are not yet authenticated or no data", Toast.LENGTH_SHORT).show();
                    } else {
                        JSONObject mnn = mn.getJSONObject(0);
                        String st = mnn.getString("name");
                        Toast.makeText(Login.this, st, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Login.this,Home.class);
                        startActivity(i);
                    }
                } catch (JSONException e) {
                    Toast.makeText(Login.this, e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", userIdOnLogin);
                params.put("password", passwordOnLogin);
                return params;
            }


        };
        postrequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postrequest);
    }

    public void Register(View v) {
        Intent i = new Intent(Login.this, Register.class);
        startActivity(i);
    }
}
