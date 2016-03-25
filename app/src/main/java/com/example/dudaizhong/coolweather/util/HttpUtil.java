package com.example.dudaizhong.coolweather.util;

import android.os.Message;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Dudaizhong on 2016/3/19.
 */

/**
 * 发起一条HTTP请求的时候就这样写：
 * String address = "http://www.baidu.com";
 *  HttpUtil.sendHttpRequest(address,new HttpCallbackListener(){
 *
 *     public void onFinish(String response){
 *      //在这里根据返回的数据执行具体的逻辑
 *     }
 *
 *     public void onError(Exception e){
 *         //在这里对异常情况进行处理
 *     }
 *  });
 */


public class HttpUtil {

    public static void sendHttpRequest(final String address, final HttpCallbackListener listener) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;

                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    connection.setConnectTimeout(8000);//设置连接超时
                    connection.setReadTimeout(8000);//设置超时的毫秒数
                    //connection.setDoInput(true);
                    //connection.setDoOutput(true);

                    InputStream inputStream = connection.getInputStream();
                    //下面对获取到的输入流进行读取
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder response = new StringBuilder();
                    String line ;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    if (listener != null) {
                        //回调onFinish()方法
                        listener.onFinish(response.toString());
                    }

                } catch (Exception e) {
                    if (listener != null) {
                        //回调onError()方法
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
