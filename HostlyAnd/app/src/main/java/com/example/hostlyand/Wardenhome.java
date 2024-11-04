package com.example.hostlyand;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Wardenhome extends AppCompatActivity {

//    Button room;
    ImageView viewstudents, noti, scanner,addon,room1,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wardenhome);

        // Initialize UI components
        room1 = findViewById(R.id.imageView17);
        viewstudents = findViewById(R.id.imageView3);
        noti = findViewById(R.id.imageView4);
        scanner = findViewById(R.id.imageView9);
        addon = findViewById(R.id.imageView13);
        logout = findViewById(R.id.imageView16);

        // Set click listeners
//        room.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), Warden_room_allocation.class));
//            }
//        });

        viewstudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), WardenViewStudents.class));
            }
        });

        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), View_notification.class));
            }
        });

        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AndroidBarcodeQrExample.class));
            }
        });
        addon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), WardenviewAddontime.class));
            }
        });
        room1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Warden_room_allocation.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }
}
