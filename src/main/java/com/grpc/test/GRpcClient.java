package com.grpc.test;

import com.grpc.impl.StudentServiceGrpc;
import com.grpc.proto.MyRequest;
import com.grpc.proto.MyResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class GRpcClient {

    public static void main(String[] args) {

        ManagedChannel localhost = ManagedChannelBuilder.forAddress("localhost", 8899)
                .usePlaintext()
                .build();

        StudentServiceGrpc.StudentServiceBlockingStub studentServiceBlockingStub = StudentServiceGrpc.newBlockingStub(localhost);

        StudentServiceGrpc.StudentServiceStub studentServiceStub = StudentServiceGrpc.newStub(localhost);

        //    MyResponse realNameByUsername = studentServiceBlockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("111").build());

      //  System.out.println(realNameByUsername.getRealname());

        StreamObserver<MyResponse> res = new   StreamObserver<MyResponse>(){


            @Override
            public void onNext(MyResponse value) {

                System.out.println(value);
            }

            @Override
            public void onError(Throwable t) {

                System.out.println(t);

            }

            @Override
            public void onCompleted() {

                System.out.println("完成");


            }
        };


        //发送数据
        StreamObserver<MyRequest> reqs = new  StreamObserver<MyRequest>(){


            @Override
            public void onNext(MyRequest value) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        };

        //流都是通过异步方式   异步不会等待  不等待 方法执行完毕 事件没有触发  jvm退出


      /*  StreamObserver<MyRequest> requestStreamObserver = studentServiceStub.listStudents(res);

        requestStreamObserver.onNext(MyRequest.newBuilder().setUsername("666").build());


        requestStreamObserver.onCompleted();*/




        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
