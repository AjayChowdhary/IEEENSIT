package com.example.ajaychowdhary.ieeensit;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class membership_details extends AppCompatActivity {

    String s;
    SharedPreferences sharedPreferences;
    Member member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membership_details);
        sharedPreferences = this.getSharedPreferences("Memberdetails",0 );
        member = new Member();
        try {
            member.NAME = sharedPreferences.getString("NAME", "");
            member.PHONE = sharedPreferences.getString("Phone 1 - Value", "");
            member.EMAIL = sharedPreferences.getString("EMAIL ID", "");
            member.BRANCH = sharedPreferences.getString("BRANCH", "");

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        TextView textView=(TextView) findViewById(R.id.name_form);
        textView.setText(member.NAME);
        TextView B=(TextView) findViewById(R.id.branch_text);
        B.setText(member.BRANCH);
        TextView C=(TextView)findViewById(R.id.email);
        C.setText(member.EMAIL);
    }
}
