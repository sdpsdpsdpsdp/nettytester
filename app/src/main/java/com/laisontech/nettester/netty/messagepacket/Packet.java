package com.laisontech.nettester.netty.messagepacket;

import java.io.UnsupportedEncodingException;

import io.netty.channel.Channel;

public class Packet {
	
	private int packetType;

	public int getPacketType() {
		return packetType;
	}

	public void setPacketType(int packetType) {
		this.packetType = packetType;
	}

	
	public void decode(byte[] buffer) throws UnsupportedEncodingException {
		throw new RuntimeException("not implementation");
	}
	
	public byte[] encode() throws UnsupportedEncodingException {
		throw new RuntimeException("not implementation");
	}
	
	public void process() {
		throw new RuntimeException("not implementation");
	}
}
