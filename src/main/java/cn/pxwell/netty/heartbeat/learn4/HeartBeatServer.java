package cn.pxwell.netty.heartbeat.learn4;

import cn.pxwell.netty.http.learn1.TestServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class HeartBeatServer {

    public static void main(String[] args) throws InterruptedException {

        //EventLoopGroup 线程组
        //接收连接死循环
        EventLoopGroup bossGroup = new NioEventLoopGroup(  );
        //处理连接并且返回客户端数据
        EventLoopGroup workerGroup = new NioEventLoopGroup(  );


        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group( bossGroup,workerGroup )
                    .channel( NioServerSocketChannel.class )
                    .handler( new LoggingHandler( LogLevel.INFO ) )
                    .childHandler( new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast( new IdleStateHandler( 5,7,10 , TimeUnit.SECONDS ) );
                            pipeline.addLast( new HeartBeatServerHandler() );
                        }
                    } );


            ChannelFuture channelFuture =  serverBootstrap.bind(8899).sync();
            //关闭监听
            channelFuture.channel().closeFuture().sync();

        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }



    }
}
