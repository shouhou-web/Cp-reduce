FROM openjdk:8
WORKDIR /app/
COPY ./* ./
RUN javac Main.java
RUN javac OutPut.java