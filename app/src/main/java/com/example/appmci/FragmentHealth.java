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


    ArrayList<Entry> values = new ArrayList<>();
    ArrayList<Entry> hr_week = new ArrayList<>();
    ArrayList<BarEntry> step_day = new ArrayList<>();
    ArrayList<PieEntry> sleep_ratio = new ArrayList<>();
    ArrayList<BarEntry> step_week = new ArrayList<>();

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




        values.add(new Entry(1,	80));
        values.add(new Entry(2,	72));
        values.add(new Entry(3,	71));
        values.add(new Entry(4,	71));
        values.add(new Entry(5,	70));
        values.add(new Entry(6,	72));
        values.add(new Entry(7,	72));
        values.add(new Entry(8,	73));
        values.add(new Entry(9,	72));
        values.add(new Entry(10,72));
        values.add(new Entry(11,70));
        values.add(new Entry(12,71));
        values.add(new Entry(13,72));
        values.add(new Entry(14,75));
        values.add(new Entry(15,70));
        values.add(new Entry(16,72));
        values.add(new Entry(17,69));
        values.add(new Entry(18,71));
        values.add(new Entry(19,73));
        values.add(new Entry(20,101));
        values.add(new Entry(21,75));
        values.add(new Entry(22,72));
        values.add(new Entry(23,71));
        values.add(new Entry(24,70));
        values.add(new Entry(25,72));
        values.add(new Entry(26,68));
        values.add(new Entry(27,70));
        values.add(new Entry(28,71));
        values.add(new Entry(29,74));
        values.add(new Entry(30,69));
        values.add(new Entry(31,72));
        values.add(new Entry(32,72));
        values.add(new Entry(33,71));
        values.add(new Entry(34,72));
        values.add(new Entry(35,73));
        values.add(new Entry(36,72));
        values.add(new Entry(37,71));
        values.add(new Entry(38,67));
        values.add(new Entry(39,72));
        values.add(new Entry(40,72));
        values.add(new Entry(41,71));
        values.add(new Entry(42,70));
        values.add(new Entry(43,74));
        values.add(new Entry(44,76));
        values.add(new Entry(45,71));
        values.add(new Entry(46,73));
        values.add(new Entry(47,71));
        values.add(new Entry(48,70));
        values.add(new Entry(49,69));
        values.add(new Entry(50,72));
        values.add(new Entry(51,71));
        values.add(new Entry(52,72));
        values.add(new Entry(53,73));
        values.add(new Entry(54,71));
        values.add(new Entry(55,70));
        values.add(new Entry(56,107));
        values.add(new Entry(57,72));
        values.add(new Entry(58,68));
        values.add(new Entry(59,72));
        values.add(new Entry(60,67));
        values.add(new Entry(61,76));
        values.add(new Entry(62,73));
        values.add(new Entry(63,71));
        values.add(new Entry(64,72));
        values.add(new Entry(65,71));
        values.add(new Entry(66,70));
        values.add(new Entry(67,76));
        values.add(new Entry(68,72));
        values.add(new Entry(69,71));
        values.add(new Entry(70,70));
        values.add(new Entry(71,99));
        values.add(new Entry(72,73));
        values.add(new Entry(73,72));
        values.add(new Entry(74,72));
        values.add(new Entry(75,71));
        values.add(new Entry(76,69));
        values.add(new Entry(77,71));
        values.add(new Entry(78,95));
        values.add(new Entry(79,75));
        values.add(new Entry(80,68));
        values.add(new Entry(81,71));
        values.add(new Entry(82,74));
        values.add(new Entry(83,68));
        values.add(new Entry(84,72));
        values.add(new Entry(85,72));
        values.add(new Entry(86,71));
        values.add(new Entry(87,70));
        values.add(new Entry(88,69));
        values.add(new Entry(89,83));
        values.add(new Entry(90,71));
        values.add(new Entry(91,72));
        values.add(new Entry(92,69));
        values.add(new Entry(93,115));
        values.add(new Entry(94,72));
        values.add(new Entry(95,73));
        values.add(new Entry(96,111));
        values.add(new Entry(97,72));
        values.add(new Entry(98,71));
        values.add(new Entry(99	,95));
        values.add(new Entry(100,72));
        values.add(new Entry(101,100));
        values.add(new Entry(102,81));
        values.add(new Entry(103,87));
        values.add(new Entry(104,71));
        values.add(new Entry(105,101));
        values.add(new Entry(106,87));
        values.add(new Entry(107,81));
        values.add(new Entry(108,76));
        values.add(new Entry(109,89));
        values.add(new Entry(110,72));
        values.add(new Entry(111,81));
        values.add(new Entry(112,72));
        values.add(new Entry(113,117));
        values.add(new Entry(114,92));
        values.add(new Entry(115,81));
        values.add(new Entry(116,77));
        values.add(new Entry(117,79));
        values.add(new Entry(118,91));
        values.add(new Entry(119,82));
        values.add(new Entry(120,75));
        values.add(new Entry(121,79));
        values.add(new Entry(122,85));
        values.add(new Entry(123,91));
        values.add(new Entry(124,87));
        values.add(new Entry(125,81));
        values.add(new Entry(126,91));
        values.add(new Entry(127,78));
        values.add(new Entry(128,83));
        values.add(new Entry(129,81));
        values.add(new Entry(130,92));
        values.add(new Entry(131,107));
        values.add(new Entry(132,89));
        values.add(new Entry(133,91));
        values.add(new Entry(134,77));
        values.add(new Entry(135,81));
        values.add(new Entry(136,95));
        values.add(new Entry(137,84));
        values.add(new Entry(138,81));
        values.add(new Entry(139,72));
        values.add(new Entry(140,78));
        values.add(new Entry(141,83));
        values.add(new Entry(142,91));
        values.add(new Entry(143,83));
        values.add(new Entry(144,76));
        values.add(new Entry(145,89));
        values.add(new Entry(146,85));
        values.add(new Entry(147,91));
        values.add(new Entry(148,82));
        values.add(new Entry(149,95));
        values.add(new Entry(150,112));
        values.add(new Entry(151,98));
        values.add(new Entry(152,91));
        values.add(new Entry(153,87));
        values.add(new Entry(154,79));
        values.add(new Entry(155,89));
        values.add(new Entry(156,121));
        values.add(new Entry(157,78));
        values.add(new Entry(158,91));
        values.add(new Entry(159,81));
        values.add(new Entry(160,87));
        values.add(new Entry(161,89));
        values.add(new Entry(162,96));
        values.add(new Entry(163,97));
        values.add(new Entry(164,92));
        values.add(new Entry(165,89));
        values.add(new Entry(166,87));
        values.add(new Entry(167,84));
        values.add(new Entry(168,106));
        values.add(new Entry(169,79));
        values.add(new Entry(170,66));
        values.add(new Entry(171,72));
        values.add(new Entry(172,72));
        values.add(new Entry(173,72));
        values.add(new Entry(174,101));
        values.add(new Entry(175,89));
        values.add(new Entry(176,91));
        values.add(new Entry(177,96));
        values.add(new Entry(178,89));
        values.add(new Entry(179,82));
        values.add(new Entry(180,73));
        values.add(new Entry(181,76));
        values.add(new Entry(182,79));
        values.add(new Entry(183,81));
        values.add(new Entry(184,73));
        values.add(new Entry(185,86));
        values.add(new Entry(186,91));
        values.add(new Entry(187,85));
        values.add(new Entry(188,92));
        values.add(new Entry(189,114));
        values.add(new Entry(190,81));
        values.add(new Entry(191,79));
        values.add(new Entry(192,91));
        values.add(new Entry(193,84));
        values.add(new Entry(194,92));
        values.add(new Entry(195,95));
        values.add(new Entry(196,88));
        values.add(new Entry(197,81));
        values.add(new Entry(198,92));
        values.add(new Entry(199,97));
        values.add(new Entry(200,95));
        values.add(new Entry(201,91));
        values.add(new Entry(202,86));
        values.add(new Entry(203,93));
        values.add(new Entry(204,79));
        values.add(new Entry(205,91));
        values.add(new Entry(206,94));
        values.add(new Entry(207,89));
        values.add(new Entry(208,83));
        values.add(new Entry(209,74));
        values.add(new Entry(210,85));
        values.add(new Entry(211,91));
        values.add(new Entry(212,79));
        values.add(new Entry(213,81));
        values.add(new Entry(214,89));
        values.add(new Entry(215,93));
        values.add(new Entry(216,99));
        values.add(new Entry(217,106));
        values.add(new Entry(218,79));
        values.add(new Entry(219,81));
        values.add(new Entry(220,89));
        values.add(new Entry(221,79));
        values.add(new Entry(222,93));
        values.add(new Entry(223,91));
        values.add(new Entry(224,89));
        values.add(new Entry(225,93));
        values.add(new Entry(226,84));
        values.add(new Entry(227,91));
        values.add(new Entry(228,99));
        values.add(new Entry(229,115));
        values.add(new Entry(230,121));
        values.add(new Entry(231,81));
        values.add(new Entry(232,79));
        values.add(new Entry(233,78));
        values.add(new Entry(234,83));
        values.add(new Entry(235,91));
        values.add(new Entry(236,83));
        values.add(new Entry(237,93));
        values.add(new Entry(238,85));
        values.add(new Entry(239,84));
        values.add(new Entry(240,82));
        values.add(new Entry(241,86));
        values.add(new Entry(242,89));
        values.add(new Entry(243,83));
        values.add(new Entry(244,81));
        values.add(new Entry(245,84));
        values.add(new Entry(246,94));
        values.add(new Entry(247,89));
        values.add(new Entry(248,96));
        values.add(new Entry(249,98));
        values.add(new Entry(250,107));
        values.add(new Entry(251,87));
        values.add(new Entry(252,81));
        values.add(new Entry(253,112));
        values.add(new Entry(254,117));
        values.add(new Entry(255,83));
        values.add(new Entry(256,91));
        values.add(new Entry(257,84));
        values.add(new Entry(258,115));
        values.add(new Entry(259,87));
        values.add(new Entry(260,117));
        values.add(new Entry(261,89));
        values.add(new Entry(262,109));
        values.add(new Entry(263,73));
        values.add(new Entry(264,92));
        values.add(new Entry(265,81));
        values.add(new Entry(266,118));
        values.add(new Entry(267,101));
        values.add(new Entry(268,117));
        values.add(new Entry(269,101));
        values.add(new Entry(270,69));
        values.add(new Entry(271,72));
        values.add(new Entry(272,84));
        values.add(new Entry(273,102));
        values.add(new Entry(274,71));
        values.add(new Entry(275,112));
        values.add(new Entry(276,101));
        values.add(new Entry(277,92));
        values.add(new Entry(278,78));
        values.add(new Entry(279,119));
        values.add(new Entry(280,83));
        values.add(new Entry(281,81));
        values.add(new Entry(282,92));
        values.add(new Entry(283,108));
        values.add(new Entry(284,79));
        values.add(new Entry(285,81));
        values.add(new Entry(286,80));
        values.add(new Entry(287,88));
        values.add(new Entry(288,77));
        values.add(new Entry(289,81));


        step_day.add(new BarEntry(1, 12));
        step_day.add(new BarEntry(2, 14));
        step_day.add(new BarEntry(3, 15));
        step_day.add(new BarEntry(4, 15));
        step_day.add(new BarEntry(5, 18));
        step_day.add(new BarEntry(6, 21));
        step_day.add(new BarEntry(7, 23));
        step_day.add(new BarEntry(8, 23));
        step_day.add(new BarEntry(9, 25));
        step_day.add(new BarEntry(10, 323));
        step_day.add(new BarEntry(11, 774));
        step_day.add(new BarEntry(12, 2142));
        step_day.add(new BarEntry(13, 2471));
        step_day.add(new BarEntry(14, 2732));
        step_day.add(new BarEntry(15, 3451));
        step_day.add(new BarEntry(16, 4896));
        step_day.add(new BarEntry(17, 5218));
        step_day.add(new BarEntry(18, 5971));
        step_day.add(new BarEntry(19, 6173));
        step_day.add(new BarEntry(20, 6517));
        step_day.add(new BarEntry(21, 6971));
        step_day.add(new BarEntry(22, 7031));
        step_day.add(new BarEntry(23, 7129));
        step_day.add(new BarEntry(24, 7206));

        hr_week.add(new BarEntry(1, 22));
        hr_week.add(new BarEntry(2, 11));
        hr_week.add(new BarEntry(3, 7));
        hr_week.add(new BarEntry(4, 13));
        hr_week.add(new BarEntry(5, 8));
        hr_week.add(new BarEntry(6, 12));
        hr_week.add(new BarEntry(7, 9));

        step_week.add(new BarEntry(1, 7206));
        step_week.add(new BarEntry(2, 8124));
        step_week.add(new BarEntry(3, 7914));
        step_week.add(new BarEntry(4, 5919));
        step_week.add(new BarEntry(5, 9012));
        step_week.add(new BarEntry(6, 8213));
        step_week.add(new BarEntry(7, 7129));

        sleep_ratio.add(new PieEntry(2, 1));
        sleep_ratio.add(new PieEntry(20, 20));

        hr_report = view.findViewById(R.id.line_chart);
        step_report = view.findViewById(R.id.bar_chart);
        sleep_report = view.findViewById(R.id.pie_chart);

//        hr_week_report = view.findViewById(R.id.line_chart);

        draw_hr_lineChart(values);
        draw_step_barChart(step_day);
        draw_sleep_pieChart(sleep_ratio);



        btnDay = view.findViewById(R.id.day_btn);
        btnWeek = view.findViewById(R.id.week_btn);
        btnMonth = view.findViewById(R.id.month_btn);
        btnAnalysis = view.findViewById(R.id.analysis_report_btn);

        btnDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                draw_hr_lineChart(hr_week);
                draw_step_barChart(step_day);
                draw_sleep_pieChart(sleep_ratio);
            }
        });

        btnWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                draw_hr_lineChart(values);
                draw_step_barChart(step_day);
                draw_sleep_pieChart(sleep_ratio);
            }
        });

        btnMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                draw_hr_lineChart(hr_week);
                draw_step_barChart(step_day);
                draw_sleep_pieChart(sleep_ratio);
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
        // greenLine
        set = new LineDataSet(db_data, "Weekly Steps");
        set.setMode(LineDataSet.Mode.LINEAR);//類型為折線
        set.setColor(getResources().getColor(R.color.darkBlueLine));//線的顏色
        set.setLineWidth(1.5f);//線寬
        set.setDrawCircles(false); //不顯示相應座標點的小圓圈(預設顯示)
        set.setDrawValues(false);//不顯示座標點對應Y軸的數字(預設顯示)
        //理解爲多條線的集合
        LineData data = new LineData(set);
        hr_report.setData(data);//一定要放在最後
        hr_report.animateY(2000);
        hr_report.invalidate();//繪製圖表
    }
    private void draw_step_barChart(final ArrayList<BarEntry> db_data) {
        final BarDataSet barDataSet;

        barDataSet = new BarDataSet(db_data,"");
        barDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
//        barDataSet.setValueTextColor(Color.BLACK);
//        barDataSet.setValueTextSize(5f);
        BarData data = new BarData(barDataSet);
        step_report.setFitBars(true);
        step_report.setData(data);
        step_report.getDescription().setText("Bar");
        step_report.animateY(2000);
        step_report.invalidate();
    }
    private void draw_sleep_pieChart(final ArrayList<PieEntry> db_data) {
        final PieDataSet pieDataSet;

        pieDataSet = new PieDataSet(db_data,"");
        pieDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
//        pieDataSet.setValueTextColor(Color.BLACK);
//        pieDataSet.setValueTextSize(15f);
        PieData data = new PieData(pieDataSet);
        sleep_report.setData(data);
        sleep_report.getDescription().setEnabled(false);
        sleep_report.setCenterText("");
        sleep_report.animateY(2000);
        sleep_report.invalidate();
    }

}
