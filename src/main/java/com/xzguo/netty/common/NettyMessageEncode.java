package com.xzguo.netty.common;

import java.util.List;

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
			
		}
		
	
	}
	

}
