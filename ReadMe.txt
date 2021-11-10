/* Project Generation */

$ mvn --version
$ mvn archetype:generate -DgroupId=netto.app -DartifactId=java-demo-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
$ cd java-demo-app/
$ mvn package
$ java -cp target/java-demo-app-1.0-SNAPSHOT.jar netto.app.App
$ git init
$ echo "### ignoring the following files and folders ###" > .gitignore | gedit .gitignore

//Actualizar el target a Java 1.8
//Agregada carpeta de pruebas Stream (programacion Funcional)
$ java -cp target/java-demo-app-1.0-SNAPSHOT.jar netto.app.App
$ java -cp target/java-demo-app-1.0-SNAPSHOT.jar netto.app.progfuncional.foreach.DemoForEach



#######
+ Cliente Rest generico (Prueba con NodeJs localhost:3000)
  + javax.ws.rs-api
  + resteasy-client
+ Log4j2
  + log4j-api
  + log4j-core