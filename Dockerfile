FROM tomcat:8.0-jre8

ADD /out/artifacts/semester3project_war/semester3project_war.war /usr/local/tomcat/webapps/

CMD ["catalina.sh", "run"]