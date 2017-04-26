FROM payara/micro

USER root
RUN  apk --no-cache add curl
USER payara

ENV DEPLOYMENT_DIR /opt/apps/
EXPOSE 9990
ENTRYPOINT ["java", "-jar", "/opt/payara/payara-micro.jar", "--deploy"]
COPY ./build/libs/micro.war .
CMD ["micro.war"]
HEALTHCHECK CMD curl --fail http://localhost:8080/micro/ping/+ || exit 1