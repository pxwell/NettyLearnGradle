package cn.pxwell.netty.rpc.learn6.server;

import cn.pxwell.netty.rpc.learn6.entity.DataInfo;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class ProtobufServer {
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
                    .childHandler( new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline  = ch.pipeline();
                            pipeline.addLast( new ProtobufVarint32FrameDecoder() );
                            pipeline.addLast( new ProtobufDecoder( DataInfo.MyMessage.getDefaultInstance() ) );
                            pipeline.addLast( new ProtobufVarint32LengthFieldPrepender() );
                            pipeline.addLast( new ProtobufEncoder() );
                            pipeline.addLast( new ProtobufServerHandler() );
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
