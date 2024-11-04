package com.example.hostlyand;

//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
//
//public class Payment extends AppCompatActivity {
//    EditText pay;
//    Button send;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_payment);
//        pay = findViewById(R.id.editTextTextPersonName4);
//        send = findViewById(R.id.button10);
//
//
//
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

public class Payment extends AppCompatActivity implements View.OnClickListener {
    EditText pay;
    Button Send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        pay = findViewById(R.id.editTextTextPersonName4);

        Send = findViewById(R.id.button10);
        Send.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {

        String pay1  =pay.getText().toString();

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String url = sh.getString("url", "") + "studentpayment";
        Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
        VolleyMultipartRequest volleyMultipartRequest;
        volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {


                            JSONObject obj = new JSONObject(new String(response.data));

                            if (obj.getString("status").equals("ok")) {
                                Toast.makeText(getApplicationContext(), " payment success", Toast.LENGTH_SHORT).show();
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
                params.put("sid", sh.getString("sid",""));//passing to python

                params.put("amount", pay1);//passing to python




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