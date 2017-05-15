package com.example.amir.abcubefinal;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class main extends AppCompatActivity {
    //personal details
    EditText Firstname, Lastname, Country, Personal_Email, Phone, Course, Nationality, Citizenship, Passport_no, Visa_no, Visaexpiry;
    File file;
    Uri uri;
    EditText date;
    ImageView ImageView;
    Intent CamIntent, GalIntent, CropIntent;
    public static final int RequestPermissionCode = 1;
    DisplayMetrics displayMetrics;
    public Bitmap bitmap;

    RadioGroup Gender;
    Spinner MartialStatus;
    EditText Dob;
    Button buttonGallery, buttonCamera;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    Button submit;
    //educational background
    EditText Schoolname, Schoolmarks, Schoolyear,
            Highschoolname, Highschoolmarks, Highschoolyear,
            Bachelorname, Bachelormarks, Bacheloryear,
            Mastername, Mastermarks, Masteryear;
    //emergency contact
    EditText Name, Email, Relationship, Address, Phone_no;
    //English Language
    EditText Testname, Testdate, Testreport, Overallresult, Reading, Writing, Listening, Speaking;
    String server_url = "http://192.168.0.120/adcube/android.php";
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
//personal details
        Firstname = (EditText) findViewById(R.id.firstname);
        Lastname = (EditText) findViewById(R.id.lastname);
        Country = (EditText) findViewById(R.id.country);
        Dob = (EditText) findViewById(R.id.dob);
        Gender = (RadioGroup) findViewById(R.id.radiobtn);
        Personal_Email = (EditText) findViewById(R.id.emailaddress);
        Phone = (EditText) findViewById(R.id.mobile);
        Course = (EditText) findViewById(R.id.course);
        Nationality = (EditText) findViewById(R.id.nationality);
        MartialStatus = (Spinner) findViewById(R.id.spinner);
        Citizenship = (EditText) findViewById(R.id.citizenship);
        Passport_no = (EditText) findViewById(R.id.pass_no);
        Visa_no = (EditText) findViewById(R.id.visa_no);
        Visaexpiry = (EditText) findViewById(R.id.visa_expiry);
        ImageView = (ImageView) findViewById(R.id.imageview);
        submit = (Button) findViewById(R.id.submit);
        buttonGallery = (Button) findViewById(R.id.browsebtn);
        buttonCamera = (Button) findViewById(R.id.capturebtn);


        //martial status spinner
        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.Marital_status, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


//english test
        Testname = (EditText) findViewById(R.id.testname);
        Testdate = (EditText) findViewById(R.id.testdate);
        Testreport = (EditText) findViewById(R.id.testreport);
        Overallresult = (EditText) findViewById(R.id.overallresult);
        Reading = (EditText) findViewById(R.id.reading);
        Writing = (EditText) findViewById(R.id.writing);
        Listening = (EditText) findViewById(R.id.listening);
        Speaking = (EditText) findViewById(R.id.speaking);

//relation emergency
        Name = (EditText) findViewById(R.id.contactname);
        Email = (EditText) findViewById(R.id.email);
        Relationship = (EditText) findViewById(R.id.relation);
        Phone_no = (EditText) findViewById(R.id.phone);
        Address = (EditText) findViewById(R.id.address);

        builder = new AlertDialog.Builder(main.this);

       buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ClickImageFromCamera();

            }
        });

        buttonGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetImageFromGallery();

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String testname, testdate, testreport, overallresult, reading, writing, listening, speaking,
                        name, email, relation, address, phone;

                testname = Testname.getText().toString();
                testreport = Testreport.getText().toString();
                overallresult = Overallresult.getText().toString();
                reading = Reading.getText().toString();
                writing = Writing.getText().toString();
                listening = Listening.getText().toString();
                speaking = Speaking.getText().toString();
                testdate = Testdate.getText().toString();


                name = Name.getText().toString();
                email = Email.getText().toString();
                relation = Relationship.getText().toString();
                address = Address.getText().toString();
                phone = Phone_no.getText().toString();


                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        builder.setTitle("Server Response");
                        builder.setMessage("Response:" + response);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //englishtest
                                Testname.setText("");
                                Testdate.setText("");
                                Testreport.setText("");
                                Overallresult.setText("");
                                Reading.setText("");
                                Writing.setText("");
                                Listening.setText("");
                                Speaking.setText("");
                                //emergency
                                Name.setText("");
                                Email.setText("");
                                Relationship.setText("");
                                Address.setText("");
                                Phone_no.setText("");
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(main.this, error.toString(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }

                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        //testname
                        params.put("testname", testname);
                        params.put("testdate", testdate);
                        params.put("testreport", testreport);
                        params.put("overallresult", overallresult);
                        params.put("reading", reading);
                        params.put("writing", writing);
                        params.put("listening", listening);
                        params.put("speaking", speaking);
                        //emergency
                        params.put("name", name);
                        params.put("email", email);
                        params.put("relation", relation);
                        params.put("address", address);
                        params.put("phone", phone);
                        return params;
                    }
                };

                Mysingleton.getInstance(main.this).addToRequest(stringRequest);
            }

        });
    }




    //camera action
    public void ClickImageFromCamera() {

        CamIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        file = new File(Environment.getExternalStorageDirectory(),
                "file" + String.valueOf(System.currentTimeMillis()) + ".jpg");
        uri = Uri.fromFile(file);

        CamIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);

        CamIntent.putExtra("return-data", true);

        startActivityForResult(CamIntent, 0);

    }

    public void GetImageFromGallery() {

        GalIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(Intent.createChooser(GalIntent, "Select Image From Gallery"), 2);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            ImageCropFunction();

        } else if (requestCode == 2) {

            if (data != null) {

                uri = data.getData();

                ImageCropFunction();
            }
        } else if (requestCode == 1) {

            if (data != null) {


                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    Bundle bundle = data.getExtras();
                    bitmap =bundle.getParcelable("data");
                    ImageView.setImageBitmap(bitmap);
                    ImageView.setVisibility(View.VISIBLE);
                    Name.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }
    }


    public void ImageCropFunction() {

        // Image Crop Code
        try {
            CropIntent = new Intent("com.android.camera.action.CROP");

            CropIntent.setDataAndType(uri, "image/*");

            CropIntent.putExtra("crop", "true");
            CropIntent.putExtra("outputX", 180);
            CropIntent.putExtra("outputY", 180);
            CropIntent.putExtra("aspectX", 3);
            CropIntent.putExtra("aspectY", 4);

            CropIntent.putExtra("scaleUpIfNeeded", true);
            CropIntent.putExtra("return-data", true);

            startActivityForResult(CropIntent, 1);

        } catch (ActivityNotFoundException e) {

        }
    }

    private  void uploadImage(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,server_url , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String Response = jsonObject.getString("response");
                    Toast.makeText(main.this, Response, Toast.LENGTH_SHORT).show();
                    ImageView.setImageResource(0);
                    ImageView.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parms = new HashMap<>();
                parms.put("name", Name.getText().toString().trim());
                parms.put("image", imagetostring(bitmap));

                return parms;
            }
        };
        Mysingleton.getInstance(main.this).addToRequest(stringRequest);
    }

    //cconvert image to string for database
    private String imagetostring(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }
}



