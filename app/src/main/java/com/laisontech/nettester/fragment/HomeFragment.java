package com.laisontech.nettester.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.laisontech.nettester.R;
import com.laisontech.nettester.activity.TcpTestActivity;
import com.laisontech.nettester.base.BaseFragment;
import com.laisontech.nettester.base.CommonAdapter;
import com.laisontech.nettester.base.CommonViewHolder;
import com.laisontech.nettester.bean.IconBean;
import com.laisontech.nettester.utils.ResourceUtil;

import java.util.List;

import butterknife.BindView;

/**
 * Created by SDP
 * on 2020/3/16
 * Des：
 */
public class HomeFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    @BindView(R.id.gv)
    GridView gv;
    private HomeAdapter mHomeAdapter;

    public static Fragment getInstance() {
        return new HomeFragment();
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        mHomeAdapter = new HomeAdapter(getContext(), ResourceUtil.buildHomeResource());
        gv.setAdapter(mHomeAdapter);
    }

    @Override
    protected void initEvent() {
        gv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        IconBean.HomeType homeType = mHomeAdapter.getItem(position).getHomeType();
        switch (homeType) {
            case TCP:
                openActivity(TcpTestActivity.class);
                break;
            case TCP_SERVER:

                break;
            case UDP_SINGLE_BROADCAST:

                break;
            case UDP_BOTH_BROADCAST:

                break;
            case UDP_SERVER:

                break;
            case SETTING:

                break;
        }
    }


    private static class HomeAdapter extends CommonAdapter<IconBean> {

        HomeAdapter(Context context, List<IconBean> data) {
            super(context, data);
        }

        @Override
        public int layoutId() {
            return R.layout.gv_home_item;
        }

        @Override
        public void convert(CommonViewHolder holder, IconBean iconBean, int position) {
            ((ImageView) holder.getView(R.id.item_icon)).setImageResource(iconBean.getIconRes());
            ((TextView) holder.getView(R.id.item_text)).setText(iconBean.getIconTxtRes());
        }
    }
}
