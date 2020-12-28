package com.example.appmci.analysis;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_analysis, container, false);




        radar_data.add(new RadarEntry(4));
        radar_data.add(new RadarEntry(7));
        radar_data.add(new RadarEntry(8));
        radar_data.add(new RadarEntry(5));
        radar_data.add(new RadarEntry(9));

        radar_model.add(new RadarEntry(5));
        radar_model.add(new RadarEntry(4));
        radar_model.add(new RadarEntry(9));
        radar_model.add(new RadarEntry(3));
        radar_model.add(new RadarEntry(7));


//        radar_report = view.findViewById(R.id.analysis_line_chart);

//        draw_radarChart(radar_data,radar_model);
//        draw_radarChart(radar_model);


        return view;
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