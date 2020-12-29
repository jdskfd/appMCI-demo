package com.example.appmci.todoFunction;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.appmci.FragmentBook;
import com.example.appmci.R;

public class Eating extends Fragment {
    private WebView webView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_eating, container, false);
        TextView Title1 = view.findViewById(R.id.title1);
        TextView Title2 = view.findViewById(R.id.title2);
        TextView Title3 = view.findViewById(R.id.title3);
        TextView Title4 = view.findViewById(R.id.title4);
        TextView Title5 = view.findViewById(R.id.title5);
        TextView Context1 = view.findViewById(R.id.context1);
        TextView Context2 = view.findViewById(R.id.context2);
        TextView Context3 = view.findViewById(R.id.context3);
        TextView Context4 = view.findViewById(R.id.context4);
        TextView Context5 = view.findViewById(R.id.context5);


        String string1 = "老人飲食設計1.\n需注意熱量攝取，維持體重穩定";
        String string2 = "老人飲食設計2.\n老人營養需均衡，6大類食物皆必備";
        String string3 = "老人飲食設計3.\n以天然辛香料取代過多調味";
        String string4 = "老人飲食設計4.\n提供多變的老人營養餐";
        String string5 = "老人飲食設計5.\n應使用口感鬆軟的食物";
        String string6 = "如果老人的胃口不佳，三餐的食用量少，可以採取少量多餐的方式，並選擇營養密度高的食物，必要時可以補充一些點心，維持老人的體重及養分攝取量。";
        String string7 = "6大類食物包含：全穀雜糧類、蔬菜類、豆魚蛋肉類、乳品類、水果類及油脂類，在飲食設計上盡量每餐都均衡攝取，確保老人可以攝取所有身體所需的營養成分。";
        String string8 = "烹調以少鹽、少油、少調味為原則，過多調味料會造成血壓的負擔，可以使用九層塔、大蒜、薑等較天然的辛香料來提振食慾。此外，也要避免使用肥肉或是動物油等飽和脂肪類的食物，避免增加心血管的負擔。";
        String string9 = "烹調方式應減少煎、炸的比例，但不要只使用水煮、水炒的方式，太單調的烹調也會影響老人的進食意願，加入蒸、煮、滷、燉、涼拌等方式，豐富餐點的變化，讓老人也能享受美味的食物。";
        String string10 = "針對有缺牙問題及吞嚥能力下降的老人，要選用口感鬆軟的食物，例如瓜類、嫩葉、馬鈴薯等，或是增加烹調的時間，將食材煮得更軟爛，蔬菜及水果也可以製成蔬果汁，更方便老人進食。";

//
        Title1.setText(string1);
        Context1.setText(string6);
        Title2.setText(string2);
        Context2.setText(string7);
        Title3.setText(string3);
        Context3.setText(string8);
        Title4.setText(string4);
        Context4.setText(string9);
        Title5.setText(string5);
        Context5.setText(string10);



//        Title2.setText(string2);
//        Title3.setText(string3);
//        Title4.setText(string4);
//        Title5.setText(string5);

        return view;
    }

}

