package com.example.appmci;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.appmci.analysis.FragmentAnalysis;
import com.example.appmci.analysis.InsertChartData;
import com.example.appmci.database.ConnectionClass;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.renderer.BarChartRenderer;
import com.github.mikephil.charting.utils.ViewPortHandler;

import android.annotation.SuppressLint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;


import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;



public class FragmentHealth extends Fragment {
    private static final String TAG = "FragmentHealth";
    ArrayList<Entry> values = new ArrayList<>();
    ArrayList<BarEntry> step_day = new ArrayList<>();
    ArrayList<PieEntry> sleep_ratio = new ArrayList<>();
    ArrayList<BarEntry> step_week = new ArrayList<>();
    ArrayList<Entry> hr_day;
    ArrayList<BarEntry> steps_day;
    ArrayList<Entry> hr_week;
    ArrayList<BarEntry> steps_week;
    ArrayList<Entry> hr_month;
    ArrayList<BarEntry> steps_month;
    ArrayList<PieEntry> sleep_day = new ArrayList<>();
    ArrayList<PieEntry> sleep_week = new ArrayList<>();
    ArrayList<PieEntry> sleep_month = new ArrayList<>();
    LineChart hr_report;
    BarChart step_report;
    PieChart sleep_report;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_health, container, false);

        Button btnDay;
        Button btnWeek;
        Button btnMonth;
        Button btnAnalysis;

        MyDBHelper myDBHelper = new MyDBHelper(getContext(),"mciSQLite.db",null,1);

//        steps_week = myDBHelper.aryList_steps_week();
        hr_day = myDBHelper.aryList_hr_day();
        steps_day = myDBHelper.aryList_steps_day();
        hr_week = myDBHelper.aryList_hr_week();
        steps_week = myDBHelper.aryList_steps_week();
        hr_month = myDBHelper.aryList_hr_month();
        steps_month = myDBHelper.aryList_steps_month();
//        values.add(new Entry(1,	80));

//        step_day.add(new BarEntry(1, 12));

//        hr_week.add(new BarEntry(1, 22));
//        step_week.add(new BarEntry(1, 7206));
//        sleep_day.add(new PieEntry(105, 1));
//        sleep_week.add(new PieEntry(211, 1));
//        sleep_month.add(new PieEntry(223, 1));
        hr_report = view.findViewById(R.id.line_chart);
        step_report = view.findViewById(R.id.bar_chart);
        sleep_report = view.findViewById(R.id.pie_chart);
//        hr_week_report = view.findViewById(R.id.line_chart);
        draw_hr_lineChart(hr_day);
        draw_step_barChart(steps_day);
        draw_sleep_pieChart(sleep_day);
        btnDay = view.findViewById(R.id.day_btn);
        btnWeek = view.findViewById(R.id.week_btn);
        btnMonth = view.findViewById(R.id.month_btn);
        btnAnalysis = view.findViewById(R.id.analysis_report_btn);

        btnDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView titleHR = getActivity().findViewById(R.id.titleHR);
                TextView titleSteps = getActivity().findViewById(R.id.titleSteps);
                titleHR.setText("昨日心率起伏圖表");
                titleSteps.setText("昨日活動量圖表");

                TextView step_target = getActivity().findViewById(R.id.step_target_done);
                TextView sleep_status = getActivity().findViewById(R.id.sleep_status);
                step_target.setText("昨日夜間步數:105 \n上週夜間平均步數:214 \n退步96.7%");
                sleep_status.setText("昨日活動量:7206 \n上週活動量:7274 \n退步0.01%");
                draw_hr_lineChart(hr_day);
                draw_step_barChart(steps_day);
                draw_sleep_pieChart(sleep_day);
            }
        });


        btnWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView titleHR = getActivity().findViewById(R.id.titleHR);
                TextView titleSteps = getActivity().findViewById(R.id.titleSteps);
                titleHR.setText("本周異常心率次數圖表");
                titleSteps.setText("本周活動量圖表");

                TextView step_target = getActivity().findViewById(R.id.step_target_done);
                TextView sleep_status = getActivity().findViewById(R.id.sleep_status);
                step_target.setText("本週平均活動量:8416 \n上週活動量:7645 \n進步10.1%");
                sleep_status.setText("本週夜間步數:421 \n上週夜間離床:214 \n退步50%");
                draw_hr_lineChart(hr_week);
                draw_step_barChart(steps_week);
                draw_sleep_pieChart(sleep_week);
            }
        });

        btnMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView titleHR = getActivity().findViewById(R.id.titleHR);
                TextView titleSteps = getActivity().findViewById(R.id.titleSteps);
                titleHR.setText("本月異常心率次數圖表");
                titleSteps.setText("本月活動量圖表");

                TextView step_target = getActivity().findViewById(R.id.step_target_done);
                TextView sleep_status = getActivity().findViewById(R.id.sleep_status);
                step_target.setText("本月活動量:6117 \n上月活動量:7274 \n退步1.18%");
                sleep_status.setText("本月夜間平均步數:223 \n上月夜間離床量:223");
                draw_hr_lineChart(hr_month);
                draw_step_barChart(steps_month);
                draw_sleep_pieChart(sleep_month);
            }
        });

        btnAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FragmentAnalysis fragmentAnalysis = new FragmentAnalysis();
                if (fragmentAnalysis == null) fragmentAnalysis = new FragmentAnalysis();

                if (fragmentAnalysis.isAdded()){
                    fragmentTransaction.show(fragmentAnalysis);
                } else {
                    fragmentTransaction.add(R.id.fragment_container,fragmentAnalysis,"fragment_analysis");
                }
                fragmentTransaction.addToBackStack("fragment_health");
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

    private void draw_hr_lineChart(final ArrayList<Entry> db_data) {
        final LineDataSet set, set1, set_end, set1_end;
        set = new LineDataSet(db_data, "心率");
        set.setMode(LineDataSet.Mode.LINEAR);
        set.setColor(getResources().getColor(R.color.lightWhiteBlue));
        set.setLineWidth(1.0f);
        set.setDrawCircles(false);
        set.setDrawValues(false);
        LineData data = new LineData(set);
        XAxis xAxis = hr_report.getXAxis();
        xAxis.setEnabled(false);
        hr_report.getAxisLeft().setTextColor(Color.LTGRAY);
        hr_report.getAxisRight().setTextColor(Color.LTGRAY);
        hr_report.getLegend().setTextColor(Color.LTGRAY);
        hr_report.getDescription().setEnabled(false);
        hr_report.setData(data);//一定要放在最後
        hr_report.animateY(2000);
        hr_report.invalidate();//繪製圖表
    }
    private void draw_step_barChart(final ArrayList<BarEntry> db_data) {
        final BarDataSet barDataSet;

        barDataSet = new BarDataSet(db_data,"步數單位(步)");
        barDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
//        barDataSet.setValueTextColor(Color.BLACK);
//        barDataSet.setValueTextSize(5f);
        BarData data = new BarData(barDataSet);
        XAxis xAxis = step_report.getXAxis();
        xAxis.setEnabled(false);
        step_report.getAxisLeft().setTextColor(Color.LTGRAY);
        step_report.getAxisRight().setTextColor(Color.LTGRAY);
        step_report.getLegend().setTextColor(Color.LTGRAY);
        step_report.getDescription().setEnabled(false);
        step_report.setFitBars(true);
        step_report.setData(data);
        step_report.getDescription().setText("Bar");
        step_report.animateY(2000);
        step_report.invalidate();
    }
    private void draw_sleep_pieChart(final ArrayList<PieEntry> db_data) {
        final PieDataSet pieDataSet;
        pieDataSet = new PieDataSet(db_data,"夜間離床步數比率");
        pieDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        PieData data = new PieData(pieDataSet);
        sleep_report.getLegend().setTextColor(Color.LTGRAY);
        sleep_report.getDescription().setEnabled(false);
        sleep_report.setData(data);
        sleep_report.getDescription().setEnabled(false);
        sleep_report.setCenterText("");
        sleep_report.animateY(2000);
        sleep_report.invalidate();
    }

}
