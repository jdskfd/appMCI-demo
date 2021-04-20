package com.example.appmci.todoFunction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.appmci.R;

public class Sleeping extends Fragment {
    private WebView webView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sleep, container, false);
        TextView Title1 = view.findViewById(R.id.title1);
        TextView Title2 = view.findViewById(R.id.title2);
        TextView Title3 = view.findViewById(R.id.title3);
        TextView Title4 = view.findViewById(R.id.title4);
        TextView Context1 = view.findViewById(R.id.context1);
        TextView Context2 = view.findViewById(R.id.context2);
        TextView Context3 = view.findViewById(R.id.context3);
        TextView Context4 = view.findViewById(R.id.context4);
        String string1 = "1B, 洗澡時間";
        String string2 = "2B, 暫停時間";
        String string3 = "3B, 放鬆時間";
        String string4 = "4B, 床的時間";
        String string6 = "睡前安排一個洗澡時間，讓身心靈也洗個澡，洗去一天疲憊。如果時間不急，就慢慢來，不要總是洗一個戰鬥澡，又忙著下一件事情了。提醒自己，這個洗澡時間就像是一個切換的開關，將忙碌切換成平靜、將急忙切換成休息，從睡眠的角度來看，洗澡後體溫的改變也是可能幫助睡眠的小方法。";
        String string7 = "臨床上常有失眠患者睡前因為想了一些事情，然後躺在床上就開始止不住大腦的思緒而失眠了。睡眠管理職人也會請失眠者試著用「煩惱記事本」的方式，把這些煩惱寫下來，讓煩惱在睡前「暫停」下來，幫助大腦停止反覆思考與擔心。";
        String string8 = "建議接近睡覺前的時間安排15-30分鐘的放鬆，睡眠管理職人會推薦像是腹式呼吸法、肌肉放鬆等，來降低過度緊繃的身體與心靈，讓呼吸放慢下來、心跳也跟著緩慢，身體肌肉跟著放鬆下來，你也會漸漸感覺到頭腦可以冷靜，可以準備睡覺了！";
        String string9 = "在治療失眠的認知行為治療法中，有一個身體重新培養「床=睡覺」的治療法，稱為刺激控制法，這個行為治療法的基本概念是為降低失眠與床的配對，讓身體學習到躺上床就是該「開始放鬆、準備睡覺」，其中最重要的原則就是避免在上床看電視、看書、玩手機等活動，讓你清楚知道床只留給睡覺用。";
        Title1.setText(string1);
        Context1.setText(string6);
        Title2.setText(string2);
        Context2.setText(string7);
        Title3.setText(string3);
        Context3.setText(string8);
        Title4.setText(string4);
        Context4.setText(string9);
//        Title2.setText(string2);
//        Title3.setText(string3);
//        Title4.setText(string4);
//        Title5.setText(string5);

        return view;
    }
}