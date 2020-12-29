package com.example.appmci.analysis;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.appmci.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class FragmentBoard4 extends Fragment {

    ArrayList<Entry> board41 = new ArrayList<>();
    ArrayList<Entry> board42 = new ArrayList<>();
    LineChart lineChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analysis_board4, container, false);


        //add values in line chart
        board41.add(new Entry(1,	0));
        board41.add(new Entry(2,	1));
        board41.add(new Entry(3,	0));
        board41.add(new Entry(4,	1));
        board41.add(new Entry(5,	1));
        board41.add(new Entry(6,	1));
        board41.add(new Entry(7,	0));
        board41.add(new Entry(8,	0));
        board41.add(new Entry(9,	3));
        board41.add(new Entry(10,4));
        board41.add(new Entry(11,0));
        board41.add(new Entry(12,3));
        board41.add(new Entry(13,3));
        board41.add(new Entry(14,1));
        board41.add(new Entry(15,1));
        board41.add(new Entry(16,0));
        board41.add(new Entry(17,0));
        board41.add(new Entry(18,0));
        board41.add(new Entry(19,0));
        board41.add(new Entry(20,1));
        board41.add(new Entry(21,4));
        board41.add(new Entry(22,2));
        board41.add(new Entry(23,1));
        board41.add(new Entry(24,2));
        board41.add(new Entry(25,0));
        board41.add(new Entry(26,1));
        board41.add(new Entry(27,1));
        board41.add(new Entry(28,0));
        board41.add(new Entry(29,1));
        board41.add(new Entry(30,0));
        board41.add(new Entry(31,0));
        board41.add(new Entry(32,2));
        board41.add(new Entry(33,2));
        board41.add(new Entry(34,4));
        board41.add(new Entry(35,3));
        board41.add(new Entry(36,1));
        board41.add(new Entry(37,1));
        board41.add(new Entry(38,0));
        board41.add(new Entry(39,0));
        board41.add(new Entry(40,2));
        board41.add(new Entry(41,0));
        board41.add(new Entry(42,0));
        board41.add(new Entry(43,0));
        board41.add(new Entry(44,0));
        board41.add(new Entry(45,0));
        board41.add(new Entry(46,0));
        board41.add(new Entry(47,3));
        board41.add(new Entry(48,3));
        board41.add(new Entry(49,0));
        board41.add(new Entry(50,2));
        board41.add(new Entry(51,1));
        board41.add(new Entry(52,3));
        board41.add(new Entry(53,1));
        board41.add(new Entry(54,2));
        board41.add(new Entry(55,0));
        board41.add(new Entry(56,1));
        board41.add(new Entry(57,2));
        board41.add(new Entry(58,1));
        board41.add(new Entry(59,1));
        board41.add(new Entry(60,3));
        board41.add(new Entry(61,0));
//
        board42.add(new Entry(1, (float) 1.044262));//未完成
        board42.add(new Entry(61, (float) 1.247485));//未完成

//        board12.add(new Entry(1, (float) 1));
//        board12.add(new Entry(8,	(float) 3));

        lineChart = view.findViewById(R.id.ana_board_line4);

        draw_lineChart(board41,board42);



        return view;
    }

    private void draw_lineChart(ArrayList<Entry> board41, ArrayList<Entry> board42) {
        final LineDataSet set, set1;
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        // greenLine
        set = new LineDataSet(board41, "每日夜間離床次數(次)");
        set.setMode(LineDataSet.Mode.LINEAR);//類型為折線
        set.setColor(getResources().getColor(R.color.lightWhiteBlue));//線的顏色
        set.setLineWidth(1.0f);//線寬
        set.setDrawCircles(false); //不顯示相應座標點的小圓圈(預設顯示)
        set.setDrawValues(false);//不顯示座標點對應Y軸的數字(預設顯示)
        dataSets.add(set);

        set1 = new LineDataSet(board42, "夜間離床次數走勢");
        set1.setMode(LineDataSet.Mode.LINEAR);//類型為折線
        set1.setColor(getResources().getColor(R.color.mainGreenBlue));//線的顏色
        set1.setLineWidth(1.0f);//線寬
        set1.setDrawCircles(false); //不顯示相應座標點的小圓圈(預設顯示)
        set1.setDrawValues(false);//不顯示座標點對應Y軸的數字(預設顯示)
        dataSets.add(set1);


        //理解爲多條線的集合
        LineData data = new LineData(dataSets);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setEnabled(false);
        lineChart.getAxisLeft().setTextColor(Color.LTGRAY);
        lineChart.getAxisRight().setTextColor(Color.LTGRAY);
        lineChart.getLegend().setTextColor(Color.LTGRAY);


        lineChart.getDescription().setEnabled(false);
        lineChart.setData(data);//一定要放在最後
        lineChart.animateY(2000);
        lineChart.invalidate();//繪製圖表
    }
}
