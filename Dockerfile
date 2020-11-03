FROM openjdk:8
WORKDIR /app/
COPY ./* ./
javac Main.java
cat $input | Main.class
