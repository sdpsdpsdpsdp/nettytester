package com.laisontech.nettester.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by SDP
 * on 2020/3/16
 * Des：
 */
public abstract class BaseFragment extends Fragment {

    private Unbinder mBind;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(this.getResLayoutId(), container, false);
        if (mBind == null) {
            mBind = ButterKnife.bind(this, view);
        }
        return view;
    }

    @LayoutRes
    protected abstract int getResLayoutId();

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.initData();
        this.initEvent();
    }

    protected void initData() {
    }

    protected void initEvent() {
    }

    protected void openActivity(String serializableName, Serializable serializable, Class<?> clz) {
        Intent intent = new Intent(this.getContext(), clz);
        intent.putExtra(serializableName, serializable);
        this.startActivity(intent);
    }

    protected void openActivity(Class<?> clz) {
        Intent intent = new Intent(this.getContext(), clz);
        this.startActivity(intent);
    }

    protected void openActivity(String key, boolean values, Class<?> clz) {
        Intent intent = new Intent(this.getContext(), clz);
        intent.putExtra(key, values);
        this.startActivity(intent);
    }

    protected void openActivityForResult(Class<?> clz, int requestCode) {
        Intent intent = new Intent(this.getContext(), clz);
        this.startActivityForResult(intent, requestCode);
    }

    protected void openActivityForResult(Bundle bundle, Class<?> clz, int requestCode) {
        Intent intent = new Intent(this.getContext(), clz);
        intent.putExtras(bundle);
        this.startActivityForResult(intent, requestCode);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
            mBind = null;
        }
    }

}
