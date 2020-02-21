package com.xzguo.netty.server;

import java.util.HashMap;
import java.util.Map;

import com.xzguo.netty.codec.MarshallingDecoder;
import com.xzguo.netty.codec.MarshallingEncode;
import com.xzguo.netty.common.Header;
import com.xzguo.netty.common.Message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;


public class NettyMessageDecode extends LengthFieldBasedFrameDecoder  {
	private static final int MAXFRAMELENGTH = 1024;
	private static final int LENGTHFIELDOFFSET = 4;
	private static final int LENGTHFIELDLENGTH = 4;
	private static final int LENGTHADJUSTMENT = -8;
	private static final int INITIALBYTESTOSTRIP = 0;
	private static final boolean FAILFAST = true;
//	private Message message;
	
	private MarshallingDecoder marshallingDecoder;
	public NettyMessageDecode() throws Exception {
		super(MAXFRAMELENGTH, 
		      LENGTHFIELDOFFSET, 
		      LENGTHFIELDLENGTH, 
		      LENGTHADJUSTMENT, 
		      INITIALBYTESTOSTRIP, 
		      FAILFAST);
		marshallingDecoder = new MarshallingDecoder();
//		message = new Message();
	}
	
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		ByteBuf frame = (ByteBuf)super.decode(ctx, in);
		if (frame == null) {
			return null;
		}
		Message message = new Message();
		Header header = new Header(); //容器，用来存放读取的数据
		Map<String, Object> attachment = new HashMap<>();//容器 
		header.setCrcCode(frame.readInt());
		header.setLength(frame.readInt());
		header.setSessionId(frame.readLong());
		header.setType(frame.readByte());
		header.setPriority(frame.readByte());
		int attachmentSize = frame.readInt(); // attachment的个数
		if (attachmentSize > 0) {
			for (int i = 0; i < attachmentSize; i++) {
				int keySize = frame.readInt();
				byte[] keyArray = new byte[keySize];
				frame.readBytes(keyArray); // eaqual to 
										  //keyArray = frame.readBytes(keySize);
				String key = new String(keyArray, "UTF-8"); 
				attachment.put(key, marshallingDecoder.decode(frame));
			}
			
		} else {
			
		}
//		TODO
		return null;
		
	}
	
}
