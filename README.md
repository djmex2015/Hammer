# Hammer

BackEnd was developed in Java 8 using Spring Boot 2 (2.1.5), developed in Spring (very good support), taking advantage of the optimization of tasks automation features, including their checks (for example, the query for records) The application will read the remote information of the rows and routes and will persist when starting the server (Tomcat embedded). This may take a minute to popularize.


Front-End: Basically the implementation was done with Angular 7 (7.3.9).


The configuration of the framework can be changed in application.properties, it was tried to cover all cases, of course as the application was being tested many bugs were encountered but inconsistencies in the JSON provided by the external APIs.
Log4j2 has also been installed, leaving the xml to be added, the configuration is programmatic and you will not be afraid to find the path to add the '' DBAppender '', so choose to create an Info entity and persist manually in the Application_Info table.

