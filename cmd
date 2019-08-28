~ Run Mongo shell
docker-compose up -d
docker exec -it mongodb mongo -u root -p

~Add user
db.createUser({user: "app-owner",pwd: "App0wn3r",roles: [{ role: "readWrite", db: "sinthoma" }]});

~Dependency tree
mvn dependency:tree -Dincludes=com.sk -DappendOutput=true -DoutputType=dot -DappendOutput=true -DoutputFile=dependency-tree.dot

~Kill Windows Task by Port Number
netstat -ano | findstr :8095
taskkill /PID 12345 /F
