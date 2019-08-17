package com.example.sudhar.userinterface;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Sudhar on 19-12-2015.
 */
public class PagerAdapter extends FragmentPagerAdapter {
    ViewGroup First,second,Third;
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch(i)
        {

            case 0:
                return new Fragmentone();

            case 1:
                return new Fragmenttwo();

            case 2:
                return new Fragmentthree();

            default:
                break;
        }

return null;


    }

    @Override
    public int getCount() {
        return 3;
    }



    public static class Fragmentone extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View layout1 = inflater.inflate(R.layout.fragment_one, container, false);
           /* View text = getActivity().findViewById(R.id.firstpage);
            Animation animation1 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide);
            text.startAnimation(animation1);*/
            return layout1;

        }



    }
    public static class Fragmenttwo extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View layout1 = inflater.inflate(R.layout.fragemnt_two, container, false);


            return layout1;


        }
    }
    public static class Fragmentthree extends Fragment implements View.OnClickListener {
        Button button;
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View layout1 = inflater.inflate(R.layout.fragment_three, container, false);


            button=(Button) layout1.findViewById(R.id.Movetohome);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(),Help.class);
                    startActivity(intent);


                }
            });
            return layout1;
        }

        @Override
        public void onClick(View v) {



        }
    }
}
