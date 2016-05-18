package com.example.sky.Net;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/18 16:43
 * 修改人：meigong
 * 修改时间：2016/5/18 16:43
 * 修改备注：
 */
public class HttpServerManager {

    final String ERRMSG="网络异常,请稍后再试";



    // 需要有handler回调
    public static void getJsonFromServerByPostWithHandler(String URL, final Map<String, String> map,final Handler handler,final Context context) {

        StringRequest req = new StringRequest(Request.Method.POST, Configurator.Login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                // Gson gson = new Gson();
                // State state = gson.fromJson(response, State.class);

                Message msg = new Message();

                msg.obj = response;

                handler.sendMessage(msg);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, ERRMSG, Toast.LENGTH_SHORT).show();

            }

        }) {
            // 设置post的参数
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return map;
            }

        };
//        Appcontext.getInstance().addToRequestQueue(req);
    }


            /***
             * 处理一切的POST形式的StringRequest
             */
    public static void postStringRequest(final Handler handler, String Url, final HashMap<String, String> params) {

        Response.Listener<String> listener = new Response.Listener<String>() {

            @Override
            public void onResponse(String arg0) {
                // TODO Auto-generated method stub

                Message msg = new Message();
                msg.arg1 = 1;
                msg.obj = arg0;

                handler.sendMessage(msg);// 將結果送回請求處

            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                // TODO Auto-generated method stub
                Message msg = new Message();
                msg.arg1 = 0;
                msg.obj = arg0;
                handler.sendMessage(msg);// 將結果送回請求處

            }
        };

        StringRequest postStringRequest = new StringRequest(Request.Method.POST, Url, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                return params;
            }
        };
//        Appcontext.getInstance().addToRequestQueue(postStringRequest);

    }

}
