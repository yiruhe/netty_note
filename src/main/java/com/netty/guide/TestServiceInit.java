package com.netty.guide;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestServiceInit extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {


        ChannelPipeline  pipleLine  =ch.pipeline();

        pipleLine.addLast("httpServerCodec",new HttpServerCodec());
        pipleLine.addLast("testHttpServerHandler",new TestHttpServerHandler());
    }
}
