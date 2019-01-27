package com.thrift;

import org.apache.thrift.TException;
import thrift.generated.DataException;
import thrift.generated.Person;
import thrift.generated.PersonService;

public class PersonServiceImpl implements PersonService.Iface{
    @Override
    public Person getPersonByUsernmae(String username) throws DataException, TException {

        System.out.println("hello");

        Person person = new Person();
        person.setAge(1);
        person.setMarried(true);
        person.setUsername(username);

        return  person;
    }
}
