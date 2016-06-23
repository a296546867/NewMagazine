package com.example.sky.Application;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.example.sky.Activity.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.litepal.LitePalApplication;

import java.io.File;


/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/23 15:05
 * 修改人：meigong
 * 修改时间：2016/5/23 15:05
 * 修改备注：
 */
public class MApplication extends LitePalApplication{


    private static MApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();
        //初始化ImageLoader的配置信息
        initImageLoader(getApplicationContext());

    }

    // 单例标准中获得唯一的Application实例
    public static MApplication getInstance(){

        if(instance==null){
            synchronized(MApplication.class){
                if(instance==null){
                    instance=new MApplication();
                }
            }
        }
        return instance;
    }

    //初始化ImageLoader的配置信息
    public static void initImageLoader(Context context) {
        //配置信息
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);// 线程优先级，默认Thread.NORM_PRIORITY - 2
        config.denyCacheImageMultipleSizesInMemory();//设置内存缓存不允许缓存一张图片的多个尺寸，默认允许。
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());// 硬盘缓存文件名生成器，默认为哈希文件名生成器
        config.tasksProcessingOrder(QueueProcessingType.LIFO);// 任务进程的顺序，默认为：FIFO 先进先出
        config.threadPoolSize(4);//线程池内加载的数量,推荐范围1-5内。
        config.memoryCacheSize(3 * 1024 * 1024);//memoryCacheSize 为 0，则设置该内存缓存的最大字节数为 App 最大可用内存的 1/8。
        config.diskCacheSize(50 * 1024 * 1024);// 50 Mb sd卡(本地)缓存的最大值
        config.diskCacheFileCount(100);// 可以缓存的文件数量
        config.diskCache(new UnlimitedDiskCache(new File(Environment.getExternalStorageDirectory() + "/" + "YGdownload"+"/"+"Image")));// 硬盘缓存路径
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }
    //DisplayImageOptions配置文件
    public static DisplayImageOptions GetDisplayImageOptions(){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.mipmap.empty_photo)//图片下载时的占位图
//                .showImageForEmptyUri(R.mipmap.empty_photo)//URL为空时显示的占位图
//                .showImageOnFail(R.mipmap.empty_photo)//加载失败时显示的占位图
                .cacheInMemory(true)// 是否缓存在内存中，通过 Builder 构建的对象默认为 false
                .cacheOnDisk(true)// 是否缓存在磁盘中，通过 Builder 构建的对象默认为 false。
                .considerExifParams(false)// 是否考虑JPEG图像EXIF参数（旋转，翻转）
                .bitmapConfig(Bitmap.Config.RGB_565)// bitmap的质量，默认为ARGB_8888
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//图片的缩放方式
                .build();

        return options;
    }
}
