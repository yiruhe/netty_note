package com.netty.socket;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


public class MyClient {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup event = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(event).channel(NioSocketChannel.class)
        .handler(new MyClientInit());


        ChannelFuture localhost = bootstrap.connect("localhost", 8899).sync();

        localhost.channel().closeFuture().sync();

        event.shutdownGracefully();


    }
}
