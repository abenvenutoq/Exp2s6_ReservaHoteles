FROM eclipse-temurin:22-jdk AS buildstage 

RUN apt-get update && apt-get install -y maven

WORKDIR /reservahoteles

COPY pom.xml .

COPY src /reservahoteles/src

COPY Wallet_BDExp1S1 /reservahoteles/wallet

ENV TNS_ADMIN=/reservahoteles/wallet

RUN mvn clean package

FROM eclipse-temurin:22-jdk 

COPY --from=buildstage /reservahoteles/target/reservahoteles-0.0.1-SNAPSHOT.jar /reservahoteles/reservahoteles.jar

COPY Wallet_BDExp1S1 /reservahoteles/wallet

ENV TNS_ADMIN=/reservahoteles/wallet

EXPOSE 8081

ENTRYPOINT [ "java", "-jar","/reservahoteles/reservahoteles.jar" ]

#docker-compose up LO QUE DEBEMOS USAR EN LA TERMINAL PARA LEVANTAR NUESTRA APLICACION EN DOCKER 