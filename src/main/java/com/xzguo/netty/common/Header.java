package com.xzguo.netty.common;

import java.util.*;

public class Header {

	int crcCode = 0xABEF0101;
	int length;
	long sessionId;
	Byte type;
	byte priority;
	Map<String, Object> attachment;
	
	public int getCrcCode() {
		return crcCode;
	}
	public void setCrcCode(int crcCode) {
		this.crcCode = crcCode;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public long getSessionId() {
		return sessionId;
	}
	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	public byte getPriority() {
		return priority;
	}
	public void setPriority(byte priority) {
		this.priority = priority;
	}
	public Map<String, Object> getAttachment() {
		return attachment;
	}
	public void setAttachment(Map<String, Object> attachment) {
		this.attachment = attachment;
	}
	
	@Override
	public String toString() {
		return "Header [crcCode=" + crcCode + ", length=" + length + ", sessionId=" + sessionId + ", type=" + type
				+ ", priority=" + priority + ", attachment=" + attachment + "]";
	}
	
	
}
