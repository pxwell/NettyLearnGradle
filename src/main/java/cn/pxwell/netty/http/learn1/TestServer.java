package cn.pxwell.netty.http.learn1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {

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
                    .childHandler(new TestServerInitializer());


            ChannelFuture channelFuture =  serverBootstrap.bind(8899).sync();
            //关闭监听
            channelFuture.channel().closeFuture().sync();

        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }



    }
}
