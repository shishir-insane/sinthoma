~ Run Mongo shell
docker exec -it mongodb mongo

~Add user
db.createUser({user: "app-owner",pwd: "App0wn3r",roles: [{ role: "readWrite", db: "sinthoma" }]});

~Dependency tree
mvn dependency:tree -Dincludes=com.sk -DappendOutput=true -DoutputType=dot -DappendOutput=true -DoutputFile=dependency-tree.dot
