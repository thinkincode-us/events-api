FROM adoptopenjdk/openjdk12:x86_64-alpine-jdk12u-nightly as BUILD_IMAGE
ENV APP_HOME=/root/dev/myapp/
RUN mkdir -p $APP_HOME/src/main/java
WORKDIR $APP_HOME
COPY build.gradle gradlew gradlew.bat $APP_HOME
COPY gradle $APP_HOME/gradle
RUN ./gradlew dependencies
COPY . .
RUN ./gradlew build

FROM adoptopenjdk/openjdk12:x86_64-alpine-jre12u-nightly
WORKDIR /opt
COPY --from=BUILD_IMAGE /root/dev/myapp/build/libs/events-api-all.jar .
EXPOSE 8080
CMD java ${JAVA_OPTS} -jar events-api-all.jar