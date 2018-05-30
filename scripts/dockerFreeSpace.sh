# working with docker create a lots of images and containers.
# we have to remove all of them to save disk space.
!/bin/bash
# Delete all containers
docker rm $(docker ps -a -q)
# Delete all images
docker rmi $(docker images -q)