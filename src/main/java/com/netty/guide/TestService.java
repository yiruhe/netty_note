package com.netty.guide;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestService {


    public static void main(String[] args) {
        //接受请求
        EventLoopGroup  bossGroup = new NioEventLoopGroup();
        //处理请求
        EventLoopGroup workerGroup = new NioEventLoopGroup();
       try{
           //启动服务端的类
           ServerBootstrap serverBootstrap = new ServerBootstrap();

           //定义通道,定义处理器
           serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                   .childHandler(new TestServiceInit());

           //绑定端口
           ChannelFuture sync = serverBootstrap.bind(8899).sync();

           sync.channel().closeFuture().sync();

       }catch (Exception e){

           e.printStackTrace();
       }finally {
           bossGroup.shutdownGracefully();
           workerGroup.shutdownGracefully();
       }
    }
}
