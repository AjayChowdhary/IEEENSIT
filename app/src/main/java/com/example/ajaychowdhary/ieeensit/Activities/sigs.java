package com.example.ajaychowdhary.ieeensit.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ajaychowdhary.ieeensit.Adapters.MyPageAdapter;
import com.example.ajaychowdhary.ieeensit.R;

import java.util.List;

public class sigs extends AppCompatActivity {

    ViewPager viewPager;

    List<Fragment> fragmentList;

    MyPageAdapter myPageAdapter;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp);
/*
        setContentView(R.layout.activity_sigs);

        viewPager = (ViewPager) findViewById(R.id.myViewPager);

        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new Electronics());
        fragmentList.add(new AndroidSIG());

        fragmentManager = getSupportFragmentManager();

        myPageAdapter = new MyPageAdapter(fragmentManager, fragmentList);

        viewPager.setAdapter(myPageAdapter);
*/
    }

}
