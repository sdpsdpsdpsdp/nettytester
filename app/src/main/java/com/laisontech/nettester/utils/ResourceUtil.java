package com.laisontech.nettester.utils;

import com.laisontech.nettester.R;
import com.laisontech.nettester.bean.IconBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SDP
 * on 2020/3/16
 * Des：
 */
public class ResourceUtil {

    public static List<IconBean> buildHomeResource() {
        List<IconBean> result = new ArrayList<>();
        result.add(new IconBean(R.drawable.ic_socket_tcp, R.string.title_tcp, IconBean.HomeType.TCP));
        result.add(new IconBean(R.drawable.ic_socket_tcpserver, R.string.title_tcp_server, IconBean.HomeType.TCP_SERVER));
        result.add(new IconBean(R.drawable.ic_socket_udpsingle, R.string.title_udp_signle, IconBean.HomeType.UDP_SINGLE_BROADCAST));
        result.add(new IconBean(R.drawable.ic_socket_udpbroadcast, R.string.title_udp_both, IconBean.HomeType.UDP_BOTH_BROADCAST));
        result.add(new IconBean(R.drawable.ic_socket_udpserver, R.string.title_udp_server, IconBean.HomeType.UDP_SERVER));
        result.add(new IconBean(R.drawable.ic_menu_setting, R.string.title_setting, IconBean.HomeType.SETTING));
        return result;
    }

}
