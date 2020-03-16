package com.laisontech.nettester.netty.messagepacket;


import com.laisontech.nettester.netty.coder.Util;

import java.io.UnsupportedEncodingException;


public class MessagePacket extends Packet {

    private static final String TAG = "MessagePacket";
    private String sendUser;
    private String receiveUser;
    private String message;
    private int messagetype;

@Override
public void decode(byte[] buffer) throws UnsupportedEncodingException {
    int start_pos = 4;
    int length = Util.bytes2int(buffer, start_pos);
    start_pos += 4;
    this.sendUser = new String(buffer, start_pos, length, "UTF-8");
    start_pos += length;


    length = Util.bytes2int(buffer, start_pos);

    start_pos += 4;
    this.receiveUser = new String(buffer, start_pos, length, "UTF-8");
    start_pos += length;


    length = Util.bytes2int(buffer, start_pos);

    start_pos += 4;
    this.message = new String(buffer, start_pos, length, "UTF-8");
    start_pos += length;


    this.messagetype = Util.bytes2int(buffer, start_pos);
    System.out.println();
}

@Override
public byte[] encode() throws UnsupportedEncodingException {
    int sendUserLength = this.sendUser.getBytes("UTF-8").length;
    int receiveUserLength = this.receiveUser.getBytes("UTF-8").length;
    int messageLength = this.message.getBytes("UTF-8").length;
    int totalLength = 4 + 4 + sendUserLength + 4 + receiveUserLength + 4 + messageLength + 4;
    byte[] buffer = new byte[totalLength];
    int offset = 0;

    // add packet type to buffer
    System.arraycopy(Util.int2bytes(Util.PACKET_MESSAGE), 0, buffer, offset, 4);
    offset += 4;

    System.arraycopy(Util.int2bytes(sendUserLength), 0, buffer, offset, 4);
    offset += 4;
    // copy send user to buffer
    System.arraycopy(sendUser.getBytes("UTF-8"), 0, buffer, offset, sendUserLength);
    offset += sendUserLength;

    System.arraycopy(Util.int2bytes(receiveUserLength), 0, buffer, offset, 4);
    offset += 4;

    System.arraycopy(receiveUser.getBytes("UTF-8"), 0, buffer, offset, receiveUserLength );
    offset += receiveUserLength;

    System.arraycopy(Util.int2bytes(messageLength), 0, buffer, offset, 4);
    offset += 4;

    System.arraycopy(message.getBytes("UTF-8"), 0, buffer, offset, messageLength);
    offset += messageLength;
    // copy sign to buffer

    System.arraycopy(Util.int2bytes(messagetype), 0, buffer, offset, 4);
    return buffer;
}
public MessagePacket() {}

public MessagePacket(String sendUser, String receiveUser, String message, int messagetype) {
    super();
    this.sendUser = sendUser;
    this.receiveUser = receiveUser;
    this.message = message;
    this.messagetype = messagetype;
}

public String getSendUser() {
    return sendUser;
}

public void setSendUser(String sendUser) {
    this.sendUser = sendUser;
}

public String getReceiveUser() {
    return receiveUser;
}

public void setReceiveUser(String receiveUser) {
    this.receiveUser = receiveUser;
}

public String getMessage() {
    return message;
}

public void setMessage(String message) {
    this.message = message;
}

public int getMessagetype() {
    return messagetype;
}

public void setMessagetype(int messagetype) {
    this.messagetype = messagetype;
}

public void process() {
    String strmsg = "";
    if (this.receiveUser.equals("")) {
        strmsg="[全体-"+this.getSendUser()+"：]"+this.getMessage();
    } else {
        strmsg="[私聊-"+this.getSendUser()+"：]"+this.getMessage();
    }
//    RefreshInterface.refresh(strmsg, 1, this);
}

@Override
public String toString() {
    return "MessagePacket{" +
            "sendUser='" + sendUser + '\'' +
            ", receiveUser='" + receiveUser + '\'' +
            ", message='" + message + '\'' +
            ", messageType='" + messagetype + '\'' +
            '}';
    }
}
