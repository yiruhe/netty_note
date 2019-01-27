namespace java thrift.generated

typedef i16 short
typedef i32 int
typedef bool boolean
typedef string String


struct Person{
  1: optional String username;
  2: optional int age;
  3: optional boolean married;



}

exception DataException{
    1: optional String msg;
    2: optional string callStack;
    3: optional String date;


}

service PersonService{

    Person getPersonByUsernmae(1: required String username) throws (1:DataException dataException)


}