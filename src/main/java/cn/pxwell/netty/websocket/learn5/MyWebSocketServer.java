package cn.pxwell.netty.websocket.learn5;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class MyWebSocketServer {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup(  );
        EventLoopGroup worksGrop = new NioEventLoopGroup(  );
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group( bossGroup,worksGrop ).channel( NioServerSocketChannel.class )
                    .handler( new LoggingHandler( LogLevel.INFO ) )
                    .childHandler( new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline  = ch.pipeline();
                            pipeline.addLast( new HttpServerCodec(  ) );
                            //以块的方式写
                            pipeline.addLast( new ChunkedWriteHandler(  ) );
                            //将块合并为一个完整的请求
                            pipeline.addLast( new HttpObjectAggregator(  8192) );

                            pipeline.addLast( new WebSocketServerProtocolHandler( "/ws" ) );
                            pipeline.addLast( new MyWebSocketHandler() );
                        }
                    } );

            ChannelFuture future = serverBootstrap.bind(8899).sync();
            future.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            worksGrop.shutdownGracefully();
        }



    }
}
