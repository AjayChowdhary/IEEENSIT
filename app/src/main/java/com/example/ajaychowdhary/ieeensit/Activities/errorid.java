package com.example.ajaychowdhary.ieeensit.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ajaychowdhary.ieeensit.R;

public class errorid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_errorid);
        Button b=(Button)findViewById(R.id.applybutton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText a,q;
                a=(EditText)findViewById(R.id.name_apply);
                q=(EditText)findViewById(R.id.Phonenumber);
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, "ieeensit2016@gmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "REQUEST FOR MEMBERSHIP");
                intent.putExtra(Intent.EXTRA_TEXT, a.getText().toString()+"    "+q.getText().toString());
                startActivity(Intent.createChooser(intent, "Send Email"));
                Toast.makeText(getApplicationContext(),"A REQUEST HAS BEEN SENT TO THE BRANCH",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
