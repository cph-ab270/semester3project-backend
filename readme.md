# IntelIJ Setup

Clone the project by using git clone on the command line and then opening the folder in Intelij (File -> Open -> select folder).
Alternatively, clone the project by using the File -> New -> Project from Version Control -> Github option in IntelliJ.

Then install Ebean plugin, open the plugin window: cltr/cmd+shift+a type "plugins" and press enter, click on browse repositories, search for Ebean and install Ebean Enhancement 11.x. Restart IntelliJ. Then tick Ebean 11.x enhancement under build tab (this plugin is needed for the Ebean to run).

Open the console (alt+f12) and type in "mvn ebean-codegen:init" (this generates Ebean property files, which is something like the persistence.xml file). If the command is not working it's probably because you don't have mvn installed, which I recommend to do [quide here](https://maven.apache.org/install.html).

Alternatively, create file yourself:


**src/main/resources/ebean.properties**

    ebean.ddl.generate=true
    ebean.ddl.run=true
    ebean.ddl.createOnly=false
    ebean.ddl.seedsql=seedData.sql
    
    datasource.db.username=root
    datasource.db.password=root
    datasource.db.databaseUrl=jdbc:mysql://localhost:3306/cba_sem3project
    datasource.db.databaseDriver=com.mysql.jdbc.Driver
    
**src/test/resource/test-ebean.properties**

    ebean.ddl.generate=true
    ebean.ddl.run=true
    ebean.ddl.createOnly=true
    
    datasource.default=db
    
    datasource.db.username=root
    datasource.db.password=root
    datasource.db.databaseUrl=jdbc:mysql://localhost:3306/cba_sem3project_test
    datasource.db.databaseDriver=com.mysql.jdbc.Driver
    
I think the files are pretty self explanatory, don't forget to create the 2 databases

Also create the **src/main/resources/config.properties**

    tokenSecret=h6hFhhYY77765444EEEEvgfdeMnbV30h
    
Now you're setup and you only need to create a running configuration for your local tomcat server.

alt+shift+f10 Choose "Edit configurations"

Click on plus sign in top left corner, search for tomcat, choose local.

Configure application server, if you don't have it already, just click on the browse button and navigate to your tomcat directory.

Click on the "Fix" button in the bottom and choose the "exploded" option.

Now you have a configuration that runs a tomcat server on your PC and deploys on it your exploded artifact (uncompressed folder with compiled Java sources, libs, resources, etc.).