package com.example.sudhar.userinterface;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class homeactivity extends FragmentActivity {
    ViewPager view;

    PageIndicator mIndicator;
    SharedPreferences pref;
    final String welcome="Welcome";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        Boolean Change= pref.getBoolean(welcome,true);
        if(Change)
        {
            SharedPreferences.Editor editor=pref.edit();
            editor.putBoolean(welcome,false);
            editor.commit();
        }
        else
        {
            Intent intent= new Intent(this,SMD.class);
            startActivity(intent);
        }


        view = (ViewPager) findViewById(R.id.pager0);


        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        view.setAdapter(adapter);

        mIndicator = (UnderlinePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(view);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_homeactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
