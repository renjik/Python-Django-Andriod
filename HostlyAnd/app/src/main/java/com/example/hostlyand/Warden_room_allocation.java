package com.example.hostlyand;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Warden_room_allocation extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Spinner rooms, students;
    String[] rid, room_no, sid, student_name;
    Button btn;
    String roomid = "", studentid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warden_room_allocation);

        rooms = findViewById(R.id.spinner1);
        students = findViewById(R.id.spinner2);
        btn = findViewById(R.id.button5);
        rooms.setOnItemSelectedListener(this);
        students.setOnItemSelectedListener(this);
        btn.setOnClickListener(this);

        fetchRoomData();
        fetchStudentData();
    }

    private void fetchRoomData() {
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String url = sh.getString("url", "") + "view_rooms";
        Log.d("URL", "Fetching from: " + url);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                JSONArray js = jsonObj.getJSONArray("q");
                                rid = new String[js.length()];
                                room_no = new String[js.length()];

                                for (int i = 0; i < js.length(); i++) {
                                    JSONObject u = js.getJSONObject(i);
                                    rid[i] = u.getString("id");
                                    room_no[i] = u.getString("room");
                                }

                                ArrayAdapter<String> adapter = new ArrayAdapter<>(Warden_room_allocation.this, android.R.layout.simple_spinner_item, room_no);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                rooms.setAdapter(adapter);
                            }
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Error parsing room data", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Failed to fetch room data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(postRequest);
    }

    private void fetchStudentData() {
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String url = sh.getString("url", "") + "wardern_view_students";
        Log.d("URL", "Fetching from: " + url);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                JSONArray js = jsonObj.getJSONArray("q");

                                sid = new String[js.length()];
                                student_name = new String[js.length()];

                                for (int i = 0; i < js.length(); i++) {
                                    JSONObject u = js.getJSONObject(i);
                                    sid[i] = u.getString("id");
                                    student_name[i] = u.getString("student_name");
                                }

                                ArrayAdapter<String> adapter = new ArrayAdapter<>(Warden_room_allocation.this, android.R.layout.simple_spinner_item, student_name);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                students.setAdapter(adapter);
                            }
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Error parsing student data", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Failed to fetch student data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(postRequest);
    }

    @Override
    public void onClick(View v) {
        if (v == btn) {
            handleWardenRoomAllocation();
        }
    }

    private void handleWardenRoomAllocation() {
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String url = sh.getString("url", "") + "warden_allocate_room";
        Log.d("URL", "Posting to: " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            String status = obj.getString("status");
                            if (status.equals("ok")) {
                                Toast.makeText(getApplicationContext(), "Room Allocated Successfully", Toast.LENGTH_SHORT).show();
                                SharedPreferences.Editor ed = sh.edit();
                                ed.putString("wid", obj.getString("wid"));
                                ed.apply();
                                navigateToWardenHome();
                            } else {
                                Toast.makeText(getApplicationContext(), "Room allocation failed: " + status, Toast.LENGTH_SHORT).show();
                                navigateToWardenHome();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Failed to allocate room: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("rid", roomid);
                params.put("sid", studentid);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void navigateToWardenHome() {
        startActivity(new Intent(getApplicationContext(), Wardenhome.class));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent == rooms) {
            roomid = rid[position];
        } else if (parent == students) {
            studentid = sid[position];
            Log.d("Student Selected", "Position: " + position + " - Student ID: " + studentid);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // No action needed here
    }
}
