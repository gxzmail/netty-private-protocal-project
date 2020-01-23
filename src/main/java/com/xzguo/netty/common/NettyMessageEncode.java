package com.xzguo.netty.common;

import java.util.List;
import java.util.Map;

import io.netty.buffer.*;
import io.netty.channel.*;
import io.netty.handler.codec.*;

public class NettyMessageEncode extends MessageToByteEncoder<Message> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf sendBuf	) throws Exception {
		// TODO Auto-generated method stub
		if(msg.getHeader() == null || msg == null) {
			throw new Exception("the message or message header is null");
		}
		
		Header head = msg.getHeader();
		sendBuf.writeInt(head.getCrcCode());
		sendBuf.writeInt(head.getLength());
		sendBuf.writeLong(head.getSessionId());
		sendBuf.writeByte(head.getType()());
		sendBuf.writeByte(head.getPriority());
		if (head.getAttachment().size() == 0) {
			sendBuf.writeInt(0);
		} else if (head.getAttachment().size() > 0) {
			sendBuf.writeInt(head.getAttachment().size());
			String key = null;
			byte[] keyArray = null;
			byte[] value = null;
			for (Map.Entry<String, Object> param : head.getAttachment().
					entrySet()) {
				keyArray = key.getBytes("UTF-8");
				sendBuf.writeInt(keyArray.length);
				
			}
		}
		
	
	}
	

}
