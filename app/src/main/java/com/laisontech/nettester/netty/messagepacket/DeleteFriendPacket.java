package com.laisontech.nettester.netty.messagepacket;


import com.laisontech.nettester.netty.coder.Util;

import java.io.UnsupportedEncodingException;

public class DeleteFriendPacket extends Packet {
    private String sendUser;
    private String deleteUserName;

    public DeleteFriendPacket(String sendUser, String deleteUserName) {
        this.sendUser = sendUser;
        this.deleteUserName = deleteUserName;
    }
    public DeleteFriendPacket() {}

    @Override
    public int getPacketType() {
        return super.getPacketType();
    }

    @Override
    public void setPacketType(int packetType) {
        super.setPacketType(packetType);
    }

    @Override
    public void decode(byte[] buffer) throws UnsupportedEncodingException {
        int start_pos = 4;
        // 获取长度
        int length = Util.bytes2int(buffer, start_pos);
        start_pos += 4;

        this.sendUser = new String(buffer, start_pos, length, "UTF-8");
        start_pos += length;


        length = Util.bytes2int(buffer, start_pos);

        start_pos += 4;
        this.deleteUserName = new String(buffer, start_pos, length, "UTF-8");
    }

    @Override
    public byte[] encode() throws UnsupportedEncodingException {
        int sendUserLength = this.sendUser.getBytes("UTF-8").length;
        int receiveUserLength = this.deleteUserName.getBytes("UTF-8").length;
        int totalLength = 4 + 4 + sendUserLength + 4 + receiveUserLength;
        byte[] buffer = new byte[totalLength];
        int offset = 0;

        // add packet type to buffer
        System.arraycopy(Util.int2bytes(Util.PACKET_DELETE_FRIEND), 0, buffer, offset, 4);
        offset += 4;

        // the four bytes store send user name length
        System.arraycopy(Util.int2bytes(sendUserLength), 0, buffer, offset, 4);
        offset += 4;

        // copy send user to buffer
        System.arraycopy(sendUser.getBytes("UTF-8"), 0, buffer, offset, sendUserLength);
        offset += sendUserLength;

        // the four bytes store receive user name length
        System.arraycopy(Util.int2bytes(receiveUserLength), 0, buffer, offset, 4);
        offset += 4;

        //copy receive user to buffer
        System.arraycopy(this.deleteUserName.getBytes("UTF-8"), 0, buffer, offset, receiveUserLength );
        offset += receiveUserLength;
        return buffer;
    }

    @Override
    public void process() {
        super.process();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
