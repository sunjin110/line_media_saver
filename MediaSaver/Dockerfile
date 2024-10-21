FROM gradle:8.10.2-jdk21-graal AS build

WORKDIR /app
COPY . .

RUN ./gradlew nativeCompile

FROM gcr.io/distroless/cc-debian12:latest

COPY --from=build /app/build/native/nativeCompile/main-native .
USER nonroot

ENTRYPOINT ["/main-native"]
