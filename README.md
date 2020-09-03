# Java File Server

![Product Presentation Image](http://bytec0de.com/blog/wp-content/uploads/2019/03/java-android-banner.jpg)

Socket based server with support of indexing files from environment.

## Quick start

- Install JDK 1.8.0
- Clone or download repository
- Run `cd production && java -jar java-socket.jar`


## Build

- Install IntelliJI IDEA
- Open project repository in this IDE
- Configure Maven and other dependencies
- Add `File -> Project Structure -> Artifacts -> Add -> Jar -> From Modules`
- Build a project via IDE tools or console


## Deploy

- Clean and build a project via IDE tools or console
- Open command line in project root directory
- Run `cd production && java -jar java-socket.jar`
- If everything is ok, 2 links will be available 
- `http://localhost:4000` and `http://localhost:4000/ping`


## [Load Test](http://loadest.io)

CentOS 7.6.1810, 1x2.2Hz, 0.5gb RAM  

![Load test](https://raw.githubusercontent.com/restinpc/Java-Server/master/loadest.png)