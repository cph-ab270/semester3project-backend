[![Build Status](https://travis-ci.org/Ejdems666/semester3project-backend.svg?branch=master)](https://travis-ci.org/Ejdems666/semester3project-backend)

# IntelIJ Setup

Clone the project by using git clone on the command line and then opening the folder in Intelij (File -> Open -> select folder).
Alternatively, clone the project by using the File -> New -> Project from Version Control -> Github option in IntelliJ.

Then install Ebean plugin, open the plugin window: cltr/cmd+shift+a type "plugins" and press enter, click on browse repositories, search for Ebean and install Ebean Enhancement 11.x. Restart IntelliJ. Then tick Ebean 11.x enhancement under build tab (this plugin is needed for the Ebean to run).
    
Now you're setup and you only need to create a running configuration for your local tomcat server.

## Running config in IntelIJ

alt+shift+f10 Choose "Edit configurations"

Click on plus sign in top left corner, search for tomcat, choose local.

Configure application server if you don't have it already. Just click on the browse button and navigate to your tomcat directory.

Click on the "Fix" button in the bottom and choose the option with "exploded" word in it.

Go to the Startup/Connection tab and setup enviromental variables, here is an example:

    PROP_DB_PASSWORD=root
    PROP_DB_URL=jdbc:mysql://localhost:3306/cba_sem3project
    PROP_SECRET_TOKEN=h6hFhhYY77765444EEEEvgfdeMnbV30h

Now you have a configuration that runs a tomcat server on your PC, deploys your exploded artifact (uncompressed folder with compiled Java sources, libs, resources, etc.) 
and sets up the environmental variables.

# Running with docker-compose

Install Docker, create .env in root folder of the project with the environmental variables (example in Running config section)

run docker-compose up -d

update by docker-compose up --build

However to update the sources you need to rebuild the exploded artifact (Build tab -> Build artifacts)
Because of this, docker-compose is not the recommended way, but I might figure out soon how to automate this.
The nice think about the docker way is that you'll run the server the exact same way as in Travis and DigitalOcean.

# Travis

Don't forget, that every push and PR triggers a build in Travis, to suppress this (for example when you update readme, or something that does not need CI build)
add this `[ci skip]` to your commit message. Like this `git commit -m 'documentation update [ci skip]'`