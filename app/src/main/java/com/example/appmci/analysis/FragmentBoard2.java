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

public class FragmentBoard2 extends Fragment {

    ArrayList<Entry> board21 = new ArrayList<>();
    ArrayList<Entry> board22 = new ArrayList<>();
    LineChart lineChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analysis_board2, container, false);


        //add values in line chart
        board21.add(new Entry(1,	7834));
        board21.add(new Entry(2,	9645));
        board21.add(new Entry(3,	8212));
        board21.add(new Entry(4,	5456));
        board21.add(new Entry(5,	8521));
        board21.add(new Entry(6,	7625));
        board21.add(new Entry(7,	10256));
        board21.add(new Entry(8,	6845));
        board21.add(new Entry(9,	9554));
        board21.add(new Entry(10,5647));
        board21.add(new Entry(11,7652));
        board21.add(new Entry(12,6859));
        board21.add(new Entry(13,5687));
        board21.add(new Entry(14,8965));
        board21.add(new Entry(15,8648));
        board21.add(new Entry(16,9524));
        board21.add(new Entry(17,7568));
        board21.add(new Entry(18,8765));
        board21.add(new Entry(19,5684));
        board21.add(new Entry(20,6875));
        board21.add(new Entry(21,9578));
        board21.add(new Entry(22,6964));
        board21.add(new Entry(23,6554));
        board21.add(new Entry(24,7564));
        board21.add(new Entry(25,8654));
        board21.add(new Entry(26,6987));
        board21.add(new Entry(27,5872));
        board21.add(new Entry(28,6897));
        board21.add(new Entry(29,9854));
        board21.add(new Entry(30,6976));
        board21.add(new Entry(31,8561));
        board21.add(new Entry(32,4568));
        board21.add(new Entry(33,8756));
        board21.add(new Entry(34,5875));
        board21.add(new Entry(35,9864));
        board21.add(new Entry(36,8545));
        board21.add(new Entry(37,5745));
        board21.add(new Entry(38,6845));
        board21.add(new Entry(39,5786));
        board21.add(new Entry(40,8545));
        board21.add(new Entry(41,7564));
        board21.add(new Entry(42,6548));
        board21.add(new Entry(43,4545));
        board21.add(new Entry(44,8645));
        board21.add(new Entry(45,6845));
        board21.add(new Entry(46,7865));
        board21.add(new Entry(47,5756));
        board21.add(new Entry(48,8864));
        board21.add(new Entry(49,5756));
        board21.add(new Entry(50,4568));
        board21.add(new Entry(51,5687));
        board21.add(new Entry(52,5244));
        board21.add(new Entry(53,6854));
        board21.add(new Entry(54,5756));
        board21.add(new Entry(55,6724));
        board21.add(new Entry(56,4684));
        board21.add(new Entry(57,8645));
        board21.add(new Entry(58,5742));
        board21.add(new Entry(59,6854));
        board21.add(new Entry(60,5758));
        board21.add(new Entry(61,7564));
//
        board22.add(new Entry(1, (float) 8219.28852));
        board22.add(new Entry(61,	(float) 6280.688));

//        board12.add(new Entry(1, (float) 1));
//        board12.add(new Entry(8,	(float) 3));

        lineChart = view.findViewById(R.id.ana_board_line2);

        draw_lineChart(board21,board22);



        return view;
    }

    private void draw_lineChart(ArrayList<Entry> board21, ArrayList<Entry> board22) {
        final LineDataSet set, set1;
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        // greenLine
        set = new LineDataSet(board21, "??????????????????(???)");
        set.setMode(LineDataSet.Mode.LINEAR);//???????????????
        set.setColor(getResources().getColor(R.color.lightWhiteBlue));//????????????
        set.setLineWidth(1.0f);//??????
        set.setDrawCircles(false); //????????????????????????????????????(????????????)
        set.setDrawValues(false);//????????????????????????Y????????????(????????????)
        dataSets.add(set);

        set1 = new LineDataSet(board22, "???????????????");
        set1.setMode(LineDataSet.Mode.LINEAR);//???????????????
        set1.setColor(getResources().getColor(R.color.mainGreenBlue));//????????????
        set1.setLineWidth(1.0f);//??????
        set1.setDrawCircles(false); //????????????????????????????????????(????????????)
        set1.setDrawValues(false);//????????????????????????Y????????????(????????????)
        dataSets.add(set1);



        //???????????????????????????
        LineData data = new LineData(dataSets);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setEnabled(false);
        lineChart.getAxisLeft().setTextColor(Color.LTGRAY);
        lineChart.getAxisRight().setTextColor(Color.LTGRAY);
        lineChart.getLegend().setTextColor(Color.LTGRAY);

        lineChart.getDescription().setEnabled(false);
        lineChart.setData(data);//?????????????????????
        lineChart.animateY(2000);
        lineChart.invalidate();//????????????
    }
}
