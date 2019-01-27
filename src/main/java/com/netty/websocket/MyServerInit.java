package com.netty.websocket;

import com.netty.ping.MyServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class MyServerInit extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();
       pipeline.addLast(new HttpServerCodec());
       pipeline.addLast(new ChunkedWriteHandler());
       pipeline.addLast(new HttpObjectAggregator(8192));
       //webSocket uri地址
       pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
       pipeline.addLast(new MyHandler());
    }
}
