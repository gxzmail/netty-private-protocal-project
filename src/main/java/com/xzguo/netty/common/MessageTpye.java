package com.xzguo.netty.common;

public enum MessageTpye {
	SERVICE_REQ((byte)0),
	SERVICE_RESP((byte)1),
	ONE_WAY((byte)2),
	lOGIN_REQ((byte)3),
	LOGIN_RESP((byte)4),
	HEART_REQ((byte)5),
	HEART_RESP((byte)6); //此处要分好结尾
	
	private byte type;
//	只支持private construction function
	private MessageTpye(byte type) {
		this.type = type;
	}
	
	public byte value() {
		return this.type;
	
	}
}
