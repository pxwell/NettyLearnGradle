package cn.pxwell.netty.http.jhst;

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
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class JHServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(  );
        EventLoopGroup workersGroup = new NioEventLoopGroup(  );


        try{

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group( bossGroup,workersGroup )
                    .channel( NioServerSocketChannel.class )
                    .handler( new LoggingHandler( LogLevel.DEBUG ) )
                    .childHandler( new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast( "httpServerCodec",new HttpServerCodec(  ) );
                            //pipeline.addLast(new HeartBeatServerHandler() );
                            pipeline.addLast(new HttpObjectAggregator(1048576));
                            pipeline.addLast( new JHServer1Handler() );
                        }
                    } );
            ChannelFuture channelFuture =  serverBootstrap.bind(8899).sync();
            //关闭监听
            channelFuture.channel().closeFuture().sync();

        }finally {
            bossGroup.shutdownGracefully();
            workersGroup.shutdownGracefully();
        }
    }
}
