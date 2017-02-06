package com.example.ajaychowdhary.ieeensit.Activities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ajaychowdhary.ieeensit.R;
import com.example.ajaychowdhary.ieeensit.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register_App extends AppCompatActivity {

    //defining views
    SharedPreferences sharedPreferences;
    private Button buttonRegister;
    private EditText editTextEmail;
    private ProgressDialog progressDialog;
    private EditText editTextPhone;
    Intent intent;
    //URL to RegisterDevice.php
    private static final String URL_REGISTER_DEVICE = "http://fgethell.xyz/andpush/RegisterDevice.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__app);

        //getting views from xml
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        ImageView im=(ImageView) findViewById(R.id.registerimage);
            im.startAnimation(
                    AnimationUtils.loadAnimation(this, R.anim.rotate) );
         sharedPreferences= getSharedPreferences("userdata", 0);

        //adding listener to view
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editTextPhone.getText().toString().length()==10&&editTextEmail.getText().toString().length()>7) {
                    sendTokenToServer();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("phonenumber", editTextPhone.getText().toString());
                    editor.putString("email", editTextEmail.getText().toString());
                    editor.commit();
                    editTextEmail.setText("");
                    editTextPhone.setText("");
                    intent = new Intent(Register_App.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                    Toast.makeText(getApplicationContext(),"Please Enter Valid Details",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //storing token to mysql server
    private void sendTokenToServer() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering Device...");
        progressDialog.show();

        final String token = SharedPrefManager.getInstance(this).getDeviceToken();
        final String email = editTextEmail.getText().toString();
        final String phone = editTextPhone.getText().toString();

        if (token == null) {
            progressDialog.dismiss();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("phonenumber", "");
            editor.putString("email", "");
            editor.commit();
            Toast.makeText(this, "Token not generated", Toast.LENGTH_LONG).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER_DEVICE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("token", token);
                params.put("phone", phone);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}