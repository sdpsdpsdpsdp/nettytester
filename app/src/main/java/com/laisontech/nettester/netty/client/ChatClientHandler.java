package com.laisontech.nettester.netty.client;


import android.util.Log;

import com.laisontech.nettester.netty.coder.RpcResponse;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by SDP
 * on 2020/3/16
 * Des：
 */
public class ChatClientHandler extends SimpleChannelInboundHandler<RpcResponse> {

    //处理服务端返回的数据
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcResponse response) throws Exception {
        Log.e("Netty", "接受到server响应数据" + ctx.channel().remoteAddress().toString() + "->" + response.toString());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
