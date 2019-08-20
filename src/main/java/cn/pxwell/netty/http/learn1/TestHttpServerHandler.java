package cn.pxwell.netty.http.learn1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;


public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if(msg instanceof  HttpRequest){
            HttpRequest request = (HttpRequest) msg;
            System.out.println("请求方法名"+request.method());

            String uri = request.uri();
            if (uri.indexOf( "favicon.ico" )!=-1){
                System.out.println("请求favicon.ico");
            }
        }

        ByteBuf content = Unpooled.copiedBuffer( "HelloWord!" , CharsetUtil.UTF_8 );
        FullHttpResponse response = new DefaultFullHttpResponse( HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,content);
        response.headers().set( HttpHeaderNames.CONTENT_TYPE,"text/plain" );
        response.headers().set( HttpHeaderNames.CONTENT_LENGTH ,content.readableBytes());

        ctx.writeAndFlush( response );

    }




    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel registered");
        super.channelRegistered( ctx );
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel unregistered");
        super.channelUnregistered( ctx );
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel active");
        super.channelActive( ctx );
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler Removed");
        super.handlerRemoved( ctx );
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel inactive");
        super.channelInactive( ctx );
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler added");
        super.handlerAdded( ctx );
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       // cause.printStackTrace();
        super.exceptionCaught( ctx, cause );
    }
}
