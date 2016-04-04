# Hello Gradle
## a very simple multi-project gradle setup

Don't expect much functinality ;), this is only to blueprint a setup for a multi-project with to interface and a shared subproject with gradle.

This projects consists of 3 components:
* hello-gradle-core - provides the greetings
* hello-gradle-cli - provides a commandline interface
* hello-gradle-web - provides a web interface

The project uses the gradle war & tomcat plugin for the web interface and the gradle application plugin for the CLI distribution.

Running the CLI/web interface is dead simple:

* web interface: run 'gradle tomcatRun'
* CLI : run 'gradle distZip', unpack the hello-gradle-clie/build/distributions/hello-gradle-cli-*.zip and run the executable in the bin directory
