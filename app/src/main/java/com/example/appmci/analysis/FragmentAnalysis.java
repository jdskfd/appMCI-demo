package com.example.appmci.analysis;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appmci.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class FragmentAnalysis extends Fragment {

    ArrayList<Entry> testData = new ArrayList<>();

    LineChart step_report;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_analysis, container, false);




        testData.add(new BarEntry(1, 7206));
        testData.add(new BarEntry(2, 8124));
        testData.add(new BarEntry(3, 7914));
        testData.add(new BarEntry(4, 5919));
        testData.add(new BarEntry(5, 9012));
        testData.add(new BarEntry(6, 8213));
        testData.add(new BarEntry(7, 7129));

        step_report = view.findViewById(R.id.analysis_line_chart);

        draw_hr_lineChart(testData);


        return view;
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
        step_report.setData(data);//一定要放在最後
        step_report.animateY(2000);
        step_report.invalidate();//繪製圖表
    }



}