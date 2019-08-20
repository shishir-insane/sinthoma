#!/bin/sh

./mvnw clean package -DskipTests -Prelease

java -cp app:app/lib/* com.sk.sinthoma.user.UserManagerApplication