package com.laisontech.nettester.netty.messagepacket;

import android.util.Log;


import com.laisontech.nettester.netty.coder.Util;
import com.laisontech.nettester.netty.exception.PacketDecodeErrorException;

import java.io.UnsupportedEncodingException;


public class PacketFactory {
	private static final String TAG = "PacketFactory";
	public static Packet createPacketFromBuffer(byte[] buffer) throws PacketDecodeErrorException, UnsupportedEncodingException {
		int packetType = Util.bytes2int(buffer, 0);
		Log.e(TAG, "createPacketFromBuffer: "+packetType);
		if (packetType == Util.PACKET_MESSAGE) {
			Packet packet = new MessagePacket();
			packet.decode(buffer);
			return packet;
		} else if (packetType == Util.PACKET_ADD_FRIEND) {
			Packet packet = new AddFriendPacket();
			packet.decode(buffer);
            return packet;
		} else if (packetType == Util.PACKET_JOIN_GROUP) {
			Packet packet = new JoinGroupPacket();
			packet.decode(buffer);
            return packet;
		} else if(packetType == Util.PACKET_SEARCH_FRIEND){
            Packet packet = new SearchFriendPacket();
            packet.decode(buffer);
            return packet;
        } else if(packetType == Util.PACKET_DELETE_FRIEND){
            Packet packet = new DeleteFriendPacket();
            packet.decode(buffer);
            return packet;
        }
		throw new PacketDecodeErrorException("packet not exists");
	}
}
