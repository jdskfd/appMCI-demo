package com.example.appmci.analysis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.appmci.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class FragmentBoard5 extends Fragment {

    ArrayList<Entry> board51 = new ArrayList<>();
    ArrayList<Entry> board52 = new ArrayList<>();
    LineChart lineChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analysis_board5, container, false);


        //add values in line chart
        board51.add(new Entry(1,	75));
        board51.add(new Entry(2,	69));
        board51.add(new Entry(3,	72));
        board51.add(new Entry(4,	78));
        board51.add(new Entry(5,	76));
        board51.add(new Entry(6,	77));
        board51.add(new Entry(7,	81));
        board51.add(new Entry(8,	85));
        board51.add(new Entry(9,	76));
        board51.add(new Entry(10,72));
        board51.add(new Entry(11,73));
        board51.add(new Entry(12,78));
        board51.add(new Entry(13,68));
        board51.add(new Entry(14,71));
        board51.add(new Entry(15,69));
        board51.add(new Entry(16,71));
        board51.add(new Entry(17,68));
        board51.add(new Entry(18,73));
        board51.add(new Entry(19,69));
        board51.add(new Entry(20,75));
        board51.add(new Entry(21,80));
        board51.add(new Entry(22,71));
        board51.add(new Entry(23,78));
        board51.add(new Entry(24,70));
        board51.add(new Entry(25,76));
        board51.add(new Entry(26,81));
        board51.add(new Entry(27,67));
        board51.add(new Entry(28,77));
        board51.add(new Entry(29,75));
        board51.add(new Entry(30,81));
        board51.add(new Entry(31,65));
        board51.add(new Entry(32,74));
        board51.add(new Entry(33,67));
        board51.add(new Entry(34,78));
        board51.add(new Entry(35,74));
        board51.add(new Entry(36,71));
        board51.add(new Entry(37,71));
        board51.add(new Entry(38,80));
        board51.add(new Entry(39,68));
        board51.add(new Entry(40,78));
        board51.add(new Entry(41,85));
        board51.add(new Entry(42,75));
        board51.add(new Entry(43,88));
        board51.add(new Entry(44,71));
        board51.add(new Entry(45,73));
        board51.add(new Entry(46,75));
        board51.add(new Entry(47,86));
        board51.add(new Entry(48,68));
        board51.add(new Entry(49,72));
        board51.add(new Entry(50,69));
        board51.add(new Entry(51,71));
        board51.add(new Entry(52,78));
        board51.add(new Entry(53,68));
        board51.add(new Entry(54,71));
        board51.add(new Entry(55,65));
        board51.add(new Entry(56,69));
        board51.add(new Entry(57,70));
        board51.add(new Entry(58,67));
        board51.add(new Entry(59,74));
        board51.add(new Entry(60,78));
        board51.add(new Entry(61,67));
//
        board52.add(new Entry(1, (float) 75.19180));
        board52.add(new Entry(61,	(float) 72.330518));

        lineChart = view.findViewById(R.id.ana_board_line5);

        draw_lineChart(board51,board52);



        return view;
    }

    private void draw_lineChart(ArrayList<Entry> board51, ArrayList<Entry> board52) {
        final LineDataSet set, set1;
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        // greenLine
        set = new LineDataSet(board51, "");
        set.setMode(LineDataSet.Mode.LINEAR);//類型為折線
        set.setColor(getResources().getColor(R.color.lightWhiteBlue));//線的顏色
        set.setLineWidth(1.0f);//線寬
        set.setDrawCircles(false); //不顯示相應座標點的小圓圈(預設顯示)
        set.setDrawValues(false);//不顯示座標點對應Y軸的數字(預設顯示)
        dataSets.add(set);

        set1 = new LineDataSet(board52, "");
        set1.setMode(LineDataSet.Mode.LINEAR);//類型為折線
        set1.setColor(getResources().getColor(R.color.mainGreenBlue));//線的顏色
        set1.setLineWidth(1.0f);//線寬
        set1.setDrawCircles(false); //不顯示相應座標點的小圓圈(預設顯示)
        set1.setDrawValues(false);//不顯示座標點對應Y軸的數字(預設顯示)
        dataSets.add(set1);


        //理解爲多條線的集合
        LineData data = new LineData(dataSets);

        lineChart.getDescription().setEnabled(false);
        lineChart.setData(data);//一定要放在最後
        lineChart.animateY(2000);
        lineChart.invalidate();//繪製圖表
    }
}
