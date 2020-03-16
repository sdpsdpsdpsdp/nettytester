package com.laisontech.nettester.bean;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

/**
 * Created by SDP
 * on 2020/3/16
 * Des：
 */
public class IconBean {
    public enum HomeType {
        TCP,
        TCP_SERVER,
        UDP_SINGLE_BROADCAST,
        UDP_BOTH_BROADCAST,
        UDP_SERVER,
        SETTING
    }

    private @DrawableRes
    int iconRes;
    private @StringRes
    int iconTxtRes;
    private HomeType homeType;

    public IconBean(@DrawableRes int iconRes, @StringRes int iconTxtRes, HomeType homeType) {
        this.iconRes = iconRes;
        this.iconTxtRes = iconTxtRes;
        this.homeType = homeType;
    }

    public @DrawableRes
    int getIconRes() {
        return iconRes;
    }

    public @StringRes
    int getIconTxtRes() {
        return iconTxtRes;
    }

    public HomeType getHomeType() {
        return homeType;
    }
}
