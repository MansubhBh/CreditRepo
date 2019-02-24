#!/bin/bash

gradle clean build

buildNumber="$1"
if [ "$buildNumber" == "" ];
then
  buildNumber="0"
fi;

VER="1.1.${buildNumber}"

docker build --no-cache -t creditscore .
docker tag creditscore:latest localhost:5000/creditscore:latest
docker push localhost:5000/creditscore:latest
#docker push aaseek/creditscore:$VER
#docker push aaseek/creditscore:latest

