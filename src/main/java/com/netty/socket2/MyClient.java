package com.netty.socket2;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MyClient {


    public static void main(String[] args) {
        //处理请求
        EventLoopGroup workerGroup = new NioEventLoopGroup();
       try{
           //启动服务端的类
           Bootstrap serverBootstrap = new Bootstrap();

           //定义通道,定义处理器
           serverBootstrap.group(workerGroup).channel(NioSocketChannel.class)
                   .handler(new MyChatClientInit());

           //绑定端口
           Channel channel = serverBootstrap.connect("localhost", 8899).sync().channel();

           BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

           for(;;){
             //  System.out.println(bufferedReader.readLine());
               channel.writeAndFlush(bufferedReader.readLine()+"\r\n");
           }

       }catch (Exception e){

           e.printStackTrace();
       }finally {
           workerGroup.shutdownGracefully();
       }
    }
}
