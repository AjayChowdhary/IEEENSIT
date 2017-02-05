package com.example.ajaychowdhary.ieeensit;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by AJAY CHOWDHARY on 17-01-2017.
 */

public class JSONparser {
    JSONObject jsonObject;
    JSONArray jsonArray;
    String token;
    Boolean fromcache=false;
    String JsonString="";
    String cachedata="";

    JSONparser(String token)
    {
        this.token = token;
        establishconnection();
        fromcache=false;
    }

    void establishconnection() {

        if (!fromcache) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {

                URL url = new URL(token);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                connection.setConnectTimeout(10000);

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                    Log.d("Response: ", "> " + line);
                }

                JsonString = buffer.toString();
                jsonObject = new JSONObject(buffer.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            JsonString = cachedata;
            Log.e("cache",cachedata);
            try {
                jsonObject= new JSONObject(JsonString);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public String getmainJsonString() {
        return JsonString;
    }

    public JSONArray getJsonArray(String s) {
        return jsonArray;
    }

    public JSONObject getmainJsonObject() {

        return jsonObject;

    }


}
