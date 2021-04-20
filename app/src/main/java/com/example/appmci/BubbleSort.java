package com.example.appmci;

import android.util.Log;

import com.example.appmci.todoFunction.ItemMapping;

import java.util.ArrayList;

public class BubbleSort {
    public static void sort(ArrayList<ItemMapping> items) {
        int n = items.size();
        ItemMapping itemMapping = items.get(0);
        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                int time1 = Integer.valueOf(items.get(j-1).getItemTime());
                int time2 = Integer.valueOf(items.get(j).getItemTime());
                if( time1 > time2 ) {
                    ItemMapping temp = new ItemMapping();
                    ItemMapping temp2 = new ItemMapping();
                    String content = items.get(j-1).getItemContent();
                    temp.ItemSplit(content);
                    String content2 = items.get(j).getItemContent();
                    temp2.ItemSplit(content2);
                    items.set(j-1,temp2);
                    items.set(j,temp);
                }
            }
        }
    }
}