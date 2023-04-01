package com.awesomeproject;

import android.app.usage.UsageStats;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Module extends ReactContextBaseJavaModule {

    ReactApplicationContext reactContext;
    public Module(ReactApplicationContext reactContext) {
        super(reactContext); //required by React Native

        this.reactContext = reactContext;
    }

    @Override
    //getName is required to define the name of the module represented in JavaScript
    public String getName() {
        return "HelloWorld";
    }

    @ReactMethod
    public String sayHi(String text) {

        // Create a new HttpClient and Post Header


        try {
            // Add your data



            List<UsageStats> stats = UStats.getUsageStatsList(reactContext);
            System.out.println(stats.get(0));

            List<Map<String, Long>> result = new ArrayList<>();


            URL url = new URL(String.format("/asd/%s", "1"));
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
//            conn.setRequestProperty("Accept","application/json");
//            conn.setDoOutput(true);
//            conn.setDoInput(true);

            List<JSONObject> jsonParam = new ArrayList<>();

            for (UsageStats obj: stats) {
                if (
                        obj.getPackageName().contains("maps")
                    ||  obj.getPackageName().contains("web")
                    ||  obj.getPackageName().contains("instagram")
                    ||  obj.getPackageName().contains("facebook")

                ) {
                    result.add(
                        new HashMap<String, Long>() {{
                            put(obj.getPackageName(), obj.getTotalTimeInForeground());
                        }});

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        obj.getTotalTimeForegroundServiceUsed();
                    }

                    JSONObject jsonObject = new JSONObject();
                    System.out.println(obj.getPackageName());
                    jsonObject.put("app_name", obj.getPackageName());
                    jsonObject.put("ms", obj.getTotalTimeInForeground());

                    jsonParam.add(jsonObject);
                }
            }

            System.out.println(jsonParam.get(0).get("app_name"));

            Log.i("JSON", jsonParam.toString());
//            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
//
//            os.writeBytes(jsonParam.toString());
//
//            os.flush();
//            os.close();
//
//            Log.i("STATUS", String.valueOf(conn.getResponseCode()));
//            Log.i("MSG" , conn.getResponseMessage());
//
//            conn.disconnect();

        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }


//        ReactApplicationContext context = getReactApplicationContext();
//        Intent intent = new Intent(context, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);
//
//        System.out.println(text);

        return text;
    }

    @ReactMethod
    public void showMessage(String message) {
        // This is where you can call your native function
        // For example, you can show a Toast message
        Toast.makeText(getReactApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}