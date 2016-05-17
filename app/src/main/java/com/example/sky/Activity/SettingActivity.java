package com.example.sky.Activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/10 16:37
 * 修改人：meigong
 * 修改时间：2016/5/10 16:37
 * 修改备注：
 */
public class SettingActivity extends BaseActivity implements TextView.OnClickListener{

    TextView returnText;//返回按钮

    RelativeLayout dayOrNight;           //白天夜间模式
    RelativeLayout zhengweiziti;        //正文字体
    RelativeLayout checkUpDate;         //检查更新
    RelativeLayout aboutUS;              //关于我们

    ImageView      modeImage;            //白天夜间模式
    TextView       wordSizeText;         //字体大小

    final String[] wordSzie = new String[]{"偏小", "中等", "偏大"};
    final String[] dayOrNightText = new String[]{"白天", "夜间"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingactivity_layout);

        binViews();
        setListener();
    }


    private void binViews(){

        returnText=(TextView)findViewById(R.id.settingfragment_return_text);
        dayOrNight=(RelativeLayout)findViewById(R.id.changeMode);
        zhengweiziti=(RelativeLayout)findViewById(R.id.AdjustarticleTextSize);
        checkUpDate=(RelativeLayout)findViewById(R.id.CheckUPdate);
        aboutUS=(RelativeLayout)findViewById(R.id.AboutUs);

        //白天夜间模式图片
        modeImage=(ImageView)findViewById(R.id.modeimage);

        //字体大小文本
        wordSizeText=(TextView)findViewById(R.id.currentTextsize);

    }


    private void setListener(){

        returnText.setOnClickListener(this);
        dayOrNight.setOnClickListener(this);
        zhengweiziti.setOnClickListener(this);
        checkUpDate.setOnClickListener(this);
        aboutUS.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.settingfragment_return_text:
                this.finish();
                break;
            case R.id.changeMode:

                new AlertDialog.Builder(SettingActivity.this).setTitle("请选择显示模式：")
                        .setSingleChoiceItems(dayOrNightText, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "你选择了" + dayOrNightText[which], Toast.LENGTH_SHORT).show();

                                //设置白天或夜间图片
                                if (which==0) {
                                    modeImage.setImageResource(R.mipmap.sun);
                                }else{
                                    modeImage.setImageResource(R.mipmap.moon);
                                }

                                //关闭dialog
                                dialog.dismiss();
                            }
                        }).create().show();

                break;
            case R.id.AdjustarticleTextSize:

              new AlertDialog.Builder(SettingActivity.this).setTitle("请选择正文的字体大小：")
                        .setSingleChoiceItems(wordSzie, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "你选择了" + wordSzie[which], Toast.LENGTH_SHORT).show();

                                //设置大小
                                wordSizeText.setText(wordSzie[which]);
                                //关闭dialog
                                dialog.dismiss();
                            }
                        }).create().show();


                break;
            case R.id.CheckUPdate:
                break;
            case R.id.AboutUs:


                //跳转到关于我们
                startActivity(new Intent(SettingActivity.this,AboutUSActivity.class));


                break;

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        this.finish();

        return super.onKeyDown(keyCode, event);
    }
}
