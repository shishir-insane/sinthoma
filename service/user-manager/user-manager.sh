#!/bin/sh

APP_PATH=`pwd`

cd $APP_PATH
./mvnw clean package -DskipTests -Prelease