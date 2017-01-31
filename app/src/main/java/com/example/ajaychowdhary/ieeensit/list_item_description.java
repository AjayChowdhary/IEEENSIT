package com.example.ajaychowdhary.ieeensit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class list_item_description extends AppCompatActivity {
    String message;
    String link;
    Bitmap image;
    ImageView imageView;
    TextView textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_description);
        Intent intent=getIntent();
        message=intent.getStringExtra("message");
        link=intent.getStringExtra("link");
        byte[] bytes = intent.getByteArrayExtra("image");
        Bitmap image = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        imageView=(ImageView)findViewById(R.id.image);
        imageView.setImageBitmap(image);
        button=(Button)findViewById(R.id.link);

        if(link=="")
            button.setVisibility(View.INVISIBLE);
        else {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(link));
                    startActivity(i);
                }
            });
        }
        textView=(TextView)findViewById(R.id.des);
        textView.setText(message);




    }


}

