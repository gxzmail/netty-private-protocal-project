package com.xzguo.netty.codec;

import java.io.IOException;

import org.jboss.marshalling.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;

public class MarshallingEncode {
	Logger logger = LoggerFactory.getLogger(MarshallingEncode.class);
	private Marshaller marshaller;
//	用来占位，表示value值得长度
	byte[] LENGTH_PLACEHOLDER;
	
	public MarshallingEncode() throws Exception {
		marshaller = MarshallingCodecFactory.buildMarshalling();
		LENGTH_PLACEHOLDER = new byte[4];
	}
	
	public void encode(ByteBuf sendBuf, Object value) throws IOException {
		//必须要知道当前的数据位置是哪: 起始数据位置
		//value长度属性的位置索引
		int lengthPos = sendBuf.writerIndex();
		//占位写操作:先写一个4个字节的空的内容，记录在起始数据位置，用于设置内容长度
		sendBuf.writeBytes(LENGTH_PLACEHOLDER);
		//需要
		ChannelByteBufferOutput out = new ChannelByteBufferOutput(sendBuf);
		marshaller.start(out);
		marshaller.writeObject(value);
		marshaller.finish();
		//总长度(结束位置) - 初始化长度(起始位置) - 预留的长度  = value内容长度
		int endPos = sendBuf.writerIndex();
		sendBuf.setInt(lengthPos, endPos - lengthPos - 4);
		marshaller.close();
	}
}
