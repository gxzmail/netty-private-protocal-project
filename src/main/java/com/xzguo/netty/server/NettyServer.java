package com.xzguo.netty.server;

import io.netty.bootstrap.*;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.*;
import io.netty.channel.socket.nio.*;

public class NettyServer {
	
	public void start() {
		NettyServerHandle serverHandle = new NettyServerHandle();
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		ServerBootstrap b = new ServerBootstrap();
		NettySever nettyServer = new 
		b.group(bossGroup, workGroup)
		.channel(NioServerSocketChannel.class)
		.option(ChannelOption.SO_BACKLOG, 1024)	//设置TCP缓冲区
		.option(ChannelOption.SO_REUSEADDR, true) //设置端口共享
		.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT) //为默认配置
		.childOption(ChannelOption.SO_SNDBUF, 32*1024)// 发送缓存区32K
		.childOption(ChannelOption.SO_RCVBUF, 32*1024) // 接收换成区32k
		.childOption(ChannelOption.TCP_NODELAY, true); // 有数据立刻发送
		
		b.childHandler(new NettyServerInitial());
		
		
		
		
	}
	 
		
	

}
