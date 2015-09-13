package com.coolweather.app.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2015/9/13.
 */
public class HttpUtil {
    public static void sendHttpRequest(final String url, final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                try {
                    URL dstUrl = new URL(url);
                    connection = (HttpURLConnection) dstUrl.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    String s = "";
                    StringBuilder sb = new StringBuilder();
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        while ((s = reader.readLine()) != null) {
                            sb.append(s);
                        }
                        if(listener!=null)
                            listener.onFinish(sb.toString());
                    }
                } catch (Exception e) {
                    if(listener!=null){
                        listener.onError(e);
                    }
                }finally {
                    if(connection!=null)
                        connection.disconnect();
                }
            }
        }).start();
    }

    public interface HttpCallbackListener {
        void onFinish(String response);

        void onError(Exception e);
    }
}

