package com.example.ajaychowdhary.ieeensit.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ajaychowdhary.ieeensit.JSONparser;
import com.example.ajaychowdhary.ieeensit.Member;
import com.example.ajaychowdhary.ieeensit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by AJAY CHOWDHARY on 31-01-2017.
 */

public class id_first_page extends Activity {
    String s;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_blank);
        final EditText editText=(EditText) findViewById(R.id.phonenumber);
        Button b=(Button) findViewById(R.id.generate);
        sharedPreferences = getSharedPreferences("Memberdetails", 0);
        member=new Member();

        member.NAME = sharedPreferences.getString("NAME", "");
        member.PHONE = sharedPreferences.getString("Phone 1 - Value","");
        member.EMAIL = sharedPreferences.getString("EMAIL ID", "");
        member.PHONE = sharedPreferences.getString("BRANCH", "");

        if(member.NAME!="") {
            Intent i=new Intent(id_first_page.this,membership_details.class);
            startActivity(i);
            finish();
        }

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s=editText.getText().toString();
                new generate().execute();
            }
        });
    }
    Member member;
    private class generate extends AsyncTask<Void ,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {

                member=ismember();
                if(member==null) {

                    Intent i=new Intent(id_first_page.this,errorid.class);
                    startActivity(i);
                    finish();//  setContentView(R.layout.act_errorid);

                }
                else{
                Intent i=new Intent(id_first_page.this,membership_details.class);
                startActivity(i);
                finish();
                }


            return null;
        }
    }

    JSONArray jsonArray;
    JSONObject jsonObject;

    Member ismember()
    {
        JSONparser jsonparser=new JSONparser("http://ieeensit.org/ieeemembers.json");
        try {
            jsonArray=jsonparser.getmainJsonObject().getJSONArray("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i=0;i<jsonArray.length();i++)

        {

            try {
                jsonObject=jsonArray.getJSONObject(i);
                Log.d("s and p",s+" "+jsonObject.getString("Phone 1 - Value")+"  akna"+ (s.equals(jsonObject.getString("Phone 1 - Value")))+"");

                String p=jsonObject.getString("Phone 1 - Value");
                if((s.equals(jsonObject.getString("Phone 1 - Value"))))
                {
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    member.NAME=jsonObject.getString("NAME");
                    editor.putString("NAME",member.NAME);
                    Log.d("meme",member.NAME+"sks");

                    member.BRANCH=jsonObject.getString("BRANCH");
                    editor.putString("BRANCH",member.BRANCH);


                    member.EMAIL=jsonObject.getString("EMAIL ID");
                    editor.putString("EMAIL ID",member.EMAIL);


                    member.PHONE=jsonObject.getString("Phone 1 - Value");
                    editor.putString("Phone 1 - Value",member.PHONE);

                    editor.commit();
                    return member;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        return null;
    }

}
