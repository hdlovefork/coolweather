package com.coolweather.app.test;

import android.test.InstrumentationTestCase;
import android.text.TextUtils;

import com.coolweather.app.util.HttpUtil;

/**
 * Created by Administrator on 2015/9/13.
 */
public class SimpleTest extends InstrumentationTestCase {
    public void test(){
        HttpUtil.sendHttpRequest("http://www.weather.com.cn/data/list3/city.xml", new HttpUtil.HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                assertTrue(TextUtils.isEmpty(response));
            }

            @Override
            public void onError(Exception e) {
               assertNull(e);
            }
        });
    }
}
