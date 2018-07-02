package com.example.yangchenhui.movieshow.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MyAsyncLoader extends AsyncTaskLoader {
    private Context context;
    private String url;

    public MyAsyncLoader(@NonNull Context context, String s) {
        super(context);
        url = s;
        Logger.addLogAdapter(new AndroidLogAdapter());

    }

    @Override
    protected void onStartLoading() {

        if (isStarted()) {
            forceLoad();
        }
    }


    @Nullable
    @Override
    public String loadInBackground() {
        try {
            Log.d("sss", url);
            URL data_url = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) data_url.openConnection();
            urlConnection.setConnectTimeout(2000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader
                        (urlConnection.getInputStream()));
                StringBuffer buffer = new StringBuffer();
                String line = null;
                if ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
//                Logger.json(buffer.toString());
                return buffer.toString();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
