package com.example.sky.Utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.example.sky.Activity.R;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/18 15:21
 * 修改人：meigong
 * 修改时间：2016/5/18 15:21
 * 修改备注：
 */
public class LoaddingDialog extends Dialog {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loaddingdialog);

    }

    public LoaddingDialog(Context context) {
        super(context, R.style.dialogStyle);
        this.context = context;
    }


}
