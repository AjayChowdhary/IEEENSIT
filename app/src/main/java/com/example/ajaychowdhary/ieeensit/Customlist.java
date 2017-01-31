package com.example.ajaychowdhary.ieeensit;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by AJAY CHOWDHARY on 14-01-2017.
 */

public class Customlist extends ArrayAdapter {




    public ImageView image;
    public TextView textViewName;


    public Activity context;
    public List<Post> objects;
    public Customlist(Activity context,  List<Post> objects) {
            super(context, R.layout.list_item,objects);

        this.context = context;
        this.objects = objects;


    }



    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem;
        if(objects.get(position).message.equals(""))
        {
            listViewItem=inflater.inflate(R.layout.list_item_1, null, true);
            image = (ImageView) listViewItem.findViewById(R.id.imagenext);
            image.setImageBitmap(objects.get(position).image);

        }
        else if(objects.get(position).imageurl.equals("-1"))
        {
            listViewItem= inflater.inflate(R.layout.text_description_item, null, true);
            textViewName = (TextView) listViewItem.findViewById(R.id.data);
            textViewName.setText(objects.get(position).message);
            //ProgressBar pbar = (ProgressBar) listViewItem.findViewById(R.id.progressBar1);

            //pbar.setVisibility(View.INVISIBLE);

        }
        else{
            listViewItem= inflater.inflate(R.layout.list_item, null, true);
            textViewName = (TextView) listViewItem.findViewById(R.id.data);
            image = (ImageView) listViewItem.findViewById(R.id.image);
            //ProgressBar pbar = (ProgressBar) listViewItem.findViewById(R.id.progressBar1);

            image.setImageBitmap(objects.get(position).image);
            //pbar.setVisibility(View.INVISIBLE);

            textViewName.setText(objects.get(position).message);
        }
        AnimationSet set = new AnimationSet(true);
        TranslateAnimation slide = new TranslateAnimation(-100, 0, -100, 0);
        slide.setInterpolator(new DecelerateInterpolator(5.0f));
        slide.setDuration(300);
        Animation fade = new AlphaAnimation(0, 1.0f);
        fade.setInterpolator(new DecelerateInterpolator(5.0f));
        fade.setDuration(300);
        set.addAnimation(slide);
        set.addAnimation(fade);
        listViewItem.startAnimation(set);
        return listViewItem;


    }

    public TextView getTextViewName() {
        return textViewName;
    }
}
