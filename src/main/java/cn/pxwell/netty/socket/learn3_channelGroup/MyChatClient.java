package cn.pxwell.netty.socket.learn3_channelGroup;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyChatClient {

    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(  );

        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group( eventLoopGroup )
                    .channel( NioSocketChannel.class )
                    .handler( new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast( new DelimiterBasedFrameDecoder( 4096, Delimiters.lineDelimiter() ) );
                            pipeline.addLast( new StringDecoder( CharsetUtil.UTF_8 ) );
                            pipeline.addLast( new StringEncoder( CharsetUtil.UTF_8 ) );
                            pipeline.addLast( new MyChatClientHandler() );
                        }
                    } );

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1",8899).sync();
            Channel channel = channelFuture.channel();
            BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );

            for (;;){
                channel.writeAndFlush( br.readLine() +"\r\n" );
            }
          //  channelFuture.channel().closeFuture().sync();


        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
