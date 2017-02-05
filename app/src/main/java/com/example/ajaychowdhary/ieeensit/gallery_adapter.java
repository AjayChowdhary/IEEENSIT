package com.example.ajaychowdhary.ieeensit;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AJAY CHOWDHARY on 02-02-2017.
 */

public class gallery_adapter extends ArrayAdapter {
    //Context context;
    private Activity context;
    private int layoutResourceId;
    private List<gallery_image> data = new ArrayList();

    public gallery_adapter(Activity context, int layoutResourceId, List<gallery_image> data) {
        super(context, R.layout.gallery_item, data);
        //this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View listViewItem;
        listViewItem=layoutInflater.inflate(R.layout.gallery_item, null, true);
        ImageView im=(ImageView) listViewItem.findViewById(R.id.gallery_image);
        im.setImageBitmap(data.get(position).image);
        im.setScaleType(ImageView.ScaleType.FIT_XY);
        TextView tv=(TextView) listViewItem.findViewById(R.id.gallery_likes_number);
        tv.setText(data.get(position).likes);

        return listViewItem;
    }

}

