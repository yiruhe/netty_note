package com.thrift;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import thrift.generated.Person;
import thrift.generated.PersonService;

public class ThriftClient {

    public static void main(String[] args) throws  Exception{

        TFramedTransport localhost = new TFramedTransport(new TSocket("localhost", 8899));

        TCompactProtocol tCompactProtocol = new TCompactProtocol(localhost);

        PersonService.Client client = new PersonService.Client(tCompactProtocol);

        localhost.open();

        Person person = client.getPersonByUsernmae("测试");

        System.out.println(person.getUsername());

        localhost.close();


    }

}
