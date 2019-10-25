# Java File Server

![Product Presentation Image](http://bytec0de.com/blog/wp-content/uploads/2019/03/java-android-banner.jpg)

Java/Spring/Tomcat Server with support of indexing files from environment.

## Quick start

- Install JDK 1.8.0
- Clone or download repository
- Run `cd production && java -jar gs-rest-service-0.1.0.jar`


## Building

- Install Maven 3.6.1
- Install IntelliJI IDEA
- Open project repository in this IDE
- Configure Maven and other dependencies
- Open `File -> Project Structure -> Artifacts`
- Add `Web Application: Exploded -> From Modules` artifact


## Deploying

- Run Build->Build artifacts from IntelliJi
- Open command line in project root directory
- Run `mvn package -f pom.xml`
- Copy `gs-rest-service-0.1.0.jar` from `target` to `production`
- Run `cd production && java -jar gs-rest-service-0.1.0.jar`
- If everything is ok, 2 links will be available 
- `http://localhost:8080` and `http://localhost:8080/api/ping`


## Stress Testing

CentOS 7.6.1810, 1x2.2ГГц, 1Гб RAM 
> Testing result is `250` Transactions Per Second with `3%` of Errors and `50 ms.` average Delay.
> 

