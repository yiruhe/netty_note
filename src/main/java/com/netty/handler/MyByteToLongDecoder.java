package com.netty.handler;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * ByteToMessageDecoder 不含有泛型
 * 
 * @author Administrator
 *
 */
public class MyByteToLongDecoder  extends ByteToMessageDecoder{

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		
		System.out.println("decode invoked");
		
		System.out.println("可读大小:"+in.readableBytes());
		
		if(in.readableBytes() > 0){
			
			out.add(in.readLong());
			
		}
		
	}

}
