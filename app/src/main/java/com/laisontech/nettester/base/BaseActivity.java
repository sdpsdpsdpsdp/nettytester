package com.laisontech.nettester.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;


import com.laisontech.nettester.R;

import java.io.Serializable;

import butterknife.ButterKnife;

/**
 * Created by SDP on 2017/4/12.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected boolean isNeedOnKeyDown = false;
    private Toast mToast;

    //设置App字体大小不变
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //增加到activity的栈集合中
        //处理在加载布局前需要设置的方法功能
        setOtherMethodBeforeLoadingContentView();
        //填充布局
        setContentView(setContentViewID());
        //初始化控件(可以使用ButterKnife、也可以不使用)
        ButterKnife.bind(this);
        //加载资源
        initData();
        //加载资源
        initData(savedInstanceState);
        //加载资源
        initData(true);
        //控件的操作事件
        initEvent();
    }


    //处理在加载布局前需要设置的方法等 无需抽象，子类可以不是实现
    protected void setOtherMethodBeforeLoadingContentView() {
    }

    //填充布局
    protected abstract
    @LayoutRes
    int setContentViewID();

    //初始化数据源
    protected void initData() {
    }

    //初始化数据源
    protected void initData(Bundle savedInstanceState) {
    }

    //初始化数据源
    protected void initData(boolean isNeedRefresh) {
    }

    //初始化事件
    protected void initEvent() {
    }


    //传入Bundle的方式打开activity
    protected void openActivity(Bundle bundle, Class<?> clz) {
        Intent intent = new Intent(this, clz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //传入Bundle的方式打开activity
    protected void openActivity(String keyName, String valueName, Class<?> clz) {
        Intent intent = new Intent(this, clz);
        intent.putExtra(keyName, valueName);
        startActivity(intent);
    }

    //传入Bundle的方式打开activity
    protected void openActivity(String keyName, boolean valueName, Class<?> clz) {
        Intent intent = new Intent(this, clz);
        intent.putExtra(keyName, valueName);
        startActivity(intent);
    }

    //传入Bundle的方式打开activity
    protected void openActivity(String parcelableName, Parcelable parcelable, Class<?> clz) {
        Intent intent = new Intent(this, clz);
        intent.putExtra(parcelableName, parcelable);
        startActivity(intent);
    }

    //传入Bundle的方式打开activity
    protected void openActivity(String serializableName, Serializable serializable, Class<?> clz) {
        Intent intent = new Intent(this, clz);
        intent.putExtra(serializableName, serializable);
        startActivity(intent);
    }

    //打开Activity
    protected void openActivity(Class<?> clz) {
        Intent intent = new Intent(this, clz);
        startActivity(intent);
    }

    //startActivityForResult
    protected void openActivityForResult(Class<?> clz, int requestCode) {
        Intent intent = new Intent(this, clz);
        startActivityForResult(intent, requestCode);
    }

    //startActivityForResult 传入Bundle
    protected void openActivityForResult(Bundle bundle, Class<?> clz, int requestCode) {
        Intent intent = new Intent(this, clz);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 使用反射打开activity
     *
     * @param activityName 要打开的activity全路径 com.laisontech.xxx.xxxActivity
     * @param intent       传参使用的Intent
     */
    public void navigatorTo(final String activityName, final Intent intent) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(activityName);
            if (clazz != null) {
                intent.setClass(this, clazz);
                this.startActivity(intent);
            }
        } catch (ClassNotFoundException e) {
            return;
        }
    }

    //弹出toast
    protected void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    protected void showToast(int msgId) {
        String msg = getResources().getString(msgId);
        if (mToast == null) {
            mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }


    //关闭文本输入框
    private void closeInputWindow() {
        View view = this.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void setBackgroundAlpha(Activity activity, float alpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = alpha;
        activity.getWindow().setAttributes(lp);
    }

    protected String getResStr(int resId) {
        return getResources().getString(resId);
    }

    protected String getEditTextStr(EditText editText) {
        return editText.getText().toString().trim();
    }

    private long currentTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (isNeedOnKeyDown) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (System.currentTimeMillis() - currentTime > 2000) {
                    showToast(R.string.exit);
                    currentTime = System.currentTimeMillis();
                } else {
                    finish();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    //需要监听的网络状态
    protected void needWatchNetStatus(Object obj) {

    }

    //启动子线程
    protected void startThread(final DoOperationInSubThread inSubThread) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (inSubThread != null) {
                    inSubThread.doOperation();
                }
            }
        }).start();
    }

    protected void fromSubThreadToUIThread(final DoOperationInMainThread inMainThread) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (inMainThread != null) {
                    inMainThread.doOperation();
                }
            }
        });
    }

    public interface DoOperationInSubThread {
        void doOperation();
    }

    public interface DoOperationInMainThread {
        void doOperation();
    }


}

