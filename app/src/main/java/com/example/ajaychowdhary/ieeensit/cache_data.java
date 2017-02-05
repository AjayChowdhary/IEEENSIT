package com.example.ajaychowdhary.ieeensit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AJAY CHOWDHARY on 24-01-2017.
 */

public class cache_data {
    List<Post> list_posts;
    boolean getfromcache=false;
    database dbhelper;
    SQLiteDatabase db;
    Context context;
   //` Post post;
    cache_data(Context context, boolean getfromcache)
    {
        this.context=context;
        this.getfromcache=getfromcache;
        dbhelper=new database(context);
        db=dbhelper.getWritableDatabase();
        list_posts=new ArrayList<>();


    }


   List<Post> getfeedsfromcache(){

       Cursor cursor =db.rawQuery("select * from "+ database.TABLE_NAME,null);
       //cursor.moveToFirst();
       while(cursor.moveToNext())
       {
           Post post=new Post();
           Log.d("feeds", cursor.getString(cursor.getColumnIndex("message"))+"cache_data");

               post.link=(cursor.getString(cursor.getColumnIndex("link")));
               post.message=(cursor.getString(cursor.getColumnIndex("message")));
               post.imageurl=(cursor.getString(cursor.getColumnIndex("image")));

               post.postid=cursor.getString(cursor.getColumnIndex("postid"));
               post.likes=cursor.getInt(cursor.getColumnIndex("likes"));
               post.date=cursor.getString(cursor.getColumnIndex("date"));

           list_posts.add(post);

       }
       cursor.close();
       return list_posts;
   }



    void putincache(List<Post> list_posts)
    {
        dbhelper.onUpgrade(db,0,0);
        for(int i=0;i<list_posts.size();i++)
        {
            Log.d("feeds", list_posts.get(i).postid+"l");
            ContentValues values=new ContentValues();
            values.put("postid",list_posts.get(i).postid);
            values.put("message",list_posts.get(i).message);
            values.put("link",list_posts.get(i).link);
            values.put("image",list_posts.get(i).imageurl);
            values.put("likes",list_posts.get(i).likes+"");
            values.put("date",list_posts.get(i).date);

            long h=db.insert(database.TABLE_NAME, null, values);
            if(h==-1)
                Log.d("cache","unable to add in cache");

        }

            db.close();

    }





}
