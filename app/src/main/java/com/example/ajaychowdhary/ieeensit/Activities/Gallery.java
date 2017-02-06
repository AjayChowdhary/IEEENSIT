package com.example.ajaychowdhary.ieeensit.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.ajaychowdhary.ieeensit.Animation.Button_animation;
import com.example.ajaychowdhary.ieeensit.JSONparser;
import com.example.ajaychowdhary.ieeensit.R;
import com.example.ajaychowdhary.ieeensit.Adapters.gallery_adapter;
import com.example.ajaychowdhary.ieeensit.gallery_image;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Gallery extends AppCompatActivity {
GridView gridView;
List<gallery_image> galleryImageList;
    gallery_adapter galleryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        galleryImageList=new ArrayList<>();
        gridView=(GridView) findViewById(R.id.GalleryView);
            new getimages().execute();


    }


  private class getimages extends AsyncTask<Void ,List<gallery_image>, List<gallery_image> >
  {
      @Override
      protected void onPostExecute(List<gallery_image> gallery_images) {
          super.onPostExecute(gallery_images);
          galleryAdapter=new gallery_adapter(Gallery.this,0,galleryImageList);
          Log.d("gallery image lenght","00"+galleryImageList.size());
          gridView.setAdapter(galleryAdapter);
          gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  Button_animation btnAnimation = new Button_animation();
                  btnAnimation.animateButton(view, Gallery.this);

              }
          });
      }

      @Override
      protected List<gallery_image> doInBackground(Void... params) {

          JSONparser jsonparser=new JSONparser("https://graph.facebook.com/v2.5/176108879110422/photos?fields=name,source,likes.summary(true)&access_token=1079162565526730|X2XCCdxnMXpvWifJVGNn1iqcSl8");

          JSONArray jsonArray= null;
          try {
              jsonArray = jsonparser.getmainJsonObject().getJSONArray("data");
          } catch (JSONException e) {
              e.printStackTrace();
          }
          for(int i=0;i<20;i++)
              {
                  gallery_image g=new gallery_image();
                  try {
                      g.imageurl=jsonArray.getJSONObject(i).getString("source");
                  } catch (JSONException e) {
                      e.printStackTrace();
                  }
                  try {
                      g.about=jsonArray.getJSONObject(i).getString("name");
                  } catch (JSONException e) {
                      e.printStackTrace();
                  }
                  try {
                      g.likes=jsonArray.getJSONObject(i).getJSONObject("likes").getJSONObject("summary").getString("total_count")+"";
                  } catch (JSONException e) {
                      e.printStackTrace();
                  }
                  Log.d("galerry image",g.about);
                  try {
                      g.image=getBitmap(g.imageurl);
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
                  galleryImageList.add(g);


              }




          return galleryImageList;
      }
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
            mBitmap = builder.build().with(getApplicationContext()).load(url).get();
        }
        catch (Exception e)
        {e.printStackTrace();
            mBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.ieee_image);}

        return mBitmap;}

}
