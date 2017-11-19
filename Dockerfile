FROM tomcat:8.0-jre8

ADD out/artifacts/semester3project_war_exploded /usr/local/tomcat/webapps/ROOT/

RUN mv /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/deployment-ebean.properties /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/ebean.properties

CMD ["catalina.sh", "run"]