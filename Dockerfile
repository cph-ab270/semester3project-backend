FROM tomcat:8.0-jre8

ADD out/artifacts/semester3project_war_exploded /usr/local/tomcat/webapps/ROOT/

CMD ["catalina.sh", "run"]