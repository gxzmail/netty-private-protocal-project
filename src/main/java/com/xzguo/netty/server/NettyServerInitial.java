package com.xzguo.netty.server;

import io.netty.channel.*;

public class NettyServerInitial extends ChannelInitializer{

	@Override
	protected void initChannel(Channel ch) throws Exception {
		// TODO Auto-generated method stub
		ch.pipeline().addLast("encode", new NettyMessageEncode());                                                                                                                   
		
	}
	

}
