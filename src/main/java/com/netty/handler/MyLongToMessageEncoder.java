package com.netty.handler;


import io.netty.handler.codec.MessageToByteEncoder;

/**
 *   Long 转为ByteBuf
 * @author Administrator
 *
 */
public class MyLongToMessageEncoder extends MessageToByteEncoder<Long>{

	@Override
	protected void encode(io.netty.channel.ChannelHandlerContext ctx, Long msg, io.netty.buffer.ByteBuf out)
			throws Exception {
		
		System.out.println("encoder 参数:"+msg);
		
		out.writeLong(msg);
		
	}

	

}
