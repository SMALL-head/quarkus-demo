FROM ghcr.io/graalvm/graalvm-ce:latest AS build
RUN gu install native-image
WORKDIR /project
VOLUME ["/project"]
ENTRYPOINT ["native-image"]
