package com.example.appmci.book;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appmci.R;

public class CommonSymptom extends Fragment {
    public CommonSymptom() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_common_symptom, container, false);

        return view;
    }
}