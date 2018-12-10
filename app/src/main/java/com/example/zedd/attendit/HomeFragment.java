package com.example.zedd.attendit;

import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HomeFragment extends Fragment {
    private static final long START_TIME_IN_MILLIS = 600000;

    private TextView myprof;
    private TextView mycourse;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);
        View addst;
        myprof=(TextView) view.findViewById(R.id.myprof);
        myprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent num;
                num = new Intent(getActivity(), teacherprofile.class);
                startActivity(num);
            }
        });
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }
    public void setTime(View v)
    {

    }
    public void onClickmyprof(View v)
    {
        Intent num;
        num = new Intent(getActivity(), teacherprofile.class);
        startActivity(num);
    }
}
