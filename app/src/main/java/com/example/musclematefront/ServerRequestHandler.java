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
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, params[2]);
        Request request = new Request.Builder()
                .url(params[0])
                .method(params[1], body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", "jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySUQiOiI2NjBmZjkxZmY2YjQ2NzM2OWM5Y2M5MGQiLCJpYXQiOjE3MTIzMjI4NDcsImV4cCI6MTcxMjU4MjA0N30.JQbqhHQAZUC1VxprYvUB3LIBe8nmkyUfeugJ80oY8M8")
                .build();
        Response response;
        try {
            response = client.newCall(request).execute();
            String responseBody = response.body().string();
            Log.d(TAG, "Response: " + responseBody);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
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

    public void executeWithThreadPool(String url, String requestMethod, String jsonString) {
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url, requestMethod, jsonString);
    }
}