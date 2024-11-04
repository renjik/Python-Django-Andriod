package com.example.hostlyand;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class WardenviewAddontime extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_wardenview_addontime);
//    }
//}


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WardenviewAddontime extends AppCompatActivity {
    ListView li;
    SharedPreferences sh;
    String url, ip;


    String[]  rid, addontime, reason,statusss,stdname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wardenview_addontime);

        li = (ListView)findViewById(R.id.li);



        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip = sh.getString("url", "");
        url = ip + "viewalladdontimes";
//        Toast.makeText(getApplicationContext(),sh.getString("pid",""),Toast.LENGTH_SHORT).show();




        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObj = new JSONObject(response);
                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                        JSONArray js = jsonObj.getJSONArray("q");//from python
                        Toast.makeText(getApplicationContext(), "Not found"+js, Toast.LENGTH_LONG).show();

                        rid = new String[js.length()];
                        addontime = new String[js.length()];
                        reason = new String[js.length()];
                        statusss = new String[js.length()];
                        stdname = new String[js.length()];
                        Toast.makeText(getApplicationContext(), "-------------------------"+addontime, Toast.LENGTH_LONG).show();

                        for (int i = 0; i < js.length(); i++) {
                            JSONObject u = js.getJSONObject(i);
                            rid[i] = u.getString("id");//dbcolumn name in double quotes
                            addontime[i] = u.getString("time");
                            reason[i] = u.getString("reason");
                            statusss[i] = u.getString("statuss");
                            stdname[i] = u.getString("stdname");
                            statusss[i] = u.getString("statuss");


                        }
                        li.setAdapter(new CustomwardenviewAddontime(getApplicationContext(),rid,addontime,reason,statusss,stdname));

                    } else {
                        Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        },


                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            // value Passing android to python
            @Override
            protected Map<String, String> getParams() {
                //   SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String,String>params=new HashMap<String,String>();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                params.put("wid", sh.getString("wid",""));//passing to python

                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS = 100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);







    }
}
