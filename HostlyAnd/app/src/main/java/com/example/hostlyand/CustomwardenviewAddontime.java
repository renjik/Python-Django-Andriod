package com.example.hostlyand;

//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class CustomwardenviewAddontime extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.customwardenview_addontime);
//    }
//}



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
//import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CustomwardenviewAddontime extends BaseAdapter {
    String[] rid, addontime, reason,status,stdname;
    private Context context;
    Button bt,bt1;

    public CustomwardenviewAddontime(Context applicationContext, String[] rid, String[] addontime, String[] reason, String[] status, String[] stdname) {
        this.context = applicationContext;
        this.rid = rid;
        this.addontime = addontime;
        this.reason = reason;
        this.status = status;
        this.stdname = stdname;

    }

    @Override
    public int getCount() {
        return addontime.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (view == null) {
            gridView = new View(context);
            gridView = inflator.inflate(R.layout.customwardenview_addontime, null);
        } else {
            gridView = (View) view;
        }

        TextView tv1 = (TextView) gridView.findViewById(R.id.textView44);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView46);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView48);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView50);

        // Find the button and assign it to bt
        bt = (Button) gridView.findViewById(R.id.button13); // Or use the correct button ID
        bt1 = (Button) gridView.findViewById(R.id.button14); // Or use the correct button ID
        bt.setTag(rid[i]);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());

                String url = sh.getString("url", "") + "approve_status";
                RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                // response
                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                        Toast.makeText(context.getApplicationContext(), "Accepted", Toast.LENGTH_SHORT).show();


                                        Intent i = new Intent(context.getApplicationContext(), WardenviewAddontime.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(i);


                                    }


                                    // }
                                    else {
                                        Toast.makeText(context.getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                                    }

                                } catch (Exception e) {
                                    Toast.makeText(context.getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(context.getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {


                    @Override
                    protected Map<String, String> getParams() {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("rid",view.getTag().toString());

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


        });
        bt1.setTag(rid[i]);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());

                String url = sh.getString("url", "") + "reject_status";
                RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                // response
                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                        Toast.makeText(context.getApplicationContext(), "Rejected", Toast.LENGTH_SHORT).show();


                                        Intent i = new Intent(context.getApplicationContext(), WardenviewAddontime.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(i);


                                    }


                                    // }
                                    else {
                                        Toast.makeText(context.getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                                    }

                                } catch (Exception e) {
                                    Toast.makeText(context.getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(context.getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {


                    @Override
                    protected Map<String, String> getParams() {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("rid",view.getTag().toString());

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


        });



        // Set text and color properties
        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);

        tv1.setText(addontime[i]);
        tv2.setText(reason[i]);
        tv3.setText(status[i]);
        tv4.setText(stdname[i]);

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);

        return gridView;
    }

    private void handleButtonClick(String sid) {

        Intent intent = new Intent(context, Wardenhome.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}