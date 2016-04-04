# Hello Gradle
## a very simple multi-project gradle setup

Don't expect much functionality ;), this is only to blueprint a setup for a gradle multi-project build with two interface and a shared subproject.

The setup consists of 3 subprojects:
* hello-gradle-core - provides the greetings
* hello-gradle-cli - provides a command line interface
* hello-gradle-web - provides a web interface

The gradle war & tomcat plugins are used for the web interface subproject, the CLI subproject uses the gradle application plugin.

Running the CLI/web interfaces is dead simple:

* web interface: run 'gradle tomcatRun' and see http://localhost:8080/hello-gradle-web/greetings
* CLI : run 'gradle distZip', unpack the hello-gradle-clie/build/distributions/hello-gradle-cli-*.zip and run the executable in the bin directory
