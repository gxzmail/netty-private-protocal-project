package com.xzguo.netty.codec;

import org.jboss.marshalling.ByteInput;
import org.jboss.marshalling.Unmarshaller;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.marshalling.ChannelBufferByteInput;

public class MarshallingDecoder {
	private Unmarshaller  umMarshaller;
	public MarshallingDecoder() throws Exception {
		umMarshaller = MarshallingCodecFactory.buildUmMarshalling();
	}
	
	public Object decode(ByteBuf in) throws Exception {
//		TODO
/*自己的想法：
 * 		int valueSize = in.readInt();
		byte[] valueArray = new byte[valueSize];
		in.readBytes(valueArray);
		//反序列化
		ByteArrayInputStream bis = new ByteArrayInputStream(valueArray);
		InputStreamByteInput isb = new InputStreamByteInput(bis);
		umMarshaller.start(isb);
		Object obj = umMarshaller.readObject();
		umMarshaller.finish();
		return obj;
*/ 
		int valueSize = in.readInt();
		int readIndex = in.readerIndex();
		ByteBuf byteBuf = in.slice(readIndex, valueSize);
		ByteInput input = new ChannelBufferByteInput(byteBuf);
		umMarshaller.start(input);
		Object obj = umMarshaller.readObject();
		umMarshaller.finish();
//		TODO
	
		
	}

}
