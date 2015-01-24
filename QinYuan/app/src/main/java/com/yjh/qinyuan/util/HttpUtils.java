package com.yjh.qinyuan.util;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.MalformedInputException;

import javax.net.ssl.HttpsURLConnection;

public class HttpUtils {
    public static <T> T getJsonData(InputStream inputStream, Class<T> classOfT) {
        Gson gson = new Gson();
        T resultObject = null;

        try {
            InputStreamReader r = new InputStreamReader(inputStream);
            resultObject = gson.fromJson(r, classOfT);
        } catch (JsonParseException jsonException) {
            jsonException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultObject;
    }

    public static <T> T getJsonData(String str, Class<T> classOfT) {
        Gson gson = new Gson();
        T resultObject = null;

        try {
            resultObject = gson.fromJson(str, classOfT);
        } catch (JsonParseException jsonException) {
            jsonException.printStackTrace();
        }

        return resultObject;
    }

    public static Object[] getJsonData(String str, Object[] obj) {
        Gson gson = new Gson();
        Object result[] = null;

        try {
            result = gson.fromJson(str, obj.getClass());
        } catch (JsonParseException jsonParseException) {
            jsonParseException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static Object[] getJsonData(InputStream inputStream, Object[] obj) {
        Gson gson = new Gson();
        Object result[] = null;

        try {
            InputStreamReader reader = new InputStreamReader(inputStream);
            result = gson.fromJson(reader, obj.getClass());
        } catch (JsonParseException jsonParseException) {
            jsonParseException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static InputStream getInputStream(String urlStr){
        URL url;
        InputStream is = null;

        try {
            url = new URL(urlStr);
            if (urlStr.startsWith("http://")) {
                URLConnection yc = url.openConnection();
                is = yc.getInputStream();
            } else {
                HttpsURLConnection yc = (HttpsURLConnection) url.openConnection();
                is = yc.getInputStream();
            }

        } catch (MalformedInputException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return is;
    }
}
