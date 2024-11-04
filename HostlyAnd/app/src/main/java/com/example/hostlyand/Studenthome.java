package com.example.hostlyand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Studenthome extends AppCompatActivity {
    ImageView viewroom,attendence,noti,gpay,complaint,checkin,timer,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studenthome);
        viewroom=findViewById(R.id.imageView2);
        attendence=findViewById(R.id.imageView5);
        noti=findViewById(R.id.imageView6);
        gpay=findViewById(R.id.imageView8);
        complaint = findViewById(R.id.imageView11);
        checkin = findViewById(R.id.imageView12);
        timer = findViewById(R.id.imageView14);
        logout = findViewById(R.id.imageView15);

        viewroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),StudentViewAllocatedroom.class));

            }
        });
        attendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),StudentViewAttendence.class));

            }
        });
        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),View_notification.class));

            }
        });
        gpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),StudentPayrent.class));

            }
        });
        complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Viewreply.class));

            }
        });
        checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Viewcheckincheckout.class));

            }
        });
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),StudentviiewAddonStatus.class));

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));

            }
        });
    }
}

