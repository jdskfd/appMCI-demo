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

public class FragmentBoard6 extends Fragment {

    ArrayList<Entry> board61 = new ArrayList<>();
    ArrayList<Entry> board62 = new ArrayList<>();
    LineChart lineChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analysis_board6, container, false);


        //add values in line chart

        board61.add(new Entry(1,(float)0.002680623));
        board61.add(new Entry(2,(float)0.005598756));
        board61.add(new Entry(3,(float)0.001826595));
        board61.add(new Entry(4,(float)0.007881232));
        board61.add(new Entry(5,(float)0.007980284));
        board61.add(new Entry(6,(float)0.006819672));
        board61.add(new Entry(7,(float)0.005655226));
        board61.add(new Entry(8,(float)0.002191381));
        board61.add(new Entry(9,(float)0.004396064));
        board61.add(new Entry(10,(float)0.019125199));
        board61.add(new Entry(11,(float)0.001698902));
        board61.add(new Entry(12,(float)0.010497157));
        board61.add(new Entry(13,(float)0.01424301));
        board61.add(new Entry(14,(float)0.006246514));
        board61.add(new Entry(15,(float)0.004047179));
        board61.add(new Entry(16,(float)0.004304914));
        board61.add(new Entry(17,(float)0.003699789));
        board61.add(new Entry(18,(float)0.001711352));
        board61.add(new Entry(19,(float)0.010028149));
        board61.add(new Entry(20,(float)0.009890909));
        board61.add(new Entry(21,(float)0.022029651));
        board61.add(new Entry(22,(float)0.023549684));
        board61.add(new Entry(23,(float)0.013121758));
        board61.add(new Entry(24,(float)0.009518773));
        board61.add(new Entry(25,(float)0.002426624));
        board61.add(new Entry(26,(float)0.012451696));
        board61.add(new Entry(27,(float)0.019414169));
        board61.add(new Entry(28,(float)0.00507467));
        board61.add(new Entry(29,(float)0.007611122));
        board61.add(new Entry(30,(float)0.006594037));
        board61.add(new Entry(31,(float)0.008760659));
        board61.add(new Entry(32,(float)0.019483363));
        board61.add(new Entry(33,(float)0.01450434));
        board61.add(new Entry(34,(float)0.058723404));
        board61.add(new Entry(35,(float)0.012266829));
        board61.add(new Entry(36,(float)0.027501463));
        board61.add(new Entry(37,(float)0.020887728));
        board61.add(new Entry(38,(float)0.008327246));
        board61.add(new Entry(39,(float)0.006049084));
        board61.add(new Entry(40,(float)0.018373318));
        board61.add(new Entry(41,(float)0.004098361));
        board61.add(new Entry(42,(float)0.008246793));
        board61.add(new Entry(43,(float)0.007480748));
        board61.add(new Entry(44,(float)0.006824754));
        board61.add(new Entry(45,(float)0.003506209));
        board61.add(new Entry(46,(float)0.004577241));
        board61.add(new Entry(47,(float)0.015462126));
        board61.add(new Entry(48,(float)0.014327617));
        board61.add(new Entry(49,(float)0.007470466));
        board61.add(new Entry(50,(float)0.019045534));
        board61.add(new Entry(51,(float)0.016704765));
        board61.add(new Entry(52,(float)0.032036613));
        board61.add(new Entry(53,(float)0.030347242));
        board61.add(new Entry(54,(float)0.013203614));
        board61.add(new Entry(55,(float)0.008328376));
        board61.add(new Entry(56,(float)0.019000854));
        board61.add(new Entry(57,(float)0.014806246));
        board61.add(new Entry(58,(float)0.016718913));
        board61.add(new Entry(59,(float)0.011963817));
        board61.add(new Entry(60,(float)0.028655783));
        board61.add(new Entry(61,(float)0.009915389));
//
        board62.add(new Entry(1, (float) 0.005926));
        board62.add(new Entry(61,(float) 0.018126));

        lineChart = view.findViewById(R.id.ana_board_line6);

        draw_lineChart(board61,board62);



        return view;
    }

    private void draw_lineChart(ArrayList<Entry> board61, ArrayList<Entry> board62) {
        final LineDataSet set, set1;
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        // greenLine
        set = new LineDataSet(board61, "夜間活動量比率");
        set.setMode(LineDataSet.Mode.LINEAR);//類型為折線
        set.setColor(getResources().getColor(R.color.lightWhiteBlue));//線的顏色
        set.setLineWidth(1.0f);//線寬
        set.setDrawCircles(false); //不顯示相應座標點的小圓圈(預設顯示)
        set.setDrawValues(false);//不顯示座標點對應Y軸的數字(預設顯示)
        dataSets.add(set);

        set1 = new LineDataSet(board62, "夜間活動量比率走勢");
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
