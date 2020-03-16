package com.laisontech.nettester.netty.coder;

public class Util {
	
	public static final int PACKET_MESSAGE = 1;
	public static final int PACKET_ADD_FRIEND = 2;
	public static final int PACKET_JOIN_GROUP = 3;
	public static final int PACKET_SEARCH_FRIEND = 4;
	public static final int PACKET_DELETE_FRIEND = 5;

	public static byte[] int2bytes(int num) {
		byte[] data = new byte[4];
		data[3] = (byte)(num & 0xff);
		data[2] = (byte)((num >> 8) & 0xff);
		data[1] = (byte)((num >> 16) & 0xff);
		data[0] = (byte)(((num >> 24)) & 0xff);
		return data;
	}

	public static int bytes2int(byte[] buffer, int start_pos) {
		int value = 0;
		for(int i = 0; i < 4; i++) {
            int shift= (3-i) * 8;
            value +=(buffer[start_pos+i] & 0xFF) << shift;
        }
		return value;
	}

}
