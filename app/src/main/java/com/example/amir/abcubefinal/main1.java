package com.example.amir.abcubefinal;

import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class main1 extends AppCompatActivity implements View.OnClickListener {
    String server_url = "http://ranjansitikhu.com.np/abcube.php";
    ImageView ImageView;
    ViewGroup viewGroup;
    Button buttonCamera, buttonGallery, Clear;
    File file;
    Uri uri;
    String date;
    Intent CamIntent, GalIntent, CropIntent;
    public Bitmap bitmap;
    //for spinner
    private String[] mystring;//creating a string array named mystring
    Spinner samplespinner;
    String Selecteditem;

    //personal details
    EditText Firstname, Lastname, Country, Personal_Email, Phone, Course, Nationality, Citizenship, Passport_no, Visa_no,
            Pass_expiry, Visa_expiry, City, Visa_Granted,courseedate,currentschool;
    DatePicker Dob;
    Button Submit;
    RadioButton Gender;
    EditText fromDateEtxt, toDateEtxt;
    DatePickerDialog fromDatePickerDialog;
    DatePickerDialog toDatePickerDialog;
    DatePickerDialog tovisagrantedDialog;
    DatePickerDialog topassexpiry;
    DatePickerDialog totestdate, tosyear, tohyear, tobyear, tomyear,coursefinishdialog;


    SimpleDateFormat dateFormatter, dateFormatter2;
    //educational background
    EditText Schoolname, Schoolmarks, Schoolyear,
            Highschoolname, Highschoolmarks, Highschoolyear,
            Bachelorname, Bachelormarks, Bacheloryear,
            Mastername, Mastermarks, Masteryear;


    //emergency contact
    EditText Name, Email, Relationship, Address, Phone_no;

    //English Language
    EditText Testname, Testdate, Testreport, Overallresult, Reading, Writing, Listening, Speaking;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout1);
        System.out.println("enteron");
        //for date picker
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        dateFormatter2 = new SimpleDateFormat("yyyy", Locale.US);
        findViewsById();
        setDateTimeField();
    }

    public void findViewsById() {
        fromDateEtxt = (EditText) findViewById(R.id.date);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);

        toDateEtxt = (EditText) findViewById(R.id.visa_expiry);
        toDateEtxt.setInputType(InputType.TYPE_NULL);

        courseedate = (EditText) findViewById(R.id.coursedate);
        courseedate.setInputType(InputType.TYPE_NULL);

        Visa_Granted = (EditText) findViewById(R.id.visa_grant);
        Visa_Granted.setInputType(InputType.TYPE_NULL);

        Pass_expiry = (EditText) findViewById(R.id.pass_expiry);
        Pass_expiry.setInputType(InputType.TYPE_NULL);

        Testdate = (EditText) findViewById(R.id.testdate);
        Testdate.setInputType(InputType.TYPE_NULL);

        Schoolyear = (EditText) findViewById(R.id.schoolyear);
        Schoolyear.setInputType(InputType.TYPE_NULL);

        Highschoolyear = (EditText) findViewById(R.id.highscchoolyear);
        Highschoolyear.setInputType(InputType.TYPE_NULL);

        Bacheloryear = (EditText) findViewById(R.id.bacheloryear);
        Bacheloryear.setInputType(InputType.TYPE_NULL);

        Masteryear = (EditText) findViewById(R.id.masteryear);
        Masteryear.setInputType(InputType.TYPE_NULL);

    }

    public void setDateTimeField() {
        fromDateEtxt.setOnClickListener(this);
        toDateEtxt.setOnClickListener(this);
        Visa_Granted.setOnClickListener(this);
        Pass_expiry.setOnClickListener(this);
        Testdate.setOnClickListener(this);
        Schoolyear.setOnClickListener(this);
        Highschoolyear.setOnClickListener(this);
        Bacheloryear.setOnClickListener(this);
        Masteryear.setOnClickListener(this);
        courseedate.setOnClickListener(this);


        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                toDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        coursefinishdialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                courseedate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        tovisagrantedDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                Visa_Granted.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        topassexpiry = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                Pass_expiry.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        totestdate = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                Testdate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        tosyear = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                Schoolyear.setText(dateFormatter2.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        tohyear = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                Highschoolyear.setText(dateFormatter2.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        tobyear = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                Bacheloryear.setText(dateFormatter2.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        tomyear = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                Masteryear.setText(dateFormatter2.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        //for spinner
        ArrayAdapter sampleadapter;//Assigning a name for ArrayAdapter
        Resources res = getResources();//Assigning a name for Resources
        mystring = res.getStringArray(R.array.Marital_status);//getting the array items to string named my string
        //mystring is an array which is defined on the top
        samplespinner = (Spinner) findViewById(R.id.spinner); //samplespinner is defined in the top
        //samplespinner is the name given to the spinner at the top
        sampleadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mystring);
        samplespinner.setAdapter(sampleadapter);
        samplespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //Toast.makeText(getBaseContext(), spVIA.getSelectedItem().toString(),
                //Toast.LENGTH_LONG).show();

                Selecteditem = samplespinner.getSelectedItem().toString();
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });


        //for radio button
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                switch (checkedId) {
                    case R.id.male:
                        Gender = (RadioButton) findViewById(R.id.male);
                        break;
                    case R.id.female:
                        Gender = (RadioButton) findViewById(R.id.female);
                        break;
                }
            }
        });

        //personal details
        ImageView = (ImageView) findViewById(R.id.imageview);
        Firstname = (EditText) findViewById(R.id.firstname);
        Lastname = (EditText) findViewById(R.id.lastname);
        Country = (EditText) findViewById(R.id.country);
        City = (EditText) findViewById(R.id.city);
        Personal_Email = (EditText) findViewById(R.id.email);
        Phone = (EditText) findViewById(R.id.mobile);
        Course = (EditText) findViewById(R.id.course);
        Nationality = (EditText) findViewById(R.id.nationality);
        Citizenship = (EditText) findViewById(R.id.citizenship);
        Passport_no = (EditText) findViewById(R.id.pass_no);
        Visa_no = (EditText) findViewById(R.id.visa_no);
        currentschool = (EditText)findViewById(R.id.currentSchool);



        //educational background
        Schoolname = (EditText) findViewById(R.id.schoolname);
        Schoolmarks = (EditText) findViewById(R.id.schoolmarks);
        Schoolyear = (EditText) findViewById(R.id.schoolyear);
        Highschoolname = (EditText) findViewById(R.id.highschoolname);
        Highschoolmarks = (EditText) findViewById(R.id.highschoolmarks);
        Highschoolyear = (EditText) findViewById(R.id.highscchoolyear);
        Bachelorname = (EditText) findViewById(R.id.bachelorname);
        Bachelormarks = (EditText) findViewById(R.id.bachelormarks);
        Bacheloryear = (EditText) findViewById(R.id.bacheloryear);
        Mastername = (EditText) findViewById(R.id.mastername);
        Mastermarks = (EditText) findViewById(R.id.mastermarks);
        Masteryear = (EditText) findViewById(R.id.masteryear);


        //eergency ccontact
        Name = (EditText) findViewById(R.id.contactname);
        Email = (EditText) findViewById(R.id.emailaddress);
        Relationship = (EditText) findViewById(R.id.relation);
        Phone_no = (EditText) findViewById(R.id.phone);
        Address = (EditText) findViewById(R.id.address);

        //english language
        Testname = (EditText) findViewById(R.id.testname);
        Testdate = (EditText) findViewById(R.id.testdate);
        Testreport = (EditText) findViewById(R.id.testreport);
        Overallresult = (EditText) findViewById(R.id.overallresult);
        Reading = (EditText) findViewById(R.id.reading);
        Writing = (EditText) findViewById(R.id.writing);
        Listening = (EditText) findViewById(R.id.listening);
        Speaking = (EditText) findViewById(R.id.speaking);

        buttonCamera = (Button) findViewById(R.id.capturebtn);
        buttonGallery = (Button) findViewById(R.id.browsebtn);
        Submit = (Button) findViewById(R.id.submit);
        Clear = (Button) findViewById(R.id.clear);
        buttonCamera.setOnClickListener(this);
        buttonGallery.setOnClickListener(this);
        Submit.setOnClickListener(this);
        Clear.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.browsebtn:
                GetImageFromGallery();
                break;
            case R.id.capturebtn:
                ClickImageFromCamera();
                break;
            case R.id.submit:
                submitall();
                break;
            case R.id.clear:
                clearall();
                break;
            case R.id.date:
                fromDatePickerDialog.show();
                break;
            case R.id.visa_expiry:
                toDatePickerDialog.show();
                break;
            case R.id.visa_grant:
                tovisagrantedDialog.show();
                break;
            case R.id.pass_expiry:
                topassexpiry.show();
                break;
            case R.id.testdate:
                totestdate.show();
                break;
            case R.id.schoolyear:
                tosyear.show();
                break;
            case R.id.highscchoolyear:
                tohyear.show();
                break;
            case R.id.bacheloryear:
                tobyear.show();
                break;
            case R.id.masteryear:
                tomyear.show();
                break;
            case R.id.coursedate:
                coursefinishdialog.show();
                break;
        }
    }

    public void clearall() {
        Firstname.setText("");
        Lastname.setText("");
        Country.setText("");
        City.setText("");
        Email.setText("");
        Personal_Email.setText("");
        Phone.setText("");
        Phone_no.setText("");
        Nationality.setText("");
        Citizenship.setText("");
        Passport_no.setText("");
        Pass_expiry.setText("");
        Visa_no.setText("");
        Visa_Granted.setText("");
        toDateEtxt.setText("");
        fromDateEtxt.setText("");
        currentschool.setText("");
        courseedate.setText("");
        samplespinner.setSelection(0);
        Testname.setText("");
        Testdate.setText("");
        Testreport.setText("");
        Overallresult.setText("");
        Reading.setText("");
        Writing.setText("");
        Listening.setText("");
        Speaking.setText("");
        Schoolname.setText("");
        Schoolmarks.setText("");
        Schoolyear.setText("");
        Highschoolname.setText("");
        Highschoolmarks.setText("");
        Highschoolyear.setText("");
        Bachelorname.setText("");
        Bachelormarks.setText("");
        Bacheloryear.setText("");
        Mastername.setText("");
        Mastermarks.setText("");
        Masteryear.setText("");
        Name.setText("");
        Relationship.setText("");
        Address.setText("");
        Phone_no.setText("");
        Email.setText("");
        ImageView.setImageDrawable(null);

    }

    public void submitall() {
        final String first_name, last_name, country, personal_address, dob, personal_email, course, citizenship, nationality, pass_no,
                visa_no, visa_expiry, phone_no, contact, address, email, phone, relation, pass_expiry, gender, CFD, CS, visa_grant, ephone;

        //String PATTERN = "([a-zA-Z]{3,30})+";
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        first_name = Firstname.getText().toString();

        if (first_name.isEmpty()) {
            Firstname.setError("Enter Your Firstname");
            return;
        }


        last_name = Lastname.getText().toString();
        if (last_name.isEmpty()) {
            Lastname.setError("Enter Your Lastname");
            return;
        }


        personal_address = City.getText().toString();
        if (personal_address.isEmpty()) {
            City.setError("Enter Your Firstname");
            return;
        }
        country = Country.getText().toString();
        if (country.isEmpty()) {
            Country.setError("Enter Your Country Name");
            return;
        }


        personal_email = Personal_Email.getText().toString();
        if (personal_email.isEmpty() || !personal_email.matches(emailPattern)) {
            Personal_Email.setError("Enter Valid Email Address");
            return;
        }

        phone_no = Phone_no.getText().toString();

        dob = fromDateEtxt.getText().toString();


        nationality = Nationality.getText().toString();
        if (nationality.isEmpty()) {
            Nationality.setError("This field is required");
            return;
        }


        citizenship = Citizenship.getText().toString();
        if (citizenship.isEmpty()) {
            Citizenship.setError("This field is required");
            return;
        }


        pass_no = Passport_no.getText().toString();
        if (pass_no.isEmpty()) {
            Passport_no.setError("Enter Your Passport No");
            return;
        }
        pass_expiry = Pass_expiry.getText().toString();
        visa_no = Visa_no.getText().toString();
        visa_grant = Visa_Granted.getText().toString();
        visa_expiry = toDateEtxt.getText().toString();
        gender = Gender.getText().toString();
        CFD = courseedate.getText().toString();
        CS = currentschool.getText().toString();


        //emergency contact
        contact = Name.getText().toString();
        if (contact.isEmpty()) {
            Name.setError("Enter Your Contact Name");
            return;
        }


        email = Email.getText().toString();
        if (email.isEmpty() || !personal_email.matches(emailPattern)) {
            Email.setError("Enter Valid Email Address");
            return;
        }


        address = Address.getText().toString();
        if (address.isEmpty()) {
            Address.setError("Enter Your Address");
            return;
        }
        phone = Phone_no.getText().toString();
        if (phone.isEmpty() || !personal_email.matches(emailPattern)) {
            Phone.setError("Enter Your  Phone No");
            return;
        }
        relation = Relationship.getText().toString();
        if (relation.isEmpty()) {
            Relationship.setError("Enter Valid Email Address");
            return;
        }


        //english test
        final String testname, testdate, testreport, overallresult, reading, writing, listening, speaking;
        testname = Testname.getText().toString();


        testreport = Testreport.getText().toString();
        overallresult = Overallresult.getText().toString();
        reading = Reading.getText().toString();
        writing = Writing.getText().toString();
        listening = Listening.getText().toString();
        speaking = Speaking.getText().toString();
        testdate = Testdate.getText().toString();


        //educational background
        final String schoolname, schoolmarks, schoolyear, highschoolname, highschoolmarks,
                highshoolyear, bachelorname, bachelormarks, bacheloryear, mastername, mastermarks, masteryear;
        schoolname = Schoolname.getText().toString();


        schoolmarks = Schoolmarks.getText().toString();
        schoolyear = Schoolyear.getText().toString();
        highschoolname = Highschoolname.getText().toString();


        highschoolmarks = Highschoolmarks.getText().toString();
        highshoolyear = Highschoolyear.getText().toString();
        bachelorname = Bachelorname.getText().toString();


        bachelormarks = Bachelormarks.getText().toString();
        bacheloryear = Bacheloryear.getText().toString();
        mastername = Mastername.getText().toString();


        mastermarks = Mastermarks.getText().toString();
        masteryear = Masteryear.getText().toString();


        if (!first_name.isEmpty() && !last_name.isEmpty() && !country.isEmpty() && !personal_email.isEmpty() && !phone_no.isEmpty()
                 && !nationality.isEmpty() && !citizenship.isEmpty() && !pass_no.isEmpty() && !visa_no.isEmpty()
                && !visa_expiry.isEmpty() && !contact.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !relation.isEmpty()) {


        }
            StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String Response = jsonObject.getString("cropIntent");
                        Toast.makeText(main1.this, Response, Toast.LENGTH_SHORT).show();
                        ImageView.setImageResource(0);
                        ImageView.setVisibility(View.GONE);
                        // Firstname.setText("");
                        // Firstname.setVisibility(View.GONE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(main1.this, response.toString(), Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(main1.this, "Enter the image", Toast.LENGTH_SHORT).show();
                }

            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("first_name", first_name);
                    params.put("country", country);
                    params.put("dob", dob);
                    params.put("personal_email", personal_email);
                    params.put("mobile", phone_no);
                    params.put("nationality", nationality);
                    params.put("citizenship", citizenship);
                    params.put("pass_no", pass_no);
                    params.put("pass_expiry", pass_expiry);
                    params.put("visa_no", visa_no);
                    params.put("visa_granted_date", visa_grant);
                    params.put("visa_expiry", visa_expiry);
                    params.put("gender", gender);
                    params.put("status", Selecteditem);
                    params.put("image", imageToString(bitmap));
                    params.put("CFD", CFD);
                    params.put("CS", CS);
                    params.put("last_name", last_name);
                    params.put("personal_address", personal_address);

                    //educational background
                    params.put("schoolname", schoolname);
                    params.put("schoolmarks", schoolmarks);
                    params.put("schoolyear", schoolyear);
                    params.put("highschoolname", highschoolname);
                    params.put("highschoolmarks", highschoolmarks);
                    params.put("highschoolyear", highshoolyear);
                    params.put("bachelorname", bachelorname);
                    params.put("bachelormarks", bachelormarks);
                    params.put("bacheloryear", bacheloryear);
                    params.put("mastername", mastername);
                    params.put("mastermarks", mastermarks);
                    params.put("masteryear", masteryear);

                    //english test
                    params.put("testname", testname);
                    params.put("testdate", testdate);
                    params.put("testreport", testreport);
                    params.put("overallresult", overallresult);
                    params.put("reading", reading);
                    params.put("writing", writing);
                    params.put("listening", listening);
                    params.put("speaking", speaking);

                    //emergency contact
                    params.put("contact", contact);
                    params.put("relation", relation);
                    params.put("address", address);
                    params.put("phone", phone);
                    params.put("email", email);
                    return params;
                }


            };


            Mysingleton.getInstance(main1.this).addToRequest(stringRequest);
        }









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
                    bitmap = bundle.getParcelable("data");
                    ImageView.setImageBitmap(bitmap);
                    ImageView.setVisibility(View.VISIBLE);
                    Name.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    private void uploadImage() {
        StringRequest stringrequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String Response = jsonObject.getString("cropIntent");
                    Toast.makeText(main1.this, Response, Toast.LENGTH_SHORT).show();
                    ImageView.setImageResource(0);
                    ImageView.setVisibility(View.GONE);
                    Name.setText("");
                    Name.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(main1.this, "successful", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(main1.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parms = new HashMap<>();
                parms.put("name", Name.getText().toString().trim());
                parms.put("image", imageToString(bitmap));

                return parms;
            }
        };
        Mysingleton.getInstance(main1.this).addToRequest(stringrequest);
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
    //cconvert image to string for database

    public static String imageToString(Bitmap BitmapData) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        BitmapData.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] byte_arr = bos.toByteArray();
        String encoded = Base64.encodeToString(byte_arr, Base64.DEFAULT); //appendLog(file);
        return encoded;
    }
}








