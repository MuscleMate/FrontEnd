package com.example.musclematefront;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

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

public class ServerRequestHandler extends AsyncTask<String, Void, Pair<Integer, JSONObject>> {

    private static final String TAG = ServerRequestHandler.class.getSimpleName();
    private static final String COOKIE_PREFS = "CookiePrefs";
    private static final String COOKIE_KEY = "Cookie";
    private static final String USER_PREFS = "UserPrefs";
    private static final String USER_KEY = "User";
    private OnServerResponseListener listener;
    private Handler handler;
    Context context;

    public interface OnServerResponseListener {
        void onResponse(Pair<Integer, JSONObject> responsePair);

        void onError(String error);
    }

    public ServerRequestHandler(Context context, OnServerResponseListener listener) {
        this.context = context;
        this.listener = listener;
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    protected Pair<Integer, JSONObject> doInBackground(String... params) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request.Builder requestBuilder = new Request.Builder()
                .url(params[0])
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", context.getSharedPreferences(COOKIE_PREFS, Context.MODE_PRIVATE).getString(COOKIE_KEY, ""));
        Log.d(TAG, "Cookie: " + context.getSharedPreferences(COOKIE_PREFS, Context.MODE_PRIVATE).getString(COOKIE_KEY, "a"));
        if ("GET".equals(params[1])) {
            // If it's a GET request, use GET method
            requestBuilder = requestBuilder.get();
        } else if ("POST".equals(params[1])) {
            // If it's a POST request, use POST method and include the request body
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, params[2]);
            requestBuilder = requestBuilder.post(body);
        }else if("DELETE".equals(params[1])){
            requestBuilder = requestBuilder.delete();
        }

        // Build the request
        Request request = requestBuilder.build();
        Response response;
        JSONObject jsonResponseHeader;
        try {
            response = client.newCall(request).execute();
            int statusCode = response.code();
            String responseBody = response.body().string();
            Log.d(TAG, "Response: " + responseBody);
            JSONObject jsonResponse = new JSONObject(responseBody);

            String user = jsonResponse.optString("user");
            JSONObject jsonHeader = new JSONObject(response.headers().toMultimap());
            String cookie = jsonHeader.optString("set-cookie");
            if (cookie != null&&cookie.length()>3) {
                String[] parts = cookie.split(";");
                cookie = parts[0];
                cookie = cookie.trim().substring(2);
                }
            if (!user.isEmpty()&& !cookie.isEmpty()) {
                // Save the extracted cookie to SharedPreferences
                saveCookie(user, cookie);
            }
            return new Pair<>(statusCode, jsonResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }

    private void saveCookie(String user, String cookie) {
        if (cookie != null && cookie != "") {
            SharedPreferences sharedPreferences = context.getSharedPreferences(COOKIE_PREFS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(COOKIE_KEY, cookie).apply();
            Log.d(TAG, "cookiesaved: " + cookie);
            SharedPreferences sharedPreferencesUser = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editorUser = sharedPreferencesUser.edit();
            editor.putString(USER_KEY, user).apply();
        }
    }

    @Override
    protected void onPostExecute(Pair<Integer, JSONObject> response) {
        JSONObject jsonObject = response.second;
        if (jsonObject != null) {
            sendResponseToMainThread(response);
        }
    }

    private void sendResponseToMainThread(final Pair<Integer, JSONObject> responsePair) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                listener.onResponse(responsePair);
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