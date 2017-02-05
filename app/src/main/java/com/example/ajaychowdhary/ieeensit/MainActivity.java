package com.example.ajaychowdhary.ieeensit;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //boolean dataincache = false;

    JSONArray jsonArray;
    ListView listView;
    JSONparser j;
    public static String token="https://graph.facebook.com/v2.8/278952135548721?fields=description%2Ccover%2Cid%2Cposts%7Blink%2Cmessage%2Cfull_picture%2Ccreated_time%2Clikes%7D&access_token=779358395553187%7C_5Qv8HWiZjpVAhrVU15UZFyVdjg";
    ProgressBar progressBar;
    SwipeRefreshLayout refreshLayout;
    SharedPreferences sharedPreferences;
    String Jsonstring,Jsonstring_cmp;
    List<Post> list_post=new ArrayList<>();;
    FloatingActionButton fab;
    FloatingActionButton fab1;
    FloatingActionButton fab2;
    FloatingActionButton fab3;
    private boolean FAB_Status = false;
    cache_data cache;
    //Animations
    Animation show_fab_1;
    Animation hide_fab_1;
    Animation show_fab_2;
    Animation hide_fab_2;
    Animation show_fab_3;
    Animation hide_fab_3;
    Boolean is_cache_data_old=false;
    Boolean noviewload=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        refreshLayout=(SwipeRefreshLayout) findViewById(R.id.swipe_container);
        //slide drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //navigation
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //floating button
        fabbutton_function();

        sharedPreferences=getSharedPreferences("Jsonfile",0);
        listView = (ListView) findViewById(R.id.list);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                {Log.d("in function","workking");
                    if(isNetworkConnected()) {
                        Toast.makeText(getApplicationContext(), "FEEDS UPDATED", Toast.LENGTH_SHORT).show();
                        refreshLayout.setRefreshing(false);
                    }else {
                        Toast.makeText(getApplicationContext(), "Can't Connect!!!!", Toast.LENGTH_SHORT).show();
                        refreshLayout.setRefreshing(false);
                    }
                }
            }
        });


        new feed().execute();


    }

    private void fabbutton_function()
    {

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab_1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab_2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab_3);

        //Animations
        show_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab1_show);
        hide_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab1_hide);
        show_fab_2 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab2_show);
        hide_fab_2 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab2_hide);
        show_fab_3 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab3_show);
        hide_fab_3 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab3_hide);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (FAB_Status == false) {
                    //Display FAB menu
                    expandFAB();
                    FAB_Status = true;
                } else {
                    //Close FAB menu
                    hideFAB();
                    FAB_Status = false;
                }
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(),"ANY PROJECT IDEA!! NEED OUR HELP? ", Toast.LENGTH_SHORT).show();
                if(isNetworkConnected()){
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSdVDvD5ZbFkHBdxfPtLuntFW_gjgry07wxmyrzOt-6HcvyuqQ/viewform?c=0&w=1"));
                startActivity(i);}
                else
                    Toast.makeText(getApplicationContext(),"Can't Connect!!!!",Toast.LENGTH_SHORT).show();


            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "Volunteer For IEEE", Toast.LENGTH_SHORT).show();
                if(isNetworkConnected()) {
                    Intent i = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLScU0P0GOLs2KXj6yg9K8TIUHPrKd377FwLL2dUZKscI6ZNLaQ/viewform?c=0&w=1"));
                    startActivity(i);
                }
                else
                Toast.makeText(getApplicationContext(),"Can't Connect!!!!",Toast.LENGTH_SHORT).show();

            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplication(), "Feed Back", Toast.LENGTH_SHORT).show();
                if(isNetworkConnected()){
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLScoX_cSslLFzZ-gjg1eqa32PXeaU3cRxQK3l2gHFQSrQ-BjHg/viewform?c=0&w=1"));
                startActivity(i);}
                else
                    Toast.makeText(getApplicationContext(),"Can't Connect!!!!",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void expandFAB() {

        //Floating Action Button 1
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab1.getLayoutParams();
        layoutParams.rightMargin += (int) (fab1.getWidth() * 1.7);
        layoutParams.bottomMargin += (int) (fab1.getHeight() * 0.25);
        fab1.setLayoutParams(layoutParams);
        fab1.startAnimation(show_fab_1);
        fab1.setClickable(true);

        //Floating Action Button 2
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
        layoutParams2.rightMargin += (int) (fab2.getWidth() * 1.5);
        layoutParams2.bottomMargin += (int) (fab2.getHeight() * 1.5);
        fab2.setLayoutParams(layoutParams2);
        fab2.startAnimation(show_fab_2);
        fab2.setClickable(true);

        //Floating Action Button 3
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab3.getLayoutParams();
        layoutParams3.rightMargin += (int) (fab3.getWidth() * 0.25);
        layoutParams3.bottomMargin += (int) (fab3.getHeight() * 1.7);
        fab3.setLayoutParams(layoutParams3);
        fab3.startAnimation(show_fab_3);
        fab3.setClickable(true);
    }


    private void hideFAB() {

        //Floating Action Button 1
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab1.getLayoutParams();
        layoutParams.rightMargin -= (int) (fab1.getWidth() * 1.7);
        layoutParams.bottomMargin -= (int) (fab1.getHeight() * 0.25);
        fab1.setLayoutParams(layoutParams);
        fab1.startAnimation(hide_fab_1);
        fab1.setClickable(false);

        //Floating Action Button 2
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
        layoutParams2.rightMargin -= (int) (fab2.getWidth() * 1.5);
        layoutParams2.bottomMargin -= (int) (fab2.getHeight() * 1.5);
        fab2.setLayoutParams(layoutParams2);
        fab2.startAnimation(hide_fab_2);
        fab2.setClickable(false);

        //Floating Action Button 3
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab3.getLayoutParams();
        layoutParams3.rightMargin -= (int) (fab3.getWidth() * 0.25);
        layoutParams3.bottomMargin -= (int) (fab3.getHeight() * 1.7);
        fab3.setLayoutParams(layoutParams3);
        fab3.startAnimation(hide_fab_3);
        fab3.setClickable(false);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent i;
        switch (id) {
            case R.id.nav_feeds:

                break;
            case R.id.nav_gallery:
                if(isNetworkConnected())
                { i=new Intent(this,Gallery.class);
                startActivity(i);}
                else
                Toast.makeText(getApplicationContext(),"Can't Connect!!!!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_SIGs:
                if(isNetworkConnected())
                { i=new Intent(this,sigs.class);
                    startActivity(i);}
                else
                    Toast.makeText(getApplicationContext(),"Can't Connect!!!!",Toast.LENGTH_SHORT).show();

                break;
            case R.id.Contribute:
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.activity_contribute);

                dialog.setTitle("DEVELOPER SWAG");
                TextView  rep = (TextView) dialog.findViewById(R.id.GoToRepo);
                rep.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse("https://github.com/AjayChowdhary/IEEENSIT");
                        if(isNetworkConnected())
                        {Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);}
                    }
                });
                TextView con= (TextView) dialog.findViewById(R.id.cont);
                con.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse("https://github.com/AjayChowdhary/IEEENSIT/wiki");
                        if(isNetworkConnected())
                        {Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);}
                    }
                });
                dialog.show();

                break;






            case R.id.aboutus:
                i=new Intent(this,About_US.class);
                startActivity(i);
                break;
            case R.id.membership_card:
                if(isNetworkConnected())
                { i=new Intent(this,id_first_page.class);
                    startActivity(i);}
                else
                    Toast.makeText(getApplicationContext(),"Can't Connect!!!!",Toast.LENGTH_SHORT).show();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;

    }


    private class feed extends AsyncTask<Void,Void,List<Post> > {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(final List<Post> apost) {
            super.onPostExecute(apost);



            progressBar.setVisibility(View.GONE);

            if(noviewload)
            {Toast.makeText(getApplicationContext(), "check internet connectivity!!!!", Toast.LENGTH_LONG).show();
                setContentView(R.layout.temp);
                /*FrameLayout layout=(FrameLayout) findViewById(R.id.content_frame);
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    layout.setBackgroundDrawable( getResources().getDrawable(R.mipmap.ieee_logo) );
                } else {
                    layout.setBackground( getResources().getDrawable(R.mipmap.ieee_logo));
                }*/
            }
            else {



                            Customlist customlist = new Customlist(MainActivity.this, apost);
                            listView.setAdapter(customlist);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                                    if (id != 1) {
                                        final Intent intent = new Intent(MainActivity.this, list_item_description.class);
                                        intent.putExtra("message", apost.get(position).message);
                                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                        Bitmap bitmap = apost.get(position).image;
                                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                        byte[] bytes = stream.toByteArray();
                                        intent.putExtra("image", bytes);
                                        intent.putExtra("link", apost.get(position).link);

                                        startActivity(intent);
                                    }
                                }


                            });

                        }

                 }






        @Override
        protected List<Post> doInBackground(Void... params) {
            j=new JSONparser(token);
            Jsonstring = sharedPreferences.getString("JsonString","");
            Jsonstring_cmp = j.getmainJsonString();

            cache = new cache_data(getApplicationContext(), true);
            list_post = cache.getfeedsfromcache();

            if(isNetworkConnected()) {

               if (Jsonstring_cmp.equals(Jsonstring)) {

                   if ((list_post.size() != 0)) {
                       for (int i = 0; i < list_post.size(); i++)
                           try {
                               if (list_post.get(i).imageurl == "-1")
                                   list_post.get(i).image = BitmapFactory.decodeResource(getResources(), R.drawable.ieee_image);
                                else
                                  list_post.get(i).image= getBitmap(list_post.get(i).imageurl);
                           } catch (Exception e) {
                                    list_post.get(i).image = BitmapFactory.decodeResource(getResources(), R.drawable.ieee_image);
                                     e.printStackTrace();
                           }

                       return list_post;
                   }

               }
                //else

             list_post=getdatafromnet(cache);
                return list_post;
           }
            else
           {
               if ((list_post.size() != 0)) {
                   for (int i = 0; i < list_post.size(); i++)
                       try {
                           if (list_post.get(i).imageurl == "-1")
                               list_post.get(i).image = BitmapFactory.decodeResource(getResources(), R.drawable.ieee_image);
                           else
                               list_post.get(i).image = getBitmap(list_post.get(i).imageurl);
                       } catch (IOException e) {
                           list_post.get(i).image = BitmapFactory.decodeResource(getResources(), R.drawable.ieee_image);
                           e.printStackTrace();
                       }

                   return list_post;
               }
               else
               {
                   noviewload=true;
                   return null;
               }

           }

        }


    }








        List<Post> getdatafromnet(cache_data this_cache) {

            List<Post> listpost_net = new ArrayList<>();
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("JsonString",j.getmainJsonString());
            editor.commit();
            JSONObject json = j.getmainJsonObject();
            try {
                jsonArray = json.getJSONObject("posts").getJSONArray("data");
            } catch (JSONException e) {
                e.printStackTrace();
            }

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
                    Log.d("message", p.message);
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

                try {
                    p.likes = temp.getJSONObject("likes").getJSONArray("data").length();
                    Log.d("likes found", "00" + p.likes);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("likes not found", "00" + p.likes + " ");
                }
                try {
                    p.date = temp.getString("created_time");
                    p.date = p.date.substring(0, 10);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //p.postid = temp.getString("id");
                String image_link = "";
                try {

                    image_link = temp.getString("full_picture");
                    p.imageurl = image_link;
                    p.image = getBitmap(image_link);

                } catch (JSONException e) {

                    e.printStackTrace();
                    p.imageurl = "-1";
                    p.image = BitmapFactory.decodeResource(getResources(), R.drawable.ieee_image);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                listpost_net.add(p);
            }
            this_cache.putincache(listpost_net);
            return listpost_net;
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

            try {
                mBitmap = builder.build().with(MainActivity.this).load(url).get();

            } catch (Exception e) {
                e.printStackTrace();
                mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ieee_image);
            }

            return mBitmap;
        }


}
