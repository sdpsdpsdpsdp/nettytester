package com.laisontech.nettester.netty.coder;


import com.laisontech.nettester.netty.messagepacket.Packet;
import com.laisontech.nettester.netty.messagepacket.PacketFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;


import java.util.List;
 
public class ChatMsgDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        final int length=byteBuf.readableBytes();
        final byte[] array=new byte[length];
        byteBuf.getBytes(byteBuf.readerIndex(),array,0,length);
        // use PacketFactory's createPacketFromBuffer()method to decode data packet
        Packet packet = PacketFactory.createPacketFromBuffer(array);
        list.add(packet);
    }
}