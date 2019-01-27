package com.netty.ping;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        System.out.println(11);
        if(evt instanceof IdleStateEvent){

            IdleStateEvent event = (IdleStateEvent) evt;

            String eventType = null;

            switch (event.state()){
                case READER_IDLE:
                    eventType = "读空间";
                    break;
                case WRITER_IDLE:
                    eventType = "写空闲";
                    break;
                case ALL_IDLE:
                    eventType = "读写空";
                    break;

            }

            System.out.println("超时事件"+eventType);

            ctx.channel().close();

        }

    }
}
