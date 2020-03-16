package com.laisontech.nettester.fragment;

import android.support.v4.app.Fragment;

import com.laisontech.nettester.R;
import com.laisontech.nettester.base.BaseFragment;

/**
 * Created by SDP
 * on 2020/3/16
 * Des：
 */
public class ChatFragment extends BaseFragment {
    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_chat;
    }

    public static Fragment getInstance() {
        return new ChatFragment();
    }
}
