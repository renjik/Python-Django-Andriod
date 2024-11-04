package com.example.hostlyand;

//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class WardenViewStudents extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_warden_view_students);
//    }
//}


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
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

public class WardenViewStudents extends AppCompatActivity {
    ListView li;
    SharedPreferences sh;
    EditText e1;
    String url, ip;
    String[] stdname, room, course, sem, rid,sid;
//    Button searchButton = findViewById(R.id.button9);



    public static  String bus_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warden_view_students);

        e1=(EditText)findViewById(R.id.editTextTextPersonName12);
//        e2=(EditText)findViewById(R.id.editTextTextPersonName13);

        e1.addTextChangedListener(textWatcher);
//        e2.addTextChangedListener(textWatcher);

        li = findViewById(R.id.li);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip = sh.getString("url", "");
        url = ip + "view_students";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                JSONArray js = jsonObj.getJSONArray("q");
                                rid = new String[js.length()];
                                stdname = new String[js.length()];
                                course = new String[js.length()];
                                sem = new String[js.length()];
                                sid = new String[js.length()];
                                room = new String[js.length()];


                                for (int i = 0; i < js.length(); i++) {
                                    JSONObject u = js.getJSONObject(i);
                                    rid[i] = u.getString("id");
                                    stdname[i] = u.getString("stdname");
                                    course[i] = u.getString("course");
                                    room[i] = u.getString("room");
                                    sem[i] = u.getString("sem");
                                    sid[i] = u.getString("sid");
                                }

                                // Set adapter for ListView
                                li.setAdapter(new Custom_wardentakeattendence(getApplicationContext(), rid, stdname, course, room, sem,sid));
                            } else {
                                Toast.makeText(getApplicationContext(), "Attendance already recorded for today", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("wid", sh.getString("wid", ""));
                params.put("sid", sh.getString("sid", ""));
                // Uncomment and pass bus_id if needed

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

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // No need to implement
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // No need to implement
        }

        @Override
        public void afterTextChanged(Editable editable) {
            makeVolleyRequest();
        }
    };



    private void makeVolleyRequest() {
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip = sh.getString("url", "");
        String url = ip + "search_students";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                JSONArray js = jsonObj.getJSONArray("q");
                                rid = new String[js.length()];
                                stdname = new String[js.length()];
                                course = new String[js.length()];
                                sem = new String[js.length()];
                                sid = new String[js.length()];
                                room = new String[js.length()];

                                for (int i = 0; i < js.length(); i++) {
                                    JSONObject u = js.getJSONObject(i);
                                    rid[i] = u.getString("id");
                                    stdname[i] = u.getString("stdname");
                                    course[i] = u.getString("course");
                                    room[i] = u.getString("room");
                                    sem[i] = u.getString("sem");
                                    sid[i] = u.getString("sid");  // Ensure sid is handled correctly
                                }

                                li.setAdapter(new Custom_wardentakeattendence(getApplicationContext(), rid, stdname, course, room, sem, sid));
                            } else {
                                Toast.makeText(getApplicationContext(), "Attendance already recorded for today", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
//                params.put("course", e1.getText().toString());  // Assuming course input is from e2
//                params.put("sem", e1.getText().toString());     // Assuming semester input is from e3
                params.put("course", e1.getText().toString());  // Assuming stdname input is from e1
                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS = 100000;
        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);
    }}
