WORK_DIR=$(shell pwd)

native/build:
	./gradlew nativeCompile

native/run:
	@make b
	@./build/native/nativeCompile/main-native

jar/build:
	@./gradlew main

jar/run:
	@java -jar build/libs/main.jar

image/build/lambda:
	@docker build -t media_saver -f Dockerfile.lambda --platform=linux/amd64 .

image/build:
	@docker build -t media_server -f Dockerfile --platform=linux/arm64 .

image/run:
	@docker run -t media_server:latest

run/lambda:
	java -jar build/libs/lambda.jar
