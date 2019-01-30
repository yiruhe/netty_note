package com.netty.handler3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyPersonProtocolEncoder extends MessageToByteEncoder<PersonProtocol>{

	@Override
	protected void encode(ChannelHandlerContext ctx, PersonProtocol msg, ByteBuf out) throws Exception {
		
		System.out.println("MyPersonProtocolEncoder");
		out.writeInt(msg.getLength());
		out.writeBytes(msg.getContent());
		
	}

}
