/* Project Generation */

$ mvn --version
$ mvn archetype:generate -DgroupId=netto.app -DartifactId=java-demo-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
$ cd java-demo-app/
$ mvn package
$ java -cp target/java-demo-app-1.0-SNAPSHOT.jar netto.app.App
$ git init
$ echo "### ignoring the following files and folders ###" > .gitignore | gedit .gitignore

