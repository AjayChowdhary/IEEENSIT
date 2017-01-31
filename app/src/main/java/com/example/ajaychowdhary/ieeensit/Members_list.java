package com.example.ajaychowdhary.ieeensit;

import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by AJAY CHOWDHARY on 27-01-2017.
 */

public class Members_list {
    Context context;
    String url;
    SharedPreferences sharedPreferences;
    JSONparser jsonparser;
    JSONArray jsonArray;
    String mPhoneNumber;
    JSONObject jsonObject;
    Member member;
    public Members_list(Context context,String s )
    {
        this.context=context;
        mPhoneNumber=s;
        jsonparser=new JSONparser("http://ieeensit.org/ieeemembers.json");
        try {
            jsonArray=jsonparser.getmainJsonObject().getJSONArray("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        member=new Member();
        sharedPreferences= context.getSharedPreferences("Memberdetails",0);
        member.NAME=sharedPreferences.getString("NAME","");
        member.PHONE=sharedPreferences.getString("Phone 1 - Value","");
        member.EMAIL=sharedPreferences.getString("EMAIL ID","");
        member.PHONE=sharedPreferences.getString("BRANCH","");
            if(member.NAME=="")
                Check_id();

    }
    void Check_id()
    {

        for(int i=0;i<jsonArray.length();i++)
        {
            try {
                 jsonObject=jsonArray.getJSONObject(i);
                if(jsonObject.get("Phone 1 - Value")==mPhoneNumber)
                {
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    member.NAME=jsonObject.getString("NAME");
                    editor.putString("NAME",member.NAME);

                    member.BRANCH=jsonObject.getString("BRANCH");
                    editor.putString("BRANCH",member.BRANCH);


                    member.EMAIL=jsonObject.getString("EMAIL ID");
                    editor.putString("EMAIL ID",member.EMAIL);


                    member.PHONE=jsonObject.getString("Phone 1 - Value");
                    editor.putString("Phone 1 - Value",member.PHONE);

                    editor.commit();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
