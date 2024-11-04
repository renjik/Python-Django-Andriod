package com.example.hostlyand;

//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class StudentAddonTime extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_student_addon_time);
//    }
//}



import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StudentAddonTime extends AppCompatActivity implements View.OnClickListener {
    EditText time,resaon;
    Button send4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_addon_time);
        time = findViewById(R.id.editTextTime);
        resaon = findViewById(R.id.editTextTextPersonName15);
        send4 = findViewById(R.id.button12);
        send4.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        String message  =resaon.getText().toString();
        String time1  =time.getText().toString();
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String url = sh.getString("url", "") + "send_Addontime";
        Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
        VolleyMultipartRequest volleyMultipartRequest;
        volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {


                            JSONObject obj = new JSONObject(new String(response.data));

                            if (obj.getString("status").equals("ok")) {
                                Toast.makeText(getApplicationContext(), " success", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), Studenthome.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), " fail", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "----" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                SharedPreferences o = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                params.put("pid", sh.getString("pid",""));//passing to python
                params.put("reason", message);//passing to python
                params.put("time", time1);//passing to python



                return params;
            }
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
//                        params.put("image", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                return params;
            }
        };

        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }
}