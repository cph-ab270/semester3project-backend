FROM tomcat:8.0-jre8

RUN rm -rf /usr/local/tomcat/webapps/ROOT
ADD /out/artifacts/semester3project_war/semester3project_war.war /usr/local/tomcat/webapps/ROOT.war

CMD ["catalina.sh", "run"]