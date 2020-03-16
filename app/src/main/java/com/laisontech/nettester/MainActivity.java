package com.laisontech.nettester;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.laisontech.nettester.base.BaseActivity;
import com.laisontech.nettester.fragment.ChatFragment;
import com.laisontech.nettester.fragment.HomeFragment;
import com.laisontech.nettester.fragment.MoreFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final int FRAGMENT_POSITION_HOME = 0;
    private static final int FRAGMENT_POSITION_CHAT = 1;
    private static final int FRAGMENT_POSITION_MORE = 2;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    private List<Fragment> mBaseFragments;
    private static final String KEY_ID = "id";
    private static final String KEY_INDEX = "index";
    private @StringRes
    int mSelectID = R.string.app_name;
    private int mSelectIndex = FRAGMENT_POSITION_HOME;


    @Override
    protected int setContentViewID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mBaseFragments = new ArrayList<>();
        mBaseFragments.add(HomeFragment.getInstance());
        mBaseFragments.add(ChatFragment.getInstance());
        mBaseFragments.add(MoreFragment.getInstance());
    }

    @Override
    protected void initEvent() {
        navigation.setOnNavigationItemSelectedListener(this);
        showFragment(mSelectID, mSelectIndex);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                showFragment(R.string.app_name, FRAGMENT_POSITION_HOME);
                return true;
            case R.id.navigation_dashboard:
                showFragment(R.string.Chat, FRAGMENT_POSITION_CHAT);
                return true;
            case R.id.navigation_notifications:
                showFragment(R.string.More, FRAGMENT_POSITION_MORE);
                return true;
        }
        return false;
    }

    private void showFragment(@StringRes int titleRes, int currentIndex) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(currentIndex + "");
        if (fragment == null) {
            ft.add(R.id.fl_container, mBaseFragments.get(currentIndex), currentIndex + "");
        }
        for (int index = 0; index < mBaseFragments.size(); index++) {
            if (currentIndex == index) {
                ft.show(mBaseFragments.get(currentIndex));
            } else {
                ft.hide(mBaseFragments.get(index));
            }
        }
        ft.commit();
        setTitle(titleRes);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_ID, mSelectID);
        outState.putInt(KEY_INDEX, mSelectIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mSelectID = savedInstanceState.getInt(KEY_ID);
        mSelectIndex = savedInstanceState.getInt(KEY_INDEX);
    }
}
