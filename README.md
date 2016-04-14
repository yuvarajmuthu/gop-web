To run the application
mvn spring-boot:run

GET request
http://localhost:8080/getDistrict
http://localhost:8080/getShape
http://localhost:8080/getLatAndLong
http://localhost:8080/party/getParty/{partyName}
http://localhost:8080/group/getGroup/{groupName}
http://localhost:8080/position/getPosition/{positionName}
http://localhost:8080/person/getPerson/{firstName}

POST request
http://localhost:8080/party/addParty
Sample data:
{"partyName":"","ideology":"","establishedDate":"","registeredAddress":""}

http://localhost:8080/group/addGroup
http://localhost:8080/position/addPosition
http://localhost:8080/person/addPerson