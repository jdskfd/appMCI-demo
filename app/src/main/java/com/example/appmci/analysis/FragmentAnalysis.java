package com.example.appmci.analysis;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.appmci.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;

public class FragmentAnalysis extends Fragment {

    ArrayList<RadarEntry> radar_data = new ArrayList<>();
    ArrayList<RadarEntry> radar_model = new ArrayList<>();


    RadarChart radar_report;
    View btnBoard1;
    View btnBoard2;
    View btnBoard3;
    View btnBoard4;
    View btnBoard5;
    View btnBoard6;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_analysis, container, false);




//        radar_data.add(new RadarEntry(4));
//        radar_data.add(new RadarEntry(7));
//        radar_data.add(new RadarEntry(8));
//        radar_data.add(new RadarEntry(5));
//        radar_data.add(new RadarEntry(9));
//
//        radar_model.add(new RadarEntry(5));
//        radar_model.add(new RadarEntry(4));
//        radar_model.add(new RadarEntry(9));
//        radar_model.add(new RadarEntry(3));
//        radar_model.add(new RadarEntry(7));


//        radar_report = view.findViewById(R.id.analysis_line_chart);

//        draw_radarChart(radar_data,radar_model);
//        draw_radarChart(radar_model);

        //board1
        btnBoard1 = (View)view.findViewById(R.id.ana_board1);
        btnBoard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FragmentBoard1 fragmentBoard1 = new FragmentBoard1();
                if (fragmentBoard1 == null) fragmentBoard1 = new FragmentBoard1();

                if (fragmentBoard1.isAdded()){
                    fragmentTransaction.show(fragmentBoard1);
                } else {
                    fragmentTransaction.add(R.id.fragment_container,fragmentBoard1,"fragment_board");

                }
                fragmentTransaction.addToBackStack("fragment_analysis");
                fragmentTransaction.commit();
            }
        });

        //board2
        btnBoard2 = (View)view.findViewById(R.id.ana_board2);
        btnBoard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FragmentBoard2 fragmentBoard2 = new FragmentBoard2();
                if (fragmentBoard2 == null) fragmentBoard2 = new FragmentBoard2();

                if (fragmentBoard2.isAdded()){
                    fragmentTransaction.show(fragmentBoard2);
                } else {
                    fragmentTransaction.add(R.id.fragment_container,fragmentBoard2,"fragment_board");

                }
                fragmentTransaction.addToBackStack("fragment_analysis");
                fragmentTransaction.commit();
            }
        });

        //board3
        btnBoard3 = (View)view.findViewById(R.id.ana_board3);
        btnBoard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FragmentBoard3 fragmentBoard3 = new FragmentBoard3();
                if (fragmentBoard3 == null) fragmentBoard3 = new FragmentBoard3();

                if (fragmentBoard3.isAdded()){
                    fragmentTransaction.show(fragmentBoard3);
                } else {
                    fragmentTransaction.add(R.id.fragment_container,fragmentBoard3,"fragment_board");

                }
                fragmentTransaction.addToBackStack("fragment_analysis");
                fragmentTransaction.commit();
            }
        });

        //board4
        btnBoard4 = (View)view.findViewById(R.id.ana_board4);
        btnBoard4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FragmentBoard4 fragmentBoard4 = new FragmentBoard4();
                if (fragmentBoard4 == null) fragmentBoard4 = new FragmentBoard4();

                if (fragmentBoard4.isAdded()){
                    fragmentTransaction.show(fragmentBoard4);
                } else {
                    fragmentTransaction.add(R.id.fragment_container,fragmentBoard4,"fragment_board");

                }
                fragmentTransaction.addToBackStack("fragment_analysis");
                fragmentTransaction.commit();
            }
        });

        //board5
        btnBoard5 = (View)view.findViewById(R.id.ana_board5);
        btnBoard5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FragmentBoard5 fragmentBoard5 = new FragmentBoard5();
                if (fragmentBoard5 == null) fragmentBoard5 = new FragmentBoard5();

                if (fragmentBoard5.isAdded()){
                    fragmentTransaction.show(fragmentBoard5);
                } else {
                    fragmentTransaction.add(R.id.fragment_container,fragmentBoard5,"fragment_board");

                }
                fragmentTransaction.addToBackStack("fragment_analysis");
                fragmentTransaction.commit();
            }
        });

        //board6
        btnBoard6 = (View)view.findViewById(R.id.ana_board6);
        btnBoard6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FragmentBoard6 fragmentBoard6 = new FragmentBoard6();
                if (fragmentBoard6 == null) fragmentBoard6 = new FragmentBoard6();

                if (fragmentBoard6.isAdded()){
                    fragmentTransaction.show(fragmentBoard6);
                } else {
                    fragmentTransaction.add(R.id.fragment_container,fragmentBoard6,"fragment_board");

                }
                fragmentTransaction.addToBackStack("fragment_analysis");
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

    private void draw_radarChart(final ArrayList<RadarEntry> data1,final ArrayList<RadarEntry> data2) {


        Paint mainPaint;
        // Initialize the radar zone brush
        mainPaint = new Paint();
        mainPaint.setAntiAlias(true);
        mainPaint.setColor(Color.parseColor("#6BD18E"));
        mainPaint.setStyle(Paint.Style.STROKE);


        final RadarDataSet set1,set2;
        // greenLine
        set1 = new RadarDataSet(data1, "近期狀況");//另一個是常摸
        set2 = new RadarDataSet(data2, "常摸分析");//另一個是常摸



        set1.setFillColor(Color.rgb(42, 114, 142));
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);
        set1.setColor(getResources().getColor(R.color.darkBlueLine));//線的顏色
        set1.setLineWidth(1.5f);//線寬
        set1.setDrawValues(false);//不顯示座標點對應Y軸的數字(預設顯示)



        set2.setFillColor(Color.rgb(42, 114, 142));
        set2.setDrawFilled(true);
        set2.setFillAlpha(100);
        set2.setDrawHighlightCircleEnabled(true);
        set2.setDrawHighlightIndicators(false);
        set2.setColor(getResources().getColor(R.color.darkBlueLine));//線的顏色
        set2.setLineWidth(1.5f);//線寬
        set2.setDrawValues(false);//不顯示座標點對應Y軸的數字(預設顯示)


        String[] labels = {"心率異常次數","心率平均","臥姿比率","夜間步數","日間步數"};
        XAxis xAxis = radar_report.getXAxis();
//        xAxis.setTypeface(tfLight);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));


        YAxis yAxis = radar_report.getYAxis();
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum(10);
        yAxis.setDrawLabels(false);

//        ArrayList<IRadarDataSet> sets = new ArrayList<>();
//        sets.add(set1);
//        sets.add(set2);


        RadarData radarData = new RadarData();
        radarData.addDataSet(set1);
        radarData.addDataSet(set2);

        radar_report.getDescription().setEnabled(false);
        radar_report.setData(radarData);//一定要放在最後
        radar_report.animateY(2000, Easing.EaseInOutQuad);
        radar_report.invalidate();//繪製圖表

    }



}