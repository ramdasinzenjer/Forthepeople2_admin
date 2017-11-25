package com.inzenjer.forthepeople;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.inzenjer.forthepeople.adapters.Connectivity;
import com.inzenjer.forthepeople.adapters.Constants;
import com.inzenjer.forthepeople.adapters.RecyclerTouchListener;
import com.inzenjer.forthepeople.adapters.suggetionAdapter;
import com.inzenjer.forthepeople.models.suggetions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SUDHEESH on 11/25/2017.
 */

public class Suggetion extends AppCompatActivity {

    RecyclerView suggetionView;
    private List<suggetions> suggetion_list = new ArrayList<>();
    String sh;
    suggetionAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        suggetionView = (RecyclerView) findViewById(R.id.suggetion_recyclerView);

        mAdapter = new suggetionAdapter(suggetion_list);
        suggetionView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        suggetionView.setLayoutManager(mLayoutManager);
        suggetionView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        suggetionView.setItemAnimator(new DefaultItemAnimator());
        suggetionView.setAdapter(mAdapter);
        final Suggetion.NewClass n = new Suggetion.NewClass();
        n.execute();
        suggetionView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), suggetionView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

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
            try {
                SharedPreferences share = getSharedPreferences("userdata", MODE_PRIVATE);
                share.getString("username", "");
                share.getString("password", "");
                String id = share.getString("userid", "");
                urlParameters = "id=" + URLEncoder.encode(id, "UTF-8");
            } catch (Exception e) {

            }

            sh = Connectivity.excutePost(Constants.suggetion,
                    urlParameters);

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

            for (int i = 0; i < length; i++) {
                JSONObject data1 = ja.getJSONObject(i);
                final String[] id = {data1.getString("id")};
                String name = data1.getString("name");
                String title = data1.getString("title");
                String suggetion_des = data1.getString("complaint_des");
                String log_id = data1.getString("Log_id");
                String status = data1.getString("Status");

                suggetions ml = new suggetions(title,log_id,status,name,suggetion_des);

                suggetion_list.add(ml);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
