package cn.pxwell.netty.rpc.learn6.client;

import cn.pxwell.netty.rpc.learn6.entity.DataInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;


public class ProtobufClient {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(  );

        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group( eventLoopGroup )
                    .channel( NioSocketChannel.class )
                    .handler( new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast( new ProtobufVarint32FrameDecoder() );
                            pipeline.addLast( new ProtobufDecoder( DataInfo.MyMessage.getDefaultInstance() ) );
                            pipeline.addLast( new ProtobufVarint32LengthFieldPrepender() );
                            pipeline.addLast( new ProtobufEncoder() );
                            pipeline.addLast( new ProtobufClientHandler() );
                        }
                    } );

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1",8899).sync();
            //Channel channel = channelFuture.channel();
            channelFuture.channel().close().sync();


        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
