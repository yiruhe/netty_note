package com.netty.handler3;

import io.grpc.netty.shaded.io.netty.channel.ChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


public class MyClient {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup event = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(event).channel(NioSocketChannel.class)
        .handler(new io.netty.channel.ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				
				ChannelPipeline pipeline = ch.pipeline();
				pipeline.addLast(new MyPersonProtocolEncoder());
				pipeline.addLast(new MyPersonProtocelDeCoder());
				pipeline.addLast(new MyClientHandler());
			}
		});


        ChannelFuture localhost = bootstrap.connect("localhost", 8899).sync();

        localhost.channel().closeFuture().sync();

        event.shutdownGracefully();


    }
}
