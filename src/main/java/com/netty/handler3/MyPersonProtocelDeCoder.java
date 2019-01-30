package com.netty.handler3;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

public class MyPersonProtocelDeCoder extends ReplayingDecoder<Void>{

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		
		System.out.println("MyPersonProtocelDeCoder");
		
		int length = in.readInt();
		
		byte[] content = new byte[length];
		
		in.readBytes(content);
		
		PersonProtocol personProtocol = new PersonProtocol();
		
		personProtocol.setLength(length);
		personProtocol.setContent(content);
		
		out.add(personProtocol);
	}

}
