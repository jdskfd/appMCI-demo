package com.example.appmci;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.appmci.bluetoothlegatt.Setting_Bluetooth;

public class FragmentSettings extends Fragment {

    Button b1,b2,b3,b4,b5;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_settings, container, false);


        b1 = view.findViewById(R.id.f5_btn1);
        b2 = view.findViewById(R.id.f5_btn2);
        b3 = view.findViewById(R.id.f5_btn3);
        b4 = view.findViewById(R.id.f5_btn4);
        b5 = view.findViewById(R.id.f5_btn5);

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Setting_Bluetooth setting_bluetooth = new Setting_Bluetooth();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentSetting,setting_bluetooth,"backToSetting");
                transaction.addToBackStack(null);


                transaction.commit();
            }
        });


        return view;
    }
}
