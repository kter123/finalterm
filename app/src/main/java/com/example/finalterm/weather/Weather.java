package com.example.finalterm.weather;

public class Weather {
    private String cityid;
    private String date;
    private String week;
    private String update_time;
    private String city;
    private String cityEn;
    private String country;
    private String countryEn;
    private String wea;
    private String wea_img;
    private String tem;
    private String tem1;
    private String tem2;
    private String win;
    private String win_speed;
    private String win_meter;
    private String humidity;
    private String visibility;
    private String pressure;
    private String air;
    private String air_pm25;
    private String air_level;
    private String air_tips;
    private Alarm alarm;
    private String rain_pcpn;
    private String uvIndex;
    private String uvDescription;
    private String wea_day;
    private String wea_day_img;
    private String wea_night;
    private String wea_night_img;
    private String sunrise;
    private String sunset;
    private Aqi aqi;
    // getters and setters

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String getTem() {
        return tem;
    }
    public void setTem(String tem) {
        this.tem = tem;
    }

    public String getWea() {
        return wea;
    }
    public void setWea(String wea) {
        this.wea = wea;
    }
    public String getTem1() {
        return tem1;
    }
    public void setTem1(String tem1) {
        this.tem1 = tem1;
    }
    public String getTem2() {
        return tem2;
    }
    public void setTem2(String tem2) {
        this.tem2 = tem2;
    }
    public String getHumidity() {
        return humidity;
    }
    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getAir_level() {
        return air_level;
    }
    public void setAir_level(String air_level) {
        this.air_level = air_level;
    }


}

class Alarm {
    private String alarm_type;
    private String alarm_level;
    private String alarm_title;
    private String alarm_content;
    // getters and setters
}

class Aqi {
    private String update_time;
    private String air;
    private String air_level;
    private String air_tips;
    private String pm25;
    private String pm25_desc;
    private String pm10;
    private String pm10_desc;
    private String o3;
    private String o3_desc;
    private String no2;
    private String no2_desc;
    private String so2;
    private String so2_desc;
    private String co;
    private String co_desc;
    private String kouzhao;
    private String yundong;
    private String waichu;
    private String kaichuang;
    private String jinghuaqi;
    // getters and setters
}