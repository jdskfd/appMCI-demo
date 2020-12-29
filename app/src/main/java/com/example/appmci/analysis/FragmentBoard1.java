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
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class FragmentBoard1 extends Fragment {

    ArrayList<Entry> board11 = new ArrayList<>();
    ArrayList<Entry> board12 = new ArrayList<>();
    LineChart lineChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analysis_board1, container, false);


        //add values in line chart
        board11.add(new Entry(1,	2));
        board11.add(new Entry(2,	4));
        board11.add(new Entry(3,	6));
        board11.add(new Entry(4,	1));
        board11.add(new Entry(5,	4));
        board11.add(new Entry(6,	2));
        board11.add(new Entry(7,	1));
        board11.add(new Entry(8,	3));
//
        board12.add(new Entry(1, (float) 3.7857142));
        board12.add(new Entry(8,	(float) 2.16666));

//        board12.add(new Entry(1, (float) 1));
//        board12.add(new Entry(8,	(float) 3));

        lineChart = view.findViewById(R.id.ana_board_line1);

        draw_lineChart(board11,board12);



        return view;
    }

    private void draw_lineChart(ArrayList<Entry> board11, ArrayList<Entry> board12) {
        final LineDataSet set, set1;
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        // greenLine
        set = new LineDataSet(board11, "活動量達標次數(週)");
        set.setMode(LineDataSet.Mode.LINEAR);//類型為折線
        set.setColor(getResources().getColor(R.color.lightWhiteBlue));//線的顏色
        set.setLineWidth(1.0f);//線寬
        set.setDrawCircles(false); //不顯示相應座標點的小圓圈(預設顯示)
        set.setDrawValues(false);//不顯示座標點對應Y軸的數字(預設顯示)
        dataSets.add(set);

        set1 = new LineDataSet(board12, "活動量達標趨勢");
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
