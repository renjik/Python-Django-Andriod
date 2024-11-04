package com.example.hostlyand;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StudentViewAllocatedroom extends AppCompatActivity implements View.OnClickListener {
    TextView room, des, roomids;
    Button maintenance;
    String roomid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_allocatedroom);

        room = findViewById(R.id.textView10);
        des = findViewById(R.id.textView15);
        maintenance = findViewById(R.id.button6);
        maintenance.setOnClickListener(this);

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String url = sh.getString("url", "") + "student_view_allocated_room";

        // Show the URL in Toast for testing
        Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();

        // Create the Volley request
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            String jsonString = new String(response.data);
                            JSONObject obj = new JSONObject(jsonString);

                            if (obj.getString("status").equals("ok")) {

                                    room.setText(obj.getString("Room"));
                                    des.setText(obj.getString("des"));
                                    roomid = obj.getString("ra_id");




                            } else {
                                maintenance.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplicationContext(), "No Room allocated", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getApplicationContext(), "Request failed", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("sid", sh.getString("sid", ""));
                params.put("roomid", sh.getString("roomid", ""));
                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                return new HashMap<>();  // No file data to send
            }
        };

        // Add the request to the Volley queue
        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }


    @Override
    public void onClick(View v) {
        if (v == maintenance) {
            // Fetch the room ID from the button tag
            String roomidFromTag = roomid;

            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor ed = sh.edit();
            ed.putString("roomid",roomidFromTag);

            ed.commit();
            Intent i = new Intent(getApplicationContext(),Maintenance_request_send.class);
            startActivity(i);
        }
    }
}