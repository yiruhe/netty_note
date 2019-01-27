package com.protobuf.guide;

import com.google.protobuf.InvalidProtocolBufferException;

public class TestMain {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        DataInfo.Person build = DataInfo.Person.newBuilder().setName("1").setEmail("1").build();

        byte[] bytes = build.toByteArray();

        DataInfo.Person person = DataInfo.Person.parseFrom(bytes);


        System.out.println(person);

    }
}
