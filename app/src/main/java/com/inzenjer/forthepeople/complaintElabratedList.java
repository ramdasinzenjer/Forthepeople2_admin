package com.inzenjer.forthepeople;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.inzenjer.forthepeople.adapters.Constants;
import com.inzenjer.forthepeople.models.static_cmpaint;

import java.util.HashMap;
import java.util.Map;

import static com.inzenjer.forthepeople.adapters.Constants.change_status;
import static com.inzenjer.forthepeople.models.static_cmpaint.discription;
import static com.inzenjer.forthepeople.models.static_cmpaint.file;
import static com.inzenjer.forthepeople.models.static_cmpaint.tittle;

public class complaintElabratedList extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ProgressDialog mDailog;
    String[] spliner_names = {"view", "forwarded to authority", "completed"};
    TextView cEL_tittle, cEl_details, cEl_file1_name, cEl_file2_name;
    Button cEl_file1_download_button, cEl_file2_download_buton, cEl_updateStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_elabrated_list);
        getSupportActionBar().hide();
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spliner_names);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        spin.setOnItemSelectedListener(this);
        cEL_tittle = (TextView) findViewById(R.id.complaint_Tittle);
        cEl_details = (TextView) findViewById(R.id.details_details);
        cEl_file1_name = (TextView) findViewById(R.id.file1txt);
        cEl_file2_name = (TextView) findViewById(R.id.file2txt);
        cEl_file1_download_button = (Button) findViewById(R.id.file1download);
        cEl_file2_download_buton = (Button) findViewById(R.id.file2download);
        cEl_updateStatus = (Button) findViewById(R.id.update_Status);

        cEL_tittle.setText(tittle);
        cEl_details.setText(discription);
        if (file.isEmpty()) {
            cEL_tittle.setVisibility(View.INVISIBLE);

        } else {
            cEl_file1_name.setText(file);
        }
        cEl_file1_download_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = Constants.download+file;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //Displaying progress dailog
        final ProgressDialog mDailog = new ProgressDialog(complaintElabratedList.this);
        mDailog.setMessage("updating");
        mDailog.show();


        RequestQueue queue = Volley.newRequestQueue(complaintElabratedList.this);
        final String status = spliner_names[i];
        final String Response;
        //TODO url
        StringRequest postRequest = new StringRequest(Request.Method.POST, change_status,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mDailog.dismiss();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mDailog.dismiss();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", static_cmpaint.id);
                params.put("status", status);


                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
