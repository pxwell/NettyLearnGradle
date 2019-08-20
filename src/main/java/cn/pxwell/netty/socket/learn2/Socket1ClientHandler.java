package cn.pxwell.netty.socket.learn2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;
import java.util.UUID;

public class Socket1ClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println( ctx.channel().remoteAddress() );
        System.out.println("client output :" +msg);
        ctx.channel().writeAndFlush( "from Client "+ LocalDateTime.now() );
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush( "来自于客户端的问候" );
        /*super.channelActive( ctx );*/

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
