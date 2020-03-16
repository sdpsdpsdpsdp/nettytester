package com.laisontech.nettester.netty.client;

import android.util.Log;

import com.laisontech.nettester.netty.coder.RpcDecoder;
import com.laisontech.nettester.netty.coder.RpcEncoder;
import com.laisontech.nettester.netty.coder.RpcRequest;
import com.laisontech.nettester.netty.coder.RpcResponse;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by SDP
 * on 2020/3/16
 * Des：pipeline
 */
public class ChatClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        Log.e("Netty", "正在连接中...");
        //获取到channel
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new RpcEncoder(RpcRequest.class)); //编码request
        pipeline.addLast(new RpcDecoder(RpcResponse.class)); //解码response
        //自定义
        pipeline.addLast(new ChatClientHandler());
    }
}
