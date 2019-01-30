package com.netty.handler;

import java.nio.charset.Charset;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 *  待处理的数据必须和编码器和解码器匹配,否则不进行处理,传给下一个
 *  进行数据处理的时候,必须判断数据是否足够
 * @author Administrator
 *
 */
public class MyHandler extends SimpleChannelInboundHandler<Long>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
		
		System.out.println("服务器收到消息:"+msg);
		
		//回应客户端
		//ctx.writeAndFlush(3234234L);
		
		
		//消息可以发送,但是没有相应的Encoder进行处理,直接发送
		ctx.writeAndFlush(Unpooled.copiedBuffer("hello",Charset.forName("utf-8")));
	}

}
