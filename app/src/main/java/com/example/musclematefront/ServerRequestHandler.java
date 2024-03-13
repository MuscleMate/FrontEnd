package com.example.musclematefront;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;

public class ServerRequestHandler extends AsyncTask<String, Void, JSONObject> {

    private static final String TAG = ServerRequestHandler.class.getSimpleName();

    private OnServerResponseListener listener;
    private Handler handler;

    public interface OnServerResponseListener {
        void onResponse(JSONObject response);
        void onError(String error);
    }

    public ServerRequestHandler(OnServerResponseListener listener) {
        this.listener = listener;
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        String urlString = params[0];
        JSONObject jsonResponse = null;
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            String jsonString = builder.toString();
            jsonResponse = new JSONObject(jsonString);
        } catch (IOException | JSONException e) {
            Log.e(TAG, "Error: " + e.getMessage());
            sendErrorToMainThread("Error: " + e.getMessage());
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return jsonResponse;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        if (jsonObject != null) {
            sendResponseToMainThread(jsonObject);
        }
    }

    private void sendResponseToMainThread(final JSONObject response) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                listener.onResponse(response);
            }
        });
    }

    private void sendErrorToMainThread(final String error) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                listener.onError(error);
            }
        });
    }

    public void executeWithThreadPool(String url) {
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);
    }
}