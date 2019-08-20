~ Run Mongo shell
docker exec -it mongodb mongo

~Add user
db.createUser({user: "app-owner",pwd: "App0wn3r",roles: [{ role: "readWrite", db: "sinthoma" }]});