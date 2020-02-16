package com.xzguo.netty.common;

import java.util.List;
import java.util.Map;

import com.xzguo.netty.codec.*;

import io.netty.buffer.*;
import io.netty.channel.*;
import io.netty.handler.codec.*;

public class NettyMessageEncode extends MessageToByteEncoder<Message> {
	
	private MarshallingEncode marshallingEncode;
	public NettyMessageEncode() throws Exception {
		marshallingEncode = new MarshallingEncode();
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf sendBuf) throws Exception {
		if(msg.getHeader() == null || msg == null) {
			throw new Exception("the message or message header is null");
		}
		
		Header head = msg.getHeader();
		sendBuf.writeInt(head.getCrcCode());
		sendBuf.writeInt(head.getLength()); //先占位
		sendBuf.writeLong(head.getSessionId());
		sendBuf.writeByte(head.getType());
		sendBuf.writeByte(head.getPriority());
		/*编码规则：
		 * 1. 先编码附件attchment的个数
		 * 2. 对attchment中的每个key-value内容编码：
		 * 2.1 先编码key的长度
		 * 2.2 再编码key的内容
		 * 2.3 编码value的长度
		 * 2.4 编码value的内容
		 * */
		if (head.getAttachment().size() == 0) {
			sendBuf.writeInt(0);
		} else if (head.getAttachment().size() > 0) {
			sendBuf.writeInt(head.getAttachment().size());
			String key = null;
			Object value = null;
			byte[] keyArray = null;
//			byte[] valueArray = null;
			for (Map.Entry<String, Object> param : head.getAttachment().
					entrySet()) {
				key = param.getKey();
				keyArray = key.getBytes("UTF-8"); //编码key
				sendBuf.writeInt(keyArray.length);//写入编码后key的长度
				sendBuf.writeBytes(keyArray);	 //写入编码后key的内容
				//要编码value的长度，首先要编码value的内容，然后才可以
				//算出value的二进制长度；value是object类型，所以需要
				//使用marshalling来实现序列化
				value =param.getValue();
				marshallingEncode.encode(sendBuf, value);
			}
		} 
		/*3.开始body进行编码：
		 *3.1 先编码body的长度
		 *3.2 再编码body的内容 
		 * */
		
		byte[] bodyArray = null;
		Object body = msg.getBody();
		if (body != null) {
			marshallingEncode.encode(sendBuf, body);
		} else {
			sendBuf.writeInt(0);
		}
		//将整个message的长度写入header的length字段中
		sendBuf.setInt(4, sendBuf.readByte())
	}
	

}
