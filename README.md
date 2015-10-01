
setup a mysql database:
mysql -u root -p
create database contacts;


run with the following command:
mvn clean package tomee:run

open your browser/rest client at:
http://localhost:8080/bruno/api/contacts/


cURL
----
curl -X POST -d '{"name":"bruno lellis","email":"bruno@bruno.com","medias":[{"type":"tw","url":"twtw.com"},{"type":"fb","url":"fbfb.com"}]}' -H "Content-Type: application/json" http://localhost:8080/bruno/api/contacts

curl -X GET http://localhost:8080/bruno/api/contacts