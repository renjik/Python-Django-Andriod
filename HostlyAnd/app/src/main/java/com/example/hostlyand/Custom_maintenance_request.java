package com.example.hostlyand;

//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class Customnotification extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_customnotification);
//    }
//}


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class Custom_maintenance_request extends BaseAdapter {
    String[] id, description,reported_date,student_status;
    private Context context;
    Button bt;

    public Custom_maintenance_request(Context applicationContext, String[] id, String[] description , String[] reported_date, String[] student_status) {
        this.context = applicationContext;
        this.id = id;
        this.description = description;
        this.reported_date = reported_date;
        this.student_status = student_status;

    }

    @Override
    public int getCount() {
        return student_status.length;
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
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView = inflator.inflate(R.layout.activity_custommrequest, null);//same class name

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView18);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView19);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView70);


        tv1.setTextColor(Color.BLACK);//color setting
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);



        tv1.setText(reported_date[i]);
        tv2.setText(description[i]);
        tv3.setText(student_status[i]);







        return gridView;
    }

}
