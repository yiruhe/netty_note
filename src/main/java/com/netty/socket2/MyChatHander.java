package com.netty.socket2;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChatHander extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        System.out.println(msg);

        //向服务器发送消息的Channel
        Channel channel = ctx.channel();

        System.out.println("当前大小"+channelGroup.size());
        channelGroup.forEach( ch -> {

            if(!ch.equals(channel)){

                ch.writeAndFlush(channel.remoteAddress()+"发送消息: "+msg+"\n");

            }else{
                ch.writeAndFlush("自己："+msg+"\n");
            }

        });


    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();

        System.out.println("加入一个");
        channelGroup.writeAndFlush("服务器 ="+channel.remoteAddress()+"加入");

        channelGroup.add(channel);

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();

        //netty自动删除 channelGroup中失效的 channel

        channelGroup.writeAndFlush("服务器 ="+channel.remoteAddress()+"离开\n");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {


        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println(ctx.channel().remoteAddress()+"上线!");
    }
}
