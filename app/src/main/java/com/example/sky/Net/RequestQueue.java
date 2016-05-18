//package com.example.sky.Net;
//
//
//import android.content.Context;
//
//import com.android.volley.Request;
//import com.android.volley.toolbox.Volley;
//
//import java.util.LinkedList;
//import java.util.List;
//
///**
// * 项目名称：NewMagazine
// * 类描述：
// * 创建人：meigong
// * 创建时间：2016/5/18 16:46
// * 修改人：meigong
// * 修改时间：2016/5/18 16:46
// * 修改备注：
// */
//public class RequestQueue {
//
//
//    // volley网络请求队列对象
//    private RequestQueue mRequestQueue;
//    //存储请求的list
//    private List<RequestQueue> activityList=new LinkedList<RequestQueue>();
//
//    Context context;
//
//    public RequestQueue(Context context){
//        this.context=context;
//
//    }
//
//    // 拿到请求队列
//    public RequestQueue getRequestQueue(){
//
//        if(mRequestQueue==null){
//            mRequestQueue= Volley.newRequestQueue(context);
//
//        }
//
//        return mRequestQueue;
//    }
//
//    // 添加请求到请求队列加标注
//    public <T>void addToRequestQueue(Request<T> req, String tag){
//
//        getRequestQueue().add(req);
//    }
//
//    // 添加请求到请求队列
//    public <T>void addToRequestQueue(Request<T> req){
//        // set the default tag if tag is empty
//
//        getRequestQueue().add(req);
//    }
//
//    // 根据标示取消请求队列中的一个请求
//    public void cancelPendingRequests(Object tag){
//        if(mRequestQueue!=null){
//            mRequestQueue.cancelAll(tag);
//
//        }
//
//    }
//
//
//}
