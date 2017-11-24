FROM tomcat:8.0-jre8

ADD out/artifacts/semester3project_war_exploded /usr/local/tomcat/webapps/ROOT/

RUN apt-get update
RUN apt-get install xmlstarlet -y

RUN mkdir /home/img; chmod 777 /home/img -R

RUN xmlstarlet ed  --inplace -s '//Host'        -t elem -n 'Context' \
                    -s '//Host/Context' -t attr -n 'docBase' -v '/home/img' \
                    -s '//Host/Context' -t attr -n 'path' -v '/img' /usr/local/tomcat/conf/server.xml

CMD ["catalina.sh", "run"]