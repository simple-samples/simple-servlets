# Tomcat Project:
REFACTOR: Add in instructions to write the classes necessary

## New Project Setup
### Starting a New Project
Create a new maven project in IntelliJ. You should get the boilerplate maven directories, including src/main/java. Create a new directory under main called `webapp`. Inside that directory create a new directory called `WEB-INF`. Then inside WEB-INF create a file called `web.xml`. So now we should have the following path and file:  
`src/main/webapp/WEB-INF/web.xml`
  
In the `web.xml` file add the following boilerplate xml code:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--web.xml is your depoyment descriptor
        this is how tomcat gathers information about your project, such as welcome files,
            available servlets, how to handle errors and exceptions, and other various
            configurations you may want tomcat to know about.
    It belongs in src/main/webapp/WEB-INF/ directory
-->
<web-app
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://java.sun.com/xml/ns/javaee"
        xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
        version="2.5">
</web-app>
```
### Getting the Smart Tomcat Plugin
If you don;t already have it installed, we need to get the Smart Tomcat plugin for Intellij. Go to File > Settings > Plugins and type "smart tomcat" into the search bar. You should see Smart Tomcat come up, click install. If it says "installed" then this step is already complete.

### Setting Maven to Package a WAR
Open the `pom.xml` file and add the following information to the groupID, artifactID, and version tags like this:
```xml
    <groupId>org.example</groupId>
    <artifactId>servlet-example</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>
```
This tells maven we are going to output a WAR file, not a JAR file. Jar is Java ARchive. WAR is Web ARchive. WAR files are nearly the same as JAR, you can explore these files with any zip program like 7zip or winrar if you are so inclined.

## Run Config
Click "Add configuration" on the top of the IntelliJ window to bring up the run config editor. Click the "add new" link (on the left) and select Smart Tomcat. This load the smart tomcat run config template, and we will fill in some of the options:

### Tomcat Server:
Point tomcat server to the root directory of the tomcat software. ex: 
`apache-tomcat-9.0.46`
### Deployment Dir:
Set deployment directory to the working directory of your app. This should probably 
be the directory containing the directory `WEB-INF/`.
### Context Path:
This will be the first part of your URL after the port. For example in the following URL the context path is "/CONTEXTPATH": `http://localhost:8080/CONTEXTPATH/...`

## Project Requirements
### Directory Structure:
In your deployment directory there should be a WEB-INF folder for web.xml and other any 
resources needed by tomcat. This document suggests a folder called webapp 
in the main/ directory. Resources needed at runtime can be accessed relatively from there.  

Also add a package under main called "servlets" where we will define the listener class and our servlet classes.


### Servlet Maven Dependency:
This dependency in the POM.xml file tells maven to load the necessary software for servlets.
```xml
<!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api -->
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>3.1.0</version>
    <scope>provided</scope>
</dependency>
```


### Listener:
In order for the tomcat web server to execute servlets, we need a listener. 
This must be listed in the `web.xml` file with the `<listener>` tag. 
This project's listener is described:
```xml
<listener>
    <listener-class>servlets.DependencyLoaderListener</listener-class>
</listener>
```

The listener itself implements `javax.servlet.ServletContextListener` and must 
override `contextInitialized` and `contextDestroyed`.
So create a class in the servlets package named `DependencyLoaderListener`, then paste this code into the file:
```java
package servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DependencyLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
```

## Mapping Servlets
Enter the following information into the `web.xml` file to describe and map the servlets.
### Describe the servlet:
Each servlet must be described with a name and class. ex: 
```xml
    <servlet>
        <servlet-name>pingServlet</servlet-name>
        <servlet-class>servlets.PingServlet</servlet-class>
    </servlet>
```

### Map the servlet:
And each servlet must map a URL to the name. The url-pattern will be what follows 
the context path. ex: `http://localhost:8080/CONTEXTPATH/URL-PATTERN...`
In the following example the /ping url-pattern maps to the pingServlet, which above 
points to the PingServlet class. 
```xml
    <servlet-mapping>
        <servlet-name>pingServlet</servlet-name>
        <url-pattern>/ping</url-pattern>
    </servlet-mapping>
```
HTTP requests sent to this URL will be handled by the "do" methods in PingServlet.  

## Servlet Class
We need a class for our ping servlet. The servlet class will extend `HttpServlet` and override the HTTP methods we want the servlet to handle. For now that just means the `doGet()` method. Create a class in the servlets package named `PingServlet` and use the following code:

```java
   
package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PingServlet extends HttpServlet {
/*
This will take a simple GET request and respond with "Pong!" and status 202, indicating the request was accepted.
 */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(202);
        resp.getWriter().print("Pong!");
    }
}
```
