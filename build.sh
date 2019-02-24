#!/bin/bash

gradle clean build

buildNumber="$1"
if [ "$buildNumber" == "" ];
then
  buildNumber="0"
fi;

VER="1.1.${buildNumber}"

docker build --no-cache -t creditscore .
docker tag creditscore:latest aaseek/creditscore:$VER
docker push aaseek/creditscore:$VER
