Please refer Wiki section for more information
------------------------------------------------
Lucene: Used RAMDirectory for now and before using this, add person the database using any RestClient. I've used Postman restclient

1) http://localhost:8080/person/addPerson
Body: {userName:"userName", firstName:"firstName", lastName:"lastName", emailId:"yourgmail@gmail.com"}
Content-Type : application/json

Search the person using the lucent
2) http://localhost:8080/person/getLucenePerson/a
Right now, it's not returning the actual content , but, it's returning the document with the data. Need to research on why it's not returning the data.
------------------------------------------------------