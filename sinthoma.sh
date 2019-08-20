#!/bin/sh

#Base path
APP_HOME=`pwd`
echo 

# Output colors
NORMAL='\033[0;39m'
RED='\033[1;31m'
BLUE='033[1;34m'

log() {
  echo "$BLUE > $1 $NORMAL"
}

error() {
  echo ""
  echo "$RED >>> ERROR - $1$NORMAL"
}

up() {
  log "Building and starting services"
  docker-compose up
}

down() {
  docker-compose down
}

start() {
	log "Starting services"
	docker-compose start
}

stop() {
	log "Stopping services"
	docker-compose stop
}

top() {
  docker-compose top
}

remove() {
  log "Removing previous container $CONTAINER_NAME" && \
      docker rm -f $CONTAINER_NAME &> /dev/null || true
}

build() {
	log "Building services"
	user_manager
	if [[ "$?" -ne 0 ]] ; then
		error "Docker image build failed!"; exit 100
	fi
}

user_manager() {
  log "User Manager Docker image install started"
  cd $APP_HOME/service/user-manager
  sh mvnw clean package -Prelease

	if [[ "$?" -ne 0 ]] ; then
		error "User Manager API Docker image install failed!"; exit 101
	fi
  log "User Manager Docker image install completed"
}

help() {
  echo "-----------------------------------------------------------------------"
  echo "                      Available commands                              -"
  echo "-----------------------------------------------------------------------"
  echo -e -n "$BLUE"
  echo "   > build - To build the Docker image"
  echo "   > npm - To install NPM modules/deps"
  echo "   > bower - To install Bower/Js deps"
  echo "   > jkbuild - To build Jekyll project"
  echo "   > grunt - To run grunt task"
  echo "   > jkserve - To serve the project/blog on 127.0.0.1:4000"
  echo "   > install - To execute full install at once"
  echo "   > stop - To stop main jekyll container"
  echo "   > start - To start main jekyll container"
  echo "   > bash - Log you into container"
  echo "   > remove - Remove main jekyll container"
  echo "   > help - Display this help"
  echo -e -n "$NORMAL"
  echo "-----------------------------------------------------------------------"

}

$*