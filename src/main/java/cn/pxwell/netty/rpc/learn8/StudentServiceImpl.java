package cn.pxwell.netty.rpc.learn8;

import cn.pxwell.netty.proto.*;
import io.grpc.stub.StreamObserver;

import java.util.UUID;

public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {

    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接收到客户端的消息： "+ request.getUsername());

        responseObserver.onNext( MyResponse.newBuilder().setRealname( "王五" ).build() );
        //super.getRealNameByUsername( request, responseObserver );
        responseObserver.onCompleted();
    }


    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        System.out.println("接收到客户端的消息： "+ request.getAge());
        //super.getStudentsByAge( request, responseObserver );

        responseObserver.onNext( StudentResponse.newBuilder().setName( "王五0" ).setAge( 43 ).setCity( "武汉" ).build() );
        responseObserver.onNext( StudentResponse.newBuilder().setName( "王五1" ).setAge( 43 ).setCity( "武汉" ).build() );
        responseObserver.onNext( StudentResponse.newBuilder().setName( "王五2" ).setAge( 43 ).setCity( "武汉" ).build() );
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<StudentRequest> getStudentsWrappersByAges(StreamObserver<StudentResponseList> responseObserver) {
            return new StreamObserver<StudentRequest>() {
                @Override
                public void onNext(StudentRequest value) {
                    System.out.println("onNext:" + value.getAge());
                }

                @Override
                public void onError(Throwable t) {
                    System.out.println(t.getMessage());
                }

                @Override
                public void onCompleted() {
                    StudentResponse studentResponse = StudentResponse.newBuilder().setName( "张三" ).setAge( 20 ).setCity( "xian" ).build();
                    StudentResponse studentResponse1 = StudentResponse.newBuilder().setName( "张三1" ).setAge( 20 ).setCity( "xian" ).build();
                    StudentResponse studentResponse2 = StudentResponse.newBuilder().setName( "张三2" ).setAge( 20 ).setCity( "xian" ).build();

                    StudentResponseList studentResponseList =  StudentResponseList.newBuilder().addStudentResponse( studentResponse ).addStudentResponse( studentResponse1 )
                            .addStudentResponse( studentResponse2 ).build();
                    responseObserver.onNext( studentResponseList );
                    responseObserver.onCompleted();
                }
            };

    }

    @Override
    public StreamObserver<StreamRequest> biTalk(StreamObserver<StreamResponse> responseObserver) {
        //return super.biTalk( responseObserver );
        return new StreamObserver<StreamRequest>(){
            @Override
            public void onNext(StreamRequest value) {
                System.out.println(value.getRequestInfo());
                responseObserver.onNext(StreamResponse.newBuilder().setResponseInfo( UUID.randomUUID().toString() ).build()  );
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
