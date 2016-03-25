package com.example.dudaizhong.coolweather.util;

import android.text.TextUtils;

import com.example.dudaizhong.coolweather.model.City;
import com.example.dudaizhong.coolweather.model.CoolWeatherDB;
import com.example.dudaizhong.coolweather.model.County;
import com.example.dudaizhong.coolweather.model.Province;

/**
 * Created by Dudaizhong on 2016/3/24.
 */
public class Utility {

    //    解析和处理服务器返回的省级数据
    public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB, String response) {

        if (!TextUtils.isEmpty(response)) {//判断字符串是否为空
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length > 0) {
                for (String p : allProvinces) {
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);

//                    将解析出来的数据存储到Province数据库中
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    //    解析和处理服务器返回的市级数据
    public synchronized static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB, String response, int provinceId) {

        if (!TextUtils.isEmpty(response)) {//判断字符串是否为空
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0) {
                for (String c : allCities) {
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);

//                    将解析出来的数据存储到Province数据库中
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    //    解析和处理服务器返回的省级数据
    public synchronized static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB, String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {//判断字符串是否为空
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0) {
                for (String c : allCounties) {
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);

//                    将解析出来的数据存储到Province数据库中
                    coolWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }

}
