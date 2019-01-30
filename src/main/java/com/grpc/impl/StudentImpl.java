package com.grpc.impl;

import com.grpc.proto.MyRequest;
import com.grpc.proto.MyResponse;
import io.grpc.stub.StreamObserver;

public class StudentImpl  extends  StudentServiceGrpc.StudentServiceImplBase {


    /**
     * StreamObserver 向客户端返回信息
     *
     *   方法没有返回值,通过StreamObserver返回
     *
     * @param request
     * @param responseObserver
     */
    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {

        System.out.println("接收到客户端信息:"+request.getUsername());


        MyResponse tom = MyResponse.newBuilder().setRealname("tom").build();

        //        responseObserver.onError(); 代表请求出错
   //     responseObserver.onNext(checkFeature(request));//包装返回信息
   //     responseObserver.onCompleted();//结束一次请求

        //发送
        responseObserver.onNext(tom);

        responseObserver.onCompleted();

    }

    @Override
    public StreamObserver<MyRequest> listStudents(StreamObserver<MyResponse> responseObserver) {


        return  new StreamObserver<MyRequest>(){

            //onNext() 方法，每次客户端写入一个 MyRequest 到消息流时，拿到特性和其它信息。
            @Override
            public void onNext(MyRequest value) {

                System.out.println("收到消息"+value);

            }

            @Override
            public void onError(Throwable t) {

            }

            //覆写了 onCompleted() 方法（在 客户端 结束写入消息时调用），用来填充和构建我们的 MyResponse。
            //然后我们用 MyResponse 调用方法自己的的响应观察者的 onNext()，
            // 之后调用它的 onCompleted() 方法，结束服务器端的调用。

            @Override
            public void onCompleted() {
                MyResponse res = MyResponse.newBuilder().setRealname("1").build();
                MyResponse res1 = MyResponse.newBuilder().setRealname("2").build();

                responseObserver.onNext(res);
               responseObserver.onNext(res1);


                responseObserver.onCompleted();

            }
        };


    }
}
