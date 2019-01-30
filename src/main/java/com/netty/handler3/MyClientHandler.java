package com.netty.handler3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyClientHandler extends SimpleChannelInboundHandler<PersonProtocol>{

	private int count = 0 ;
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
		
		byte[] content = msg.getContent();
		
		count++;
		System.out.println("接受到服务端消息总数"+count);
		
		System.out.println("消息--->:"+new String(content,"utf-8"));
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		
		for (int i = 0; i < 10; i++) {
			String mString ="send from client";
			byte[] content = mString.getBytes("utf-8");
			int length = content.length;
			
			PersonProtocol personProtocol = new PersonProtocol();
			personProtocol.setContent(content);
			personProtocol.setLength(length);
			
			
			ctx.writeAndFlush(personProtocol);
		}
	
	}
	
	

}
