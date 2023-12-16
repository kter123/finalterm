package com.example.finalterm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.finalterm.fragments.weather_fragment;
import com.example.finalterm.fragments.task_fragment;

import com.example.finalterm.weather.Weather;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private static final String weatherUrl = "https://v1.yiketianqi.com/api?unescape=1&version=v61&appid=87227761&appsecret=Cj5HXeoS";

    public List<Fragment> fragmentList = new ArrayList<>();
    private FragmentManager fragmentManager;
//    private MyHandler myHandler;
    private View absorbedView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化底部导航栏
        InitBottomNavigation();
        //天气模块初始化
//        myHandler = new MyHandler();
        RefreshWeatherData();

    }

    public void RefreshWeatherData() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(weatherUrl).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e){
                e.printStackTrace();
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String weatherJson = response.body().string();
                Gson gson = new Gson();
                Log.d("test",weatherJson);
                Weather weather = gson.fromJson(weatherJson, Weather.class);
                Log.d("test","zyj:" +  gson.toJson(weather));
                ShowWeatherInfo(weather);
            }
        });
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                String weatherJson = response.body().string();
//                Weather weather = new Gson().fromJson(weatherJson, Weather.class);
//                Message message = new Message();
//                message.what = 1;
//                message.obj = weather;
//                myHandler.sendMessage(message);
//            }
//        });
    }

    //每日先知 - 天气模块
    public void RequestWeatherData(String cityName) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(weatherUrl).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e){
                e.printStackTrace();
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String weatherJson = response.body().string();
                Gson gson = new Gson();
                Log.d("test",weatherJson);
                Weather weather = gson.fromJson(weatherJson, Weather.class);
                Log.d("test","zyj:" +  gson.toJson(weather));
                ShowWeatherInfo(weather);
//                Message message = new Message();
//                message.what = 1;
//                message.obj = weather;
//                myHandler.sendMessage(message);
            }
        });

//                Response weatherJson2 = response.body().string();
//                message.what = 1;
//                message.obj = weather;
//                Log.d("test","zyj:" + message.obj);
//                myHandler.sendMessage(message);
//            }
//        });
    }



//    public void ShowWeatherInfo(Weather weather) {
////        String city = weather.getCity();
////        String wea = weather.getData().get(0).getWea();
////        String maxTem = weather.getData().get(0).getTem1();
////        String minTem = weather.getData().get(0).getTem2();
////        String tem = weather.getData().get(0).getTem();
////        String humidity = "湿度           " + weather.getData().get(0).getHumidity();
////        String air_level = "空气指数   " + weather.getData().get(0).getAir_level();
////
////        // tem  tem1  tem2  city  wea  rain  pm  image
////        ((TextView) findViewById(R.id.cityView)).setText(city);
////        ((TextView) findViewById(R.id.weaView)).setText(wea);
////        ((TextView) findViewById(R.id.mmtemView)).setText(
////                String.format(" %s ° / %s °", minTem.substring(0, minTem.length() - 1), maxTem.substring(0, maxTem.length() - 1)));
////        ((TextView) findViewById(R.id.temView)).setText(tem.substring(0, tem.length() - 1) + "°");
////        ((TextView) findViewById(R.id.humidityView)).setText(humidity);
////        ((TextView) findViewById(R.id.levelView)).setText(air_level);
////        ShowWeatherImage(wea);
//        String city = weather.getCity();
//        String wea = weather.getWea();
//        String humidity = "湿度           " + weather.getHumidity();
//        String air_level = "空气指数   " + weather.getAir_level();
//        String tem = weather.getTem();
//        String maxTem = weather.getTem1();
//        String minTem = weather.getTem2();
//        ((TextView) findViewById(R.id.cityView)).setText(city);
//        ((TextView) findViewById(R.id.weaView)).setText(wea);
//        ((TextView) findViewById(R.id.mmtemView)).setText(
//                String.format(" %s ° / %s °", minTem.substring(0, minTem.length() ), maxTem.substring(0, maxTem.length() )));
//        ((TextView) findViewById(R.id.temView)).setText(tem.substring(0, tem.length() ) + "°");
//        ((TextView) findViewById(R.id.humidityView)).setText(humidity);
//        ((TextView) findViewById(R.id.levelView)).setText(air_level);
//        ShowWeatherImage(wea);
//    }

    public void ShowWeatherInfo(Weather weather) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
               String city = weather.getCity();
               String wea = weather.getWea();
               String humidity = "湿度           " + weather.getHumidity();
               String air_level = "空气指数   " + weather.getAir_level();
               String tem = weather.getTem();
               String maxTem = weather.getTem1();
               String minTem = weather.getTem2();
               ((TextView) findViewById(R.id.cityView)).setText(city);
               ((TextView) findViewById(R.id.weaView)).setText(wea);
               ((TextView) findViewById(R.id.mmtemView)).setText(
                String.format(" %s ° / %s °", minTem.substring(0, minTem.length() ), maxTem.substring(0, maxTem.length() )));
               ((TextView) findViewById(R.id.temView)).setText(tem.substring(0, tem.length() ) + "°");
               ((TextView) findViewById(R.id.humidityView)).setText(humidity);
               ((TextView) findViewById(R.id.levelView)).setText(air_level);
               ShowWeatherImage(wea);
            }
        });
    }
    public void ShowWeatherImage(String w) {
        //天气大图片
        ImageView imageView = (ImageView) findViewById(R.id.weaImageView);
        //获取当前系统时间
        Calendar calendar;
        calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        boolean isAM = calendar.get(Calendar.AM_PM) == Calendar.PM;
        int Hour = isAM ? calendar.get(Calendar.HOUR) + 12 : calendar.get(Calendar.HOUR);
        switch (w) {
            case "暴雪":
            case "大雪":
            case "中雪":
            case "小雪":
                imageView.setImageResource(R.drawable.b_xue);
                break;
            case "暴雨":
            case "大雨":
            case "中雨":
                imageView.setImageResource(R.drawable.b_dayu);
                break;
            case "多云":
            case "多云转阴":
            case "阴":
                imageView.setImageResource(R.drawable.b_yin);
                break;
            case "多云转晴":
                if (Hour >= 18) imageView.setImageResource(R.drawable.night_qingzhuanduoyun);
                else imageView.setImageResource(R.drawable.qingzhuanduoyun);
                break;
            case "雷阵雨":
                imageView.setImageResource(R.drawable.b_leizhenyu);
                break;
            case "晴":
                if (Hour >= 18) imageView.setImageResource(R.drawable.b_nightqing);
                else imageView.setImageResource(R.drawable.b_qing);
                break;
            case "雾":
                imageView.setImageResource(R.drawable.b_wu);
                break;
            case "小雨":
            case "阵雨":
                imageView.setImageResource(R.drawable.b_xiaoyu);
                break;

        }
    }

    public void ReFreshWeatherImageClick(View view) {
        RefreshWeatherData();
    }

    // 底部导航栏模块
    public void InitBottomNavigation() {
        // 添加五个fragment实例到fragmentList，以便管理
        fragmentList.add(new task_fragment());
        fragmentList.add(new weather_fragment());

        //建立fragment管理器
        fragmentManager = getSupportFragmentManager();

        //管理器开启事务，将fragment实例加入管理器
        fragmentManager.beginTransaction()
                .add(R.id.FragmentLayout, fragmentList.get(0), "TASK")
                .add(R.id.FragmentLayout, fragmentList.get(1), "WEATHER")
                .commit();

        //设置fragment显示初始状态
        fragmentManager.beginTransaction()
                .show(fragmentList.get(0))
                .hide(fragmentList.get(1))
                .commit();

        //设置底部导航栏点击选择监听事件
        BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.menu_task){
                    ShowFragment(0);
                    return true;
                }
                else if(item.getItemId() == R.id.menu_weather){
                    ShowFragment(1);
                    return true;
                }
                return false;
            }
        });
    }

    public void ShowFragment(int index) {
        fragmentManager.beginTransaction()
                .show(fragmentList.get(index))
                .hide(fragmentList.get((index + 1) % 2))
                .commit();
    }

    //消息处理类
//    public class MyHandler extends Handler {
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//            Log.d("test2","zyj:" + msg.obj);
//            //what == 1   天气消息
//            if (msg.what == 1) {
//                ShowWeatherInfo((Weather) msg.obj);
//            }
//        }
//    }



}