package cn.pxwell.netty.rpc.learn8;

import cn.pxwell.netty.proto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;

public class GrpcClient {

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress(  "localhost",8899)
                .usePlaintext(true)
                .build();
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc
                .newBlockingStub( managedChannel );

        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub( managedChannel );

        MyResponse myResponse =  blockingStub.getRealNameByUsername( MyRequest.newBuilder().setUsername( "hh" ).build() );
        System.out.println(myResponse.getRealname());
        System.out.println("-------------------------------------");
         Iterator<StudentResponse>iterator =  blockingStub.getStudentsByAge( StudentRequest.newBuilder().setAge( 20 ).build());
        while (iterator.hasNext()){
            StudentResponse response = iterator.next();
            System.out.println(response.getName()+" "+response.getAge() +" "+response.getCity());
        }
        System.out.println("-------------------------------------");

       /* StreamObserver<StudentResponseList> studentResponseListStreamObserver = new StreamObserver<StudentResponseList>() {
            @Override
            public void onNext(StudentResponseList value) {
                value.getStudentResponseList().forEach( studentResponse -> {
                    System.out.println(studentResponse.getName());
                    System.out.println(studentResponse.getAge());
                    System.out.println(studentResponse.getCity());
                } );
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("completed!");
            }
        };

        StreamObserver<StudentRequest> studentRequestStreamObserver = stub.getStudentsWrappersByAges(studentResponseListStreamObserver );
        studentRequestStreamObserver.onNext( StudentRequest.newBuilder().setAge( 32 ).build() );
        studentRequestStreamObserver.onNext( StudentRequest.newBuilder().setAge( 32 ).build() );
        studentRequestStreamObserver.onNext( StudentRequest.newBuilder().setAge( 32 ).build() );
        studentRequestStreamObserver.onCompleted();

        try {
            Thread.sleep( 5000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        */

       StreamObserver<StreamRequest> streamRequestStreamObserver = stub.biTalk( new StreamObserver<StreamResponse>() {
           @Override
           public void onNext(StreamResponse value) {
               System.out.println(value.getResponseInfo());
           }

           @Override
           public void onError(Throwable t) {

           }

           @Override
           public void onCompleted() {
               System.out.println("onCompleted");
           }
       } );

       for (int i = 0 ; i < 10 ; i ++){
           streamRequestStreamObserver.onNext( StreamRequest.newBuilder().setRequestInfo( i+"" ).build() );
           Thread.sleep( 1000 );
       }


    }
}
