package com.laisontech.nettester.netty.coder;

import android.util.Log;


import com.laisontech.nettester.netty.messagepacket.Packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


public class ChatMsgEncoder extends MessageToByteEncoder<Packet> {
    private static final String TAG = "ChatMsgEncoder";
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        try {
            byte[] msg = packet.encode();
            Log.e(TAG, "encode: " + packet.toString() );
            byteBuf.writeBytes(msg);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "encode:出错" + e.getMessage() );
        }

    }
}