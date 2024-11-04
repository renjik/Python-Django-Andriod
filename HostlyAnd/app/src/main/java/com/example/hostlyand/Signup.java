package com.example.hostlyand;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;

public class Signup extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    ImageView img;
    EditText student_name, dob, house_name, post, pincode, city, district, state, sem, phone, parent_no, uname, psw;
    Button bt;
    Spinner course;
    RadioButton male, female, other;
    String[] id, course_name;
    String myid="";

    final int CAMERA_PIC_REQUEST = 0, GALLERY_CODE = 201;
    private Uri mImageCaptureUri;
    public static String encodedImage = "";
    public static byte[] byteArray;
    private EditText dateInput;
    Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize UI elements
        course = findViewById(R.id.spinner);
        img = findViewById(R.id.imageView);
        student_name = findViewById(R.id.editTextTextPersonName3);
        house_name = findViewById(R.id.editTextTextPersonHname);
        dateInput = findViewById(R.id.dateInput);
        male = findViewById(R.id.radioButton);
        female = findViewById(R.id.radioButton2);
        other = findViewById(R.id.radioButton3);
        phone = findViewById(R.id.editTextTextPersonName9);
        post = findViewById(R.id.editTextTextPersonName5);
        pincode = findViewById(R.id.editTextTextPersonName6);
        city = findViewById(R.id.editTextTextPersonName7);
        district = findViewById(R.id.editTextTextPersonName8);
        state = findViewById(R.id.editTextTextPersonstate);
        sem = findViewById(R.id.editTextTextPersonSem);
        parent_no = findViewById(R.id.editTextTextPersonPno);
        uname = findViewById(R.id.editTextTextPersonName10);
        psw = findViewById(R.id.editTextTextPassword2);
        bt = findViewById(R.id.button3);
        course.setOnItemSelectedListener(this);

        fetchCourseData();

        // Set click listeners
        bt.setOnClickListener(this);
        img.setOnClickListener(this);

        // Date picker for dob field
        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), (view, selectedYear, selectedMonth, selectedDay) -> {
                    dateInput.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    private void fetchCourseData() {
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String url = sh.getString("url", "") + "view_course";
        Log.d("URL", "Fetching from: " + url);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                JSONArray js = jsonObj.getJSONArray("q");
                                id = new String[js.length()];
                                course_name = new String[js.length()];

                                for (int i = 0; i < js.length(); i++) {
                                    JSONObject u = js.getJSONObject(i);
                                    id[i] = u.getString("id");
                                    course_name[i] = u.getString("course_name");  // Storing course_name in array
                                }

                                ArrayAdapter<String> adapter = new ArrayAdapter<>(Signup.this, android.R.layout.simple_spinner_item, course_name);
                                course.setAdapter(adapter);
                            }
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Failed to fetch data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the RequestQueue
        requestQueue.add(postRequest);
    }

    @Override
    public void onClick(View view) {
        if (view == img) {
            selectImageOption();
        } else if (view == bt) {
            handleSignup();
        }
    }

    private void handleSignup() {
        if (isValidInput()) {
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String url = sh.getString("url", "") + "student_registration";
            Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();

            VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                    new Response.Listener<NetworkResponse>() {
                        @Override
                        public void onResponse(NetworkResponse response) {
                            try {
                                JSONObject obj = new JSONObject(new String(response.data));
                                if (obj.getString("status").equals("ok")) {
                                    Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                                    SharedPreferences.Editor ed = sh.edit();
                                    ed.putString("lid", obj.getString("lid"));
                                    ed.putString("sid", obj.getString("sid"));
                                    ed.apply();
                                    startActivity(new Intent(getApplicationContext(), Login.class));
                                } else {
                                    Toast.makeText(getApplicationContext(), "Student already registered", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("student_name", student_name.getText().toString().trim());
                    params.put("phone_number", phone.getText().toString().trim());
                    params.put("house_name", house_name.getText().toString().trim());
                    params.put("dob", dateInput.getText().toString().trim());
                    params.put("post_office", post.getText().toString().trim());
                    params.put("pin_no", pincode.getText().toString().trim());
                    params.put("city", city.getText().toString().trim());
                    params.put("district", district.getText().toString().trim());
                    params.put("state", state.getText().toString().trim());
                    params.put("parents_phone", parent_no.getText().toString().trim());
                    params.put("username", uname.getText().toString().trim());
                    params.put("password", psw.getText().toString().trim());
                    params.put("sem", sem.getText().toString().trim());
                    params.put("course", myid);

                    if (male.isChecked()) {
                        params.put("gender", "male");
                    } else if (female.isChecked()) {
                        params.put("gender", "female");
                    } else {
                        params.put("gender", "other");
                    }



                    return params;
                }

                @Override
                protected Map<String, DataPart> getByteData() {
                    Map<String, DataPart> params = new HashMap<>();
                    long imagename = System.currentTimeMillis();
                    params.put("photo", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                    return params;
                }
            };

            // Add the request to the queue
            Volley.newRequestQueue(this).add(volleyMultipartRequest);
        }
    }

    private boolean isValidInput() {
        // Validate student name
        if (student_name.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validate phone number (assuming 10 digits for example)
        String phoneText = phone.getText().toString().trim();
        if (phoneText.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your phone number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!phoneText.matches("\\d{10}")) {  // Ensures it's 10 numeric digits
            Toast.makeText(getApplicationContext(), "Please enter a valid 10-digit phone number", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validate house name
        if (house_name.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your house name", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validate date of birth
        if (dateInput.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please select your date of birth", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validate pincode (assuming 6 digits for example)
        String pincodeText = pincode.getText().toString().trim();
        if (pincodeText.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your pincode", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!pincodeText.matches("\\d{6}")) {  // Ensures it's 6 numeric digits
            Toast.makeText(getApplicationContext(), "Please enter a valid 6-digit pincode", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validate username
        if (uname.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter a username", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validate password (assuming a minimum length of 6 characters)
        if (psw.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter a password", Toast.LENGTH_SHORT).show();
            return false;
        } else if (psw.getText().toString().length() < 6) {
            Toast.makeText(getApplicationContext(), "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validate course selection
        if (myid.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please select a course", Toast.LENGTH_SHORT).show();
            return false;
        }
        String parentNoText = parent_no.getText().toString().trim();
        if (parentNoText.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your parent's phone number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!parentNoText.matches("\\d{10}")) {  // Ensures it's 10 numeric digits
            Toast.makeText(getApplicationContext(), "Please enter a valid 10-digit parent's phone number", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validate gender selection
        if (!male.isChecked() && !female.isChecked() && !other.isChecked()) {
            Toast.makeText(getApplicationContext(), "Please select your gender", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;  // All validations passed
    }
    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void selectImageOption() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
        builder.setTitle("Add Photo");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAMERA_PIC_REQUEST);
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, GALLERY_CODE);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CAMERA_PIC_REQUEST) {
                bitmap = (Bitmap) data.getExtras().get("data");
                img.setImageBitmap(bitmap);
            } else if (requestCode == GALLERY_CODE && data != null) {
                Uri selectedImageUri = data.getData();
                try {
                    InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    img.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Encode the image as byte array
            if (bitmap != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byteArray = byteArrayOutputStream.toByteArray();
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long iid) {
        myid=id[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
