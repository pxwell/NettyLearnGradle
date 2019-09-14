package cn.pxwell.netty.rpc.learn8;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {

    private Server server;

    private void  start() throws IOException {
            server = ServerBuilder.forPort( 8899 )
                    .addService( new StudentServiceImpl() )
                    .build().start();
        System.out.println("Grpc Server Started");

        Runtime.getRuntime().addShutdownHook( new Thread( ()->{
            System.out.println("关闭JVM");
            this.stop();
        } ) );


    }

    private void stop(){
        if (null!=this.server){
            this.server.shutdown();
        }
    }

    private void awaitTermination() throws InterruptedException {
        if (null != this.server){
            this.server.awaitTermination();
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        GrpcServer server = new GrpcServer();
        server.start();
        server.awaitTermination();
    }
}
