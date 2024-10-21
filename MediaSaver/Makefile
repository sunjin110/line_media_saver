WORK_DIR=$(shell pwd)

build:
	./gradlew nativeCompile

image/build/lambda:
	@docker build -t media_saver -f Dockerfile.lambda --platform=linux/amd64 .

image/build:
	@docker build -t media_server -f Dockerfile --platform=linux/arm64 .

image/run:
	@docker run -t media_server:latest

run/lambda:
	java -jar build/libs/lambda.jar
