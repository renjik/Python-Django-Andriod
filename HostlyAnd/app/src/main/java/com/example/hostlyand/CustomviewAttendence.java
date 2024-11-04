package com.example.hostlyand;

//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class CustomviewAttendence extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.customview_attendence);
//    }
//}



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CustomviewAttendence extends BaseAdapter {
    String[] sid, att,date,stdname;
    private Context context;
    Button bt;

    public CustomviewAttendence(Context applicationContext, String[] sid, String[] att , String[] date, String[] stdname) {
        this.context = applicationContext;
        this.sid = sid;
        this.att = att;
        this.date = date;
        this.stdname = stdname;


    }

    @Override
    public int getCount() {
        return att.length;
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
            gridView = inflator.inflate(R.layout.customview_attendence, null);//same class name

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView30);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView31);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView32);

//        Button bt = (Button) gridView.findViewById(R.id.button7);
//        Button bt=(Button)gridView.findViewById(R.id.button7);
//        bt.setTag(bid[i]);
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "bid", Toast.LENGTH_SHORT).show();
//                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
//                SharedPreferences.Editor ed = sh.edit();
//                ed.putString("bid",view.getTag().toString());
//                ed.commit();
//                Intent i = new Intent(context,Passenger_send_complaints.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(i);
//            }
//        });



        tv1.setTextColor(Color.BLACK);//color setting
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);



//        tv1.setText(bus[i]);
        tv1.setText(att[i]);
        tv2.setText(date[i]);
        tv3.setText(stdname[i]);






        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);

        return gridView;
    }
    private void handleButtonClick(String busId) {

        Intent intent = new Intent(context, Studenthome.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
