package com.example.appmci;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.appmci.analysis.FragmentAnalysis;
import com.example.appmci.book.ActivityBook;
import com.example.appmci.book.CareAdvice;
import com.example.appmci.book.CommonProblem;
import com.example.appmci.book.CommonSymptom;
import com.example.appmci.book.ExerciseAdvice;
import com.example.appmci.book.HealthHr;
import com.example.appmci.book.MciIntro;
import com.example.appmci.book.Train;
import com.example.appmci.todoFunction.Eating;

public class FragmentBook extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        Button mciIntro = view.findViewById(R.id.mciIntro);
        Button commonSymptom = view.findViewById(R.id.commonSymptom);
        Button foodAdvice = view.findViewById(R.id.foodAdvice);
        Button exerciseAdvice = view.findViewById(R.id.exerciseAdvice);
        Button careAdvice = view.findViewById(R.id.careAdvice);
        Button activity = view.findViewById(R.id.activity);
        Button healthHr = view.findViewById(R.id.healthHr);
        Button train = view.findViewById(R.id.train);
        Button commonProblem = view.findViewById(R.id.commonProblem);

        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        mciIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MciIntro mciIntro = new MciIntro();
                fragmentTransaction.replace(R.id.fragment_container,mciIntro,"mciIntro");
                fragmentTransaction.commit();
            }
        });
        commonSymptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonSymptom commonSymptom = new CommonSymptom();
                fragmentTransaction.replace(R.id.fragment_container,commonSymptom,"commonSymptom");
                fragmentTransaction.commit();
            }
        });
        exerciseAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExerciseAdvice exerciseAdvice = new ExerciseAdvice();
                fragmentTransaction.replace(R.id.fragment_container,exerciseAdvice,"exerciseAdvice");
                fragmentTransaction.commit();
            }
        });
        careAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CareAdvice careAdvice = new CareAdvice();
                fragmentTransaction.replace(R.id.fragment_container,careAdvice,"careAdvice");
                fragmentTransaction.commit();
            }
        });
        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityBook activityBook = new ActivityBook();
                fragmentTransaction.replace(R.id.fragment_container,activityBook,"activityBook");
                fragmentTransaction.commit();
            }
        });
        healthHr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HealthHr healthHr = new HealthHr();
                fragmentTransaction.replace(R.id.fragment_container,healthHr,"healthHr");
                fragmentTransaction.commit();
            }
        });
       train.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Train train = new Train();
               fragmentTransaction.replace(R.id.fragment_container,train,"train");
               fragmentTransaction.commit();
           }
       });
        commonProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonProblem commonProblem = new CommonProblem();
                fragmentTransaction.replace(R.id.fragment_container,commonProblem,"commonProblem");
                fragmentTransaction.commit();
            }
        });
        foodAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eating eating = new Eating();
                fragmentTransaction.replace(R.id.fragment_container,eating,"eating");
                fragmentTransaction.commit();
            }
        });

        return view;
    }
    @Override
    public void onPause(){
        FragmentManager fragmentManager = getFragmentManager();
        int count = getFragmentManager().getBackStackEntryCount();
        for (int i = 0 ; i < count ; i++){
            fragmentManager.popBackStack();
        }
        super.onPause();
    }

}

