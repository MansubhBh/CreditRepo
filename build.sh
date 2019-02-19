gradle clean build

docker build --no-cache -t creditscore .
docker tag creditscore:latest aaseek/creditscore:1.1
docker push aaseek/creditscore:1.1
