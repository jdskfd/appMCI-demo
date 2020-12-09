package com.example.appmci;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentBook extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);

        ListView listView = (ListView)view.findViewById(R.id.book_list_view);

        String[] str = {"MCI簡介","常見症狀","飲食建議","運動規劃","照護方法","活動量","照護建議","健康心率","知能訓練","分析結果","常見問題"};
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, str);


        listView.setAdapter(adapter);

        return view;

    }

    private AdapterView.OnItemClickListener onClickListView = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

//            on click 的功能
        }
    };

}

