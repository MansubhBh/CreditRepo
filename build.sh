gradle clean build

docker build --no-cache -t creditscore .
docker tag creditscore:latest aaseek/creditscore:1.0
docker push aaseek/creditscore:1.0