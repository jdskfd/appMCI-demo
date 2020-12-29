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
    public FragmentBook() {
        // Requires empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        Button mciIntro = view.findViewById(R.id.mciIntro);
        Button commonSymptom = view.findViewById(R.id.commonSymptom);
        Button foodAdvice = view.findViewById(R.id.foodAdvice);
        Button exerciseAdvice = view.findViewById(R.id.exerciseAdvice);
        Button careAdvice = view.findViewById(R.id.careAdvice);
//        Button activity = view.findViewById(R.id.activity);
        Button healthHr = view.findViewById(R.id.healthHr);
//        Button train = view.findViewById(R.id.train);
//        Button commonProblem = view.findViewById(R.id.commonProblem);







        mciIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FragmentManager fragmentManager = getFragmentManager();
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                final FragmentBook fragmentBook = (FragmentBook)
                        fragmentManager.findFragmentByTag("fragmentBook");
                if (fragmentBook != null && !fragmentBook.isHidden()) {
                    fragmentTransaction.hide(fragmentBook);
                    fragmentTransaction.addToBackStack("fragmentBook");
                }
                final MciIntro mciIntro = new MciIntro();
                if (mciIntro.isAdded()) {
                    fragmentTransaction.show(mciIntro);
                } else {
                    fragmentTransaction.add(R.id.fragment_container, mciIntro, "mciIntro");
                }
                fragmentTransaction.commit();
            }
        });
        commonSymptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FragmentManager fragmentManager = getFragmentManager();
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                final FragmentBook fragmentBook = (FragmentBook)
                        fragmentManager.findFragmentByTag("fragmentBook");
                if (fragmentBook != null && !fragmentBook.isHidden()) {
                    fragmentTransaction.hide(fragmentBook);
                    fragmentTransaction.addToBackStack("fragmentBook");
                }
                final CommonSymptom commonSymptom = new CommonSymptom();
                if (commonSymptom.isAdded()) {
                    fragmentTransaction.show(commonSymptom);
                } else {
                    fragmentTransaction.add(R.id.fragment_container, commonSymptom, "commonSymptom");
                }
                fragmentTransaction.commit();
            }
        });
        foodAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FragmentManager fragmentManager = getFragmentManager();
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                final FragmentBook fragmentBook = (FragmentBook)
                        fragmentManager.findFragmentByTag("fragmentBook");
                if (fragmentBook != null && !fragmentBook.isHidden()) {
                    fragmentTransaction.hide(fragmentBook);
                    fragmentTransaction.addToBackStack("fragmentBook");
                }
                final Eating eating = new Eating();
                if (eating.isAdded()) { // 如果 home fragment 已經被 add 過，
                    fragmentTransaction.show(eating); // 顯示它。
                } else { // 反之，
                    fragmentTransaction.add(R.id.fragment_container, eating, "eating"); // 使用 add 方法。
                }
                fragmentTransaction.commit();
            }
        });
        exerciseAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FragmentManager fragmentManager = getFragmentManager();
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                final FragmentBook fragmentBook = (FragmentBook)
                        fragmentManager.findFragmentByTag("fragmentBook");
                if (fragmentBook != null && !fragmentBook.isHidden()) {
                    fragmentTransaction.hide(fragmentBook);
                    fragmentTransaction.addToBackStack("fragmentBook");
                }
                final ExerciseAdvice exerciseAdvice = new ExerciseAdvice();
                if (exerciseAdvice.isAdded()) {
                    fragmentTransaction.show(exerciseAdvice);
                } else {
                    fragmentTransaction.add(R.id.fragment_container, exerciseAdvice, "exerciseAdvice");
                }
                fragmentTransaction.commit();
            }
        });
        careAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FragmentManager fragmentManager = getFragmentManager();
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                final FragmentBook fragmentBook = (FragmentBook)
                        fragmentManager.findFragmentByTag("fragmentBook");
                if (fragmentBook != null && !fragmentBook.isHidden()) {
                    fragmentTransaction.hide(fragmentBook);
                    fragmentTransaction.addToBackStack("fragmentBook");
                }
                CareAdvice careAdvice = new CareAdvice();
                if (careAdvice.isAdded()) {
                    fragmentTransaction.show(careAdvice);
                } else {
                    fragmentTransaction.add(R.id.fragment_container, careAdvice, "careAdvice");
                }
                fragmentTransaction.commit();
            }
        });
//        activity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final FragmentManager fragmentManager = getFragmentManager();
//                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                final FragmentBook fragmentBook = (FragmentBook)
//                        fragmentManager.findFragmentByTag("fragmentBook");
//                if (fragmentBook != null && !fragmentBook.isHidden()) {
//                    fragmentTransaction.hide(fragmentBook);
//                    fragmentTransaction.addToBackStack("fragmentBook");
//                }
//                final ActivityBook activityBook = new ActivityBook();
//                if (activityBook.isAdded()) {
//                    fragmentTransaction.show(activityBook);
//                } else {
//                    fragmentTransaction.add(R.id.fragment_container, activityBook, "activityBook");
//                }
//                fragmentTransaction.commit();
//            }
//        });
        healthHr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FragmentManager fragmentManager = getFragmentManager();
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                final FragmentBook fragmentBook = (FragmentBook)
                        fragmentManager.findFragmentByTag("fragmentBook");
                if (fragmentBook != null && !fragmentBook.isHidden()) {
                    fragmentTransaction.hide(fragmentBook);
                    fragmentTransaction.addToBackStack("fragmentBook");
                }
                final HealthHr healthHr = new HealthHr();
                if (healthHr.isAdded()) {
                    fragmentTransaction.show(healthHr);
                } else {
                    fragmentTransaction.add(R.id.fragment_container, healthHr, "healthHr");
                }
                fragmentTransaction.commit();
            }
        });
//       train.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//               final FragmentManager fragmentManager = getFragmentManager();
//               final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//               final FragmentBook fragmentBook = (FragmentBook)
//                       fragmentManager.findFragmentByTag("fragmentBook");
//               if (fragmentBook != null && !fragmentBook.isHidden()) {
//                   fragmentTransaction.hide(fragmentBook);
//                   fragmentTransaction.addToBackStack("fragmentBook");
//               }
//               final Train train = new Train();
//               if (train.isAdded()) {
//                   fragmentTransaction.show(train);
//               } else {
//                   fragmentTransaction.add(R.id.fragment_container, train, "train");
//               }
//               fragmentTransaction.commit();
//           }
//       });
//        commonProblem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final FragmentManager fragmentManager = getFragmentManager();
//                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                final FragmentBook fragmentBook = (FragmentBook)
//                        fragmentManager.findFragmentByTag("fragmentBook");
//                if (fragmentBook != null && !fragmentBook.isHidden()) {
//                    fragmentTransaction.hide(fragmentBook);
//                    fragmentTransaction.addToBackStack("fragmentBook");
//                }
//                final CommonProblem commonProblem = new CommonProblem();
//                if (commonProblem.isAdded()) {
//                    fragmentTransaction.show(commonProblem);
//                } else {
//                    fragmentTransaction.add(R.id.fragment_container, commonProblem, "commonProblem");
//                }
//                fragmentTransaction.commit();
//            }
//        });


        return view;
    }
    @Override
    public void onDestroy(){
        FragmentManager fragmentManager = getFragmentManager();
        int count = getFragmentManager().getBackStackEntryCount();
        for (int i = 0 ; i < count ; i++){
            fragmentManager.popBackStack();
        }
        super.onDestroy();
    }

}

