package com.example.ajaychowdhary.ieeensit;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MyPageAdapter extends FragmentPagerAdapter {
	List<Fragment> fragmentList;
	
	public MyPageAdapter(FragmentManager fm, List<Fragment> fragmentList){
		super(fm);
		this.fragmentList=fragmentList;
	}
	@Override
	public Fragment getItem(int position)
	{
		return fragmentList.get(position);	}
	public int getCount()
	{
	
	return fragmentList.size();}

}



