package com.example.ajaychowdhary.ieeensit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    JSONArray jsonArray;

    String s,cmp;
    ListView listView;
    JSONparser j;
    public static String token="https://graph.facebook.com/v2.8/278952135548721?fields=description%2Ccover%2Cid%2Cposts%7Blink%2Cmessage%2Cfull_picture%7D&access_token=779358395553187%7C_5Qv8HWiZjpVAhrVU15UZFyVdjg";
    SharedPreferences prefs;
    ProgressBar progressBar;
    SwipeRefreshLayout refreshLayout;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView) findViewById(R.id.list);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        refreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipe_container);
        prefs = getSharedPreferences("com.mycompany.IEEENSIT", MODE_PRIVATE);
        sharedPreferences = getSharedPreferences("Jsonfile", 0);

        s = sharedPreferences.getString("JsonString","");
        Log.d("getsvalue",s+"111111");

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {

              if(!isNetworkConnected()) {
                  Toast.makeText(getApplicationContext(), "check internet connectivity!!!!", Toast.LENGTH_LONG).show();
                    refreshLayout.setRefreshing(false);
              }
                new feed().execute();


            }
        });

        if (prefs.getBoolean("firstrun", true)) {

            if(isNetworkConnected())
            {
                new feed().execute();
                prefs.edit().putBoolean("firstrun", false).commit();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"check internet connectivity!!!!",Toast.LENGTH_LONG).show();
            }

        }
        else
            new feed().execute();


    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }





    private class feed extends AsyncTask<Void,Void,List<Post>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(final List<Post> apost) {
            super.onPostExecute(apost);
            progressBar.setVisibility(View.INVISIBLE);
            Customlist customlist = new Customlist(MainActivity.this, apost);
            listView.setAdapter(customlist);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent=new Intent(MainActivity.this,list_item_description.class);
                    intent.putExtra("message",apost.get(position).message);

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    Bitmap bitmap=apost.get(position).image;

                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] bytes = stream.toByteArray();

                    intent.putExtra("image",bytes);
                    intent.putExtra("link",apost.get(position).link);
                    startActivity(intent);
                }
            });

            if(refreshLayout.isRefreshing())
            {if(s==cmp)
                Toast.makeText(getApplicationContext(),"THE FEEDS ARE UPTO DATE",Toast.LENGTH_LONG).show();
              else
                Toast.makeText(getApplicationContext(),"FEEDS UPDATED",Toast.LENGTH_LONG).show();
                refreshLayout.setRefreshing(false);

            }
        }

        @Override
        protected List<Post> doInBackground(Void... params) {


            JSONparser jsoncmp=new JSONparser(token);
            if(!isNetworkConnected())
                cmp="";
            else
                cmp=jsoncmp.getmainJsonString();
            Log.d("cmp",cmp);
            Log.d("svalue",s);
            if (s!=cmp&&s=="") {
                j = new JSONparser(token, false, "");
                s = j.getmainJsonString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Log.d("shared prefrence",s+"aaa");
                editor.putString("JsonString", s);
                editor.commit();

               // Log.d("pppppshared prefrence",+"aaa");

                JSONObject json = j.getmainJsonObject();
                try {
                    jsonArray = json.getJSONObject("posts").getJSONArray("data");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } else {

                 j = new JSONparser(token, true, s);

                Log.d("svalue",j.getmainJsonString()+"1111");
                try {
                    jsonArray = j.getmainJsonObject().getJSONObject("posts").getJSONArray("data");
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
            List<Post> postlist = new ArrayList<>();



            for (int i = 0; i < jsonArray.length(); i++) {
                final Post p = new Post();

                JSONObject temp = null;
                try {
                    temp = jsonArray.getJSONObject(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    p.message = temp.getString("message");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    p.link = temp.getString("link");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    p.postid = temp.getString("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String image_link= null;
                try {
                    image_link = temp.getString("full_picture");
                    p.image= getBitmap(image_link);
                  } catch (JSONException e) {
                    e.printStackTrace();

                    p.image=BitmapFactory.decodeResource(getResources(),R.drawable.ieee_image);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                postlist.add(p);
            }
            return postlist;
        }

        public Bitmap getBitmap(String url) throws IOException {
            Bitmap mBitmap;
            Picasso.Builder builder = new Picasso.Builder(getApplicationContext());
            builder.listener(new Picasso.Listener() {
                @Override
                public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                    exception.printStackTrace();
                }
            });

            mBitmap = builder.build().with(getApplicationContext()).load(url).get();
            return mBitmap;
        }





    }
}



