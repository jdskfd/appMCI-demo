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

public class FragmentBoard3 extends Fragment {

    ArrayList<Entry> board31 = new ArrayList<>();
    ArrayList<Entry> board32 = new ArrayList<>();
    LineChart lineChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analysis_board3, container, false);


        //add values in line chart
        board31.add(new Entry(1,	21));
        board31.add(new Entry(2,	54));
        board31.add(new Entry(3,	15));
        board31.add(new Entry(4,	43));
        board31.add(new Entry(5,	68));
        board31.add(new Entry(6,	52));
        board31.add(new Entry(7,	58));
        board31.add(new Entry(8,	15));
        board31.add(new Entry(9,	42));
        board31.add(new Entry(10,108));
        board31.add(new Entry(11,13));
        board31.add(new Entry(12,72));
        board31.add(new Entry(13,81));
        board31.add(new Entry(14,56));
        board31.add(new Entry(15,35));
        board31.add(new Entry(16,41));
        board31.add(new Entry(17,28));
        board31.add(new Entry(18,15));
        board31.add(new Entry(19,57));
        board31.add(new Entry(20,68));
        board31.add(new Entry(21,211));
        board31.add(new Entry(22,164));
        board31.add(new Entry(23,86));
        board31.add(new Entry(24,72));
        board31.add(new Entry(25,21));
        board31.add(new Entry(26,87));
        board31.add(new Entry(27,114));
        board31.add(new Entry(28,35));
        board31.add(new Entry(29,75));
        board31.add(new Entry(30,46));
        board31.add(new Entry(31,75));
        board31.add(new Entry(32,89));
        board31.add(new Entry(33,127));
        board31.add(new Entry(34,345));
        board31.add(new Entry(35,121));
        board31.add(new Entry(36,235));
        board31.add(new Entry(37,120));
        board31.add(new Entry(38,57));
        board31.add(new Entry(39,35));
        board31.add(new Entry(40,157));
        board31.add(new Entry(41,31));
        board31.add(new Entry(42,54));
        board31.add(new Entry(43,34));
        board31.add(new Entry(44,59));
        board31.add(new Entry(45,24));
        board31.add(new Entry(46,36));
        board31.add(new Entry(47,89));
        board31.add(new Entry(48,127));
        board31.add(new Entry(49,43));
        board31.add(new Entry(50,87));
        board31.add(new Entry(51,95));
        board31.add(new Entry(52,168));
        board31.add(new Entry(53,208));
        board31.add(new Entry(54,76));
        board31.add(new Entry(55,56));
        board31.add(new Entry(56,89));
        board31.add(new Entry(57,128));
        board31.add(new Entry(58,96));
        board31.add(new Entry(59,82));
        board31.add(new Entry(60,165));
        board31.add(new Entry(61,75));
//
        board32.add(new Entry(1, (float) 49.304918032787));
        board32.add(new Entry(61,	(float) 114.7371));

//        board12.add(new Entry(1, (float) 1));
//        board12.add(new Entry(8,	(float) 3));

        lineChart = view.findViewById(R.id.ana_board_line3);

        draw_lineChart(board31,board32);



        return view;
    }

    private void draw_lineChart(ArrayList<Entry> board31, ArrayList<Entry> board32) {
        final LineDataSet set, set1;
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        // greenLine
        set = new LineDataSet(board31, "每日夜間離床步數(步)");
        set.setMode(LineDataSet.Mode.LINEAR);//類型為折線
        set.setColor(getResources().getColor(R.color.lightWhiteBlue));//線的顏色
        set.setLineWidth(1.0f);//線寬
        set.setDrawCircles(false); //不顯示相應座標點的小圓圈(預設顯示)
        set.setDrawValues(false);//不顯示座標點對應Y軸的數字(預設顯示)
        dataSets.add(set);

        set1 = new LineDataSet(board32, "夜間離床步數走勢");
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
