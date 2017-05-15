package com.example.amir.abcubefinal;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class Educational_background extends android.support.v4.app.Fragment {
public Object myObject;
    EditText Schoolname,Schoolmarks,Schoolyear,
            Highschoolname,Highschoolmarks,Highschoolyear,
             Bachelorname,Bachelormarks,Bacheloryear,
            Mastername,Mastermarks,Masteryear;
    Button ok;
    String server_url = "http://192.168.0.126/adcube/emergency.php";
    AlertDialog.Builder builder;
    View myview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview =  inflater.inflate(R.layout.educational_background,container,false);

        Schoolname = (EditText) myview.findViewById(R.id.schoolname);
        Schoolmarks = (EditText) myview.findViewById(R.id.schoolmarks);
        Schoolyear = (EditText) myview.findViewById(R.id.schoolaward);
        Highschoolname= (EditText) myview.findViewById(R.id.highschoolname);
        Highschoolmarks= (EditText) myview.findViewById(R.id.highschoolmarks);
        Highschoolyear= (EditText) myview.findViewById(R.id.highscchoolaward);
        Bachelorname= (EditText) myview.findViewById(R.id.bachelorname);
        Bachelormarks= (EditText) myview.findViewById(R.id.bachelormarks);
        Bacheloryear= (EditText) myview.findViewById(R.id.bacheloryear);
        Mastername= (EditText) myview.findViewById(R.id.mastername);
        Mastermarks= (EditText) myview.findViewById(R.id.mastermarks);
        Masteryear= (EditText) myview.findViewById(R.id.masteryear);



        builder = new AlertDialog.Builder(getActivity());
ok.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View view) {


        final String schoolname, schoolmarks, schoolyear, highschoolname, highschoolmarks, highshoolyear, bachelorname, bachelormarks, bacheloryear, mastername, mastermarks, masteryear;
        schoolname = Schoolyear.getText().toString();
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


                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        builder.setTitle("Server Response");
                        builder.setMessage("Response:" + response);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
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
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }

                }) {



                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("schoolname", schoolname);
                        params.put("schoolmarks", schoolmarks);
                        params.put("schoolyear",schoolyear);
                        params.put("highshoolname",highschoolname);
                        params.put("highschoolmarks",highschoolmarks);
                        params.put("highschoolyear",highshoolyear);
                        params.put("bachelorname",bachelorname);
                        params.put("bachelorlmarks",bachelormarks);
                        params.put("bacheloryear",bacheloryear);
                        params.put("mastername",mastername);
                        params.put("mastermarks",mastermarks);
                        params.put("masteryear",masteryear);
                        return params;
                    }
                };

                Mysingleton.getInstance(getActivity()).addToRequest(stringRequest);
            }

        });
        return myview;
    }

}
