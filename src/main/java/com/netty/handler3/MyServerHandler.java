package com.netty.handler3;

import java.nio.charset.Charset;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.opencensus.stats.Aggregation.Count;

public class MyServerHandler extends SimpleChannelInboundHandler<PersonProtocol>{

	//消息总数
	private int count = 0;
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
		int length = msg.getLength();
		byte[] content = msg.getContent();
		
		System.out.println("服务端收到的消息"+ new String(content,"utf-8"));
		
		count++;
		
		System.out.println("接受到的消息总数:"+count);
		
	 String reString = "响应消息";
	 byte[] resContent = reString.getBytes("utf-8");
	 int resLength = resContent.length;
	 
	 PersonProtocol personProtocol = new PersonProtocol();
	 personProtocol.setLength(resLength);
	personProtocol.setContent(resContent);
	
	ctx.writeAndFlush(personProtocol);
		
	}

}
