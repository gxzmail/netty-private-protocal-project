package com.xzguo.netty.codec;

import java.io.IOException;

import org.jboss.marshalling.ByteInput;

import io.netty.buffer.ByteBuf;

public class ChannelBufferByteInput implements ByteInput {

	private ByteBuf byteBuf;
	public ChannelBufferByteInput(ByteBuf byteBuf) {
		// TODO Auto-generated constructor stub
		this.byteBuf = byteBuf;
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int read() throws IOException {
		if (byteBuf.isReadable()) {
			return byteBuf.readByte() & 0xff;//Java的基本数据类型内存中都有一个固定的位数(内存分配空间)，如byte占8位，int占32位等。正因如此，当把一个低精度的数据类型转成一个高精度的数据类型时，必然会涉及到如何扩展位数的问题。这里有两种解决方案：                                       
		} else {                             //（1）补零扩展：填充一定位数的0。                                                                                                                                 
			return -1;                       //（2）补符号位扩展：填充一定位数的符号位（非负数填充0，负数填充1）。                                                                                                               
		}
	}

	@Override
	public int read(byte[] b) throws IOException {
		
		return read(b, 0, b.length);
		
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		if (byteBuf.isReadable()) {
			int readLength = Math.min(len , b.length);
			byteBuf.readBytes(b, off, readLength);
			return readLength;
		}
		return -1;
	}

	@Override
	public int available() throws IOException {
		// TODO Auto-generated method stub
		return (byteBuf.isReadable()) ? byteBuf.readableBytes(): -1;
		
	}

	@Override
	public long skip(long n) throws IOException {
		long skipLength = Math.min(n, byteBuf.readableBytes() & 0xffffffff);
		byteBuf.skipBytes((int)skipLength);
		return skipLength;
	}

}
