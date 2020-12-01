package com.example.appmci;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appmci.database.ConnectionClass;
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

public class FragmentHealth extends Fragment {


    ArrayList<Entry> values = new ArrayList<>();
    ArrayList<Entry> values_end = new ArrayList<>();

    LineChart lineChart;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_health, container, false);


        values.add(new Entry(1, 30.65f));
        values.add(new Entry(1.3f, 30.69f));
        values.add(new Entry(5.8f, 30.58f));
        values.add(new Entry(6, 30.58f));
        //last point
        values_end.add(new Entry(6, 30.58f));

        lineChart = view.findViewById(R.id.chart_line);
        initDataSet(values,values_end);

        return view;
    }

    private void initDataSet(final ArrayList<Entry> values, ArrayList<Entry> values_end) {
        final LineDataSet set, set1, set_end, set1_end;
        // greenLine
        set = new LineDataSet(values, "");
        set.setMode(LineDataSet.Mode.LINEAR);//類型為折線
        set.setColor(getResources().getColor(R.color.tiffanyBlue));//線的顏色
        set.setLineWidth(1.5f);//線寬
        set.setDrawCircles(false); //不顯示相應座標點的小圓圈(預設顯示)
        set.setDrawValues(false);//不顯示座標點對應Y軸的數字(預設顯示)
//greenLine最後的圓點
        set_end = new LineDataSet(values_end, "");
        set_end.setCircleColor(getResources().getColor(R.color.lightGrey));//圓點顏色
        set_end.setColor(getResources().getColor(R.color.displayBoardBackground));//線的顏色
        set_end.setCircleRadius(4);//圓點大小
        set_end.setDrawCircleHole(false);//圓點為實心(預設空心)
        set_end.setDrawValues(false);//不顯示座標點對應Y軸的數字(預設顯示)
        /**
         * yellowLine及其最後的圓點設定可比照如上greenLine設定，不再列示
         */
//理解爲多條線的集合
        LineData data = new LineData(set, set_end);
        lineChart.setData(data);//一定要放在最後
        lineChart.invalidate();//繪製圖表
    }

}
