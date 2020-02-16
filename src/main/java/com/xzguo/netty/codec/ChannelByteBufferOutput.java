package com.xzguo.netty.codec;

import java.io.IOException;

import org.jboss.marshalling.ByteOutput;

import io.netty.buffer.ByteBuf;

public class ChannelByteBufferOutput implements ByteOutput{
	private ByteBuf byteBuf;
	
	public ChannelByteBufferOutput(ByteBuf byteBuf) {
		this.byteBuf = byteBuf;
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flush() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(int b) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(byte[] b) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
