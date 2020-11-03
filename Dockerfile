FROM openjdk:8
WORKDIR /app/
COPY ./* ./
cat $input | RUN javac Main.java
