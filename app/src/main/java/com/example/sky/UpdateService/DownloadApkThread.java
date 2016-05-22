package com.example.sky.UpdateService;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：sky
 * 创建时间：2016/5/20 21:41
 * 修改人：sky
 * 修改时间：2016/5/20 21:41
 * 修改备注：
 */
public class DownloadApkThread extends Thread {


    private static final int DOWNLOAD = 1;//apk的下载进度
    private static final int DOWNLOAD_FINISH = 2;//apk下载结束


    private String apkURL;
    private Handler handler;

    private int nowProgress;

    public DownloadApkThread(String apkURL, Handler handler){
        this.apkURL=apkURL;
        this.handler=handler;

        nowProgress=0;

    }


    @Override
    public void run() {
        super.run();
        try {
            // 判断SD卡是否存在，并且是否具有读写权限
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                // 获得存储路径字符串
                String sdpath = Environment.getExternalStorageDirectory() + "/" + "YGdownload";
                //生成目录
                File file = new File(sdpath);
                // 判断文件目录是否存在
                if (!file.exists()) {
                    //不存在就生成目录
                    file.mkdir();
                }
                //建立文件
                File apkfile = new File(sdpath, "Magazine.apk");// 在sdpath目录建立文件对象
                // 创建连接
                HttpURLConnection conn = (HttpURLConnection) new URL(apkURL).openConnection();
                conn.connect();
                // 获取文件大小
                int length = conn.getContentLength();
                // 创建输入流，读取数据
                InputStream is = conn.getInputStream();
                //创建输出流，写入数据到文件
                FileOutputStream fos = new FileOutputStream(apkfile);
                //已经下载的长度
                int len = 0;
                // 缓冲区，每次读取的字节
                byte buffer[] = new byte[1024];
                //每次写入的长度
                int readsize =0;
                // 写入到文件中
                while ((readsize =is.read(buffer))>0){
                    // 写入文件
                    fos.write(buffer, 0, readsize );
                    //已经下载的长度
                    len += readsize ;
                    float d =((float)len/length)*100;
                    // 计算进度条位置
                    if (length%len==2){
                        // 更新进度
                        Message msg = new Message();
                        msg.arg1=len;
                        msg.arg2=length;
                        handler.sendMessage(msg);
                    }
                }
                fos.close();
                is.close();
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
