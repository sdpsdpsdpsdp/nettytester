package com.laisontech.nettester.netty.messagepacket;


public class JoinGroupPacket extends Packet {
	private String sendUser;
	private String receiveGroupName;
	private int receiveGroupId;
	public void process() {
		
	}

//	@Override
//	public void decode(byte[] buffer) throws UnsupportedEncodingException {
//		int start_pos = 4;
//    	// ��ȡ����
//    	int length = Util.bytes2int(buffer, start_pos);
//    	start_pos += 4;
//        this.sendUser = new String(buffer, start_pos, length, "UTF-8");
//        start_pos += length;
//
//
//        length = Util.bytes2int(buffer, start_pos);
//
//    	start_pos += 4;
//        this.receiveUser = new String(buffer, start_pos, length, "UTF-8");
//        start_pos += length;
//
//     
//        length = Util.bytes2int(buffer, start_pos);
//
//    	start_pos += 4;
//        this.message = new String(buffer, start_pos, length, "UTF-8");
//        start_pos += length;
//
//        
//        this.messagetype = Util.bytes2int(buffer, start_pos);
//        System.out.println();
//	}
//
//	@Override
//	public byte[] encode() throws UnsupportedEncodingException {
//		int sendUserLength = this.sendUser.getBytes("UTF-8").length;
//        int receiveUserLength = this.receiveUser.getBytes("UTF-8").length;
//        int messageLength = this.message.getBytes("UTF-8").length;
//        int totalLength = 4 + 4 + sendUserLength + 4 + receiveUserLength + 4 + messageLength;
//        byte[] buffer = new byte[totalLength];
//        int offset = 0;
//
//        // add packet type to buffer
//        System.arraycopy(Util.int2bytes(Util.PACKET_MESSAGE), 0, buffer, offset, 4);
//        offset += 4;
//        
//        // ǰ�ĸ��ֽڴ洢sendUser�ĳ���
//        System.arraycopy(Util.int2bytes(sendUserLength), 0, buffer, offset, 4);
//        offset += 4;
//        // copy send user to buffer
//        System.arraycopy(sendUser.getBytes("UTF-8"), 0, buffer, offset, sendUserLength);
//        offset += sendUserLength;
//
//        System.arraycopy(Util.int2bytes(receiveUserLength), 0, buffer, offset, 4);
//        offset += 4;
//
//        System.arraycopy(receiveUser.getBytes("UTF-8"), 0, buffer, offset, receiveUserLength );
//        offset += receiveUserLength;
//
//        System.arraycopy(Util.int2bytes(messageLength), 0, buffer, offset, 4);
//        offset += 4;
//
//        System.arraycopy(message.getBytes("UTF-8"), 0, buffer, offset, messageLength);
//        offset += messageLength;
//        // copy sign to buffer
//
//        byte[] messageTypeBuffer = Util.int2bytes(messagetype);
//        System.arraycopy(messageTypeBuffer, 0, buffer, offset, 4);
//
//        return buffer;
//	}
	
}
