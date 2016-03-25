package com.example.dudaizhong.coolweather.util;

/**
 * Created by Dudaizhong on 2016/3/19.
 */
public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
