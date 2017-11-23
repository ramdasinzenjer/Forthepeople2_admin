package com.inzenjer.forthepeople;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.inzenjer.forthepeople.adapters.Connectivity;
import com.inzenjer.forthepeople.adapters.Constants;
import com.inzenjer.forthepeople.adapters.RecyclerTouchListener;
import com.inzenjer.forthepeople.adapters.complaintAdapter;
import com.inzenjer.forthepeople.models.complaints;
import com.inzenjer.forthepeople.models.static_cmpaint;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ComplaintList extends AppCompatActivity {
    RecyclerView complaintView;
    private List<complaints> complaint_list = new ArrayList<>();
    String sh;
    complaintAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_list);
        complaintView = (RecyclerView) findViewById(R.id.complaint_recyclerView);

        mAdapter = new complaintAdapter(complaint_list);
        complaintView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        complaintView.setLayoutManager(mLayoutManager);
        complaintView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        complaintView.setItemAnimator(new DefaultItemAnimator());
        complaintView.setAdapter(mAdapter);
        final NewClass n = new NewClass();
        n.execute();
        complaintView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), complaintView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                complaints ml = complaint_list.get(position);
                static_cmpaint.discription= ml.getComplaint_des();
                static_cmpaint.name= ml.getName();
                static_cmpaint.name= ml.getName();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    public class NewClass extends AsyncTask<String, String, String> {
        String urlParameters = "";



        @Override
        protected String doInBackground(String... strings) {
            try{
                SharedPreferences share = getSharedPreferences("userdata", MODE_PRIVATE);
                share.getString("username", "");
                share.getString("password", "");
               String id =  share.getString("userid","");
                urlParameters =  "id=" + URLEncoder.encode(id, "UTF-8");
            }catch (Exception e)
            {

            }

            sh = Connectivity.excutePost(Constants.complaints,
                    urlParameters);
Log.e("hjfcg",sh);

            return sh;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            parsingmethod(s);
            mAdapter.notifyDataSetChanged();

        }


    }

    public void parsingmethod(String resp) {
        try {
            JSONObject object0 = new JSONObject(resp);
            JSONObject jobject1 = object0.getJSONObject("Event");
            JSONArray ja = jobject1.getJSONArray("Details");
            int length = ja.length();
            List<String> label = new ArrayList<String>();

            for (int i = 0; i < length; i++) {
                JSONObject data1 = ja.getJSONObject(i);
                final String[] id = {data1.getString("id")};
                String name = data1.getString("name");
                String title = data1.getString("title");
                String complaint_des = data1.getString("complaint_des");
                String log_id = data1.getString("log_id");
                String file = data1.getString("file");
                String status = data1.getString("Status");
                String email = data1.getString("email");
                String extra = data1.getString("extra");

                complaints ml = new complaints(title,log_id,status,"",complaint_des);



                complaint_list.add(ml);

            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("mException", "qqqqqq" + e);
        }
    }

}

