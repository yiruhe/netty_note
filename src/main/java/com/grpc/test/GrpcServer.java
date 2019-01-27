package com.grpc.test;

import com.grpc.impl.StudentImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {
    public static void main(String[] args) throws Exception {


        Server start = ServerBuilder.forPort(8899).addService(new StudentImpl()).build().start();


        System.out.println("服务启动成功!");

        //阻塞
        start.awaitTermination();
    }

}
