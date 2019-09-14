package cn.pxwell.netty.socket.learn3_channelGroup;

import cn.pxwell.netty.heartbeat.learn4.HeartBeatServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

public class MyChatServer {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(  );
        EventLoopGroup workerGroup = new NioEventLoopGroup(  );
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group( bossGroup,workerGroup )
                    .channel( NioServerSocketChannel.class )
                    .handler( new LoggingHandler( LogLevel.INFO ) )
                    .childHandler( new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline  = ch.pipeline();
                            pipeline.addLast( new DelimiterBasedFrameDecoder( 4096, Delimiters.lineDelimiter()));
                            pipeline.addLast( new StringDecoder( CharsetUtil.UTF_8 ) );
                            pipeline.addLast( new StringEncoder( CharsetUtil.UTF_8 ) );

                            pipeline.addLast( new IdleStateHandler( 4,4,10 , TimeUnit.SECONDS ) );
                            pipeline.addLast( new HeartBeatServerHandler() );
                            pipeline.addLast( new MyChatServerHandler() );
                        }
                    } );

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
