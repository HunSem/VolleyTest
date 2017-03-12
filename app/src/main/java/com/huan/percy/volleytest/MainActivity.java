package com.huan.percy.volleytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String url = "";
    private static final String TAG = "VolleyTest";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void volleyGet() {
        //Step 1: apply a request queue
        //Request Queue could be a singleton
        RequestQueue queue = Volley.newRequestQueue(this);

        //Step 2: apply a request that with response and error listener
        //Request type: StringRequest, ImageRequest, JsonRequest
        StringRequest request = new StringRequest(url, new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,error.toString());
            }
        });

        //Step 3: add the request into request queue
        queue.add(request);
    }

    private void volleyGetJsonRequest() {
        //Also can be JsonArrayRequest
        JsonObjectRequest request = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
            }
        }, null);
    }

//    private void volleyGetImageRequest() {
//        ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>() {
//            @Override
//            public void onResponse(Bitmap response) {
//                imageView.setImageBitmap(response);
//            }
//        }, maxWidth, maxHeight, scaleType, decodeConfig, errListener);
//    }

    private void volleyPost() {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("header1", "header");
                map.put("header2", "header");
                map.put("params1", "value1");
                map.put("params2", "value2");
                return map;
            }
        };

        queue.add(request);
    }

}
