package com.laisontech.nettester.netty.client;

import android.util.Log;

import com.laisontech.nettester.netty.coder.RpcRequest;
import com.laisontech.nettester.netty.messagepacket.Packet;

import java.io.IOException;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by SDP
 * on 2020/3/16
 * Des：
 */
public class NettyClient {
    private static final String TAG ="Netty";
    //ip
    private String host;
    //port
    private int port;
    //客户端和服务器的通信通道
    private ChannelFuture channelFuture;

    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 连接服务器
     */
    public void connectServer() throws Exception {
        //创建事件循环组
        final EventLoopGroup workGroup = new NioEventLoopGroup();
            //客户端使用
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup)//注册channel
                    .channel(NioSocketChannel.class)//什么类型的channel
                    .handler(new ChatClientInitializer());//处理类
            //获取与主机连接开启监听
            channelFuture = bootstrap.connect(host, port).sync();
            //如果未来监听到channel关闭，则子线程会被释放
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) {
                    if (future.isSuccess()) {
                        Log.e(TAG, "连接服务器成功");
                    } else {
                        Log.e(TAG, "连接服务器失败");
                        future.cause().printStackTrace();
                        workGroup.shutdownGracefully(); //关闭线程组
                    }
                }
            });
    }

    public void sendMessage(RpcRequest msg) {
        Log.e(TAG, "sendMessage: " + msg.toString());
        //执行发送消息到缓存区并刷新
        if (channelFuture != null) {
            channelFuture.channel().writeAndFlush(msg);
        }
    }

}
