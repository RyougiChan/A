package com.shetuan.nav_tab;

import java.util.ArrayList;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager; 
import android.view.View;

public class TabPagerAdapter extends PagerAdapter {

	private ArrayList<View>mViews;
	public TabPagerAdapter(ArrayList<View> views) {
		// TODO Auto-generated constructor stub
		this.mViews = views;
	}
	
	@Override
	public void destroyItem(View container, int position, Object object) {
		// TODO Auto-generated method stub
		((ViewPager)container).removeView(mViews.get(position));
	}
	
	@Override
	public Object instantiateItem(View container, int position) {
		// TODO Auto-generated method stub
		 ((ViewPager)container).addView(mViews.get(position), 0);  
         return mViews.get(position);	
         }
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mViews.size();
	}

	@Override
	public boolean isViewFromObject(View mView, Object mObject) {
		// TODO Auto-generated method stub
		return (mView==mObject); 
	}

}
