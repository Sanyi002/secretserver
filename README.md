# Secret Server Coding Task 
> Repository for Innonic's coding task. <br>The full description can be viewed here: https://github.com/peti2001/secret-server-task 


##### Requirements
* Java 11
* Apache Maven

## Installation

#### Clone
* Clone this repo to your local machine using `https://github.com/Sanyi002/secretserver.git`

#### Setup
> Compile the project with Maven.

```
$ mvn clean install 
```
> Run the compiled jar file.
```
$ java -jar target/secretserver-0.0.1-SNAPSHOT.jar --spring.datasource.url=$YOUR_JDBC_DRIVER --spring.datasource.username=$YOUR_DB_USERNAME --spring.datasource.password=$YOUR_DB_PASSWORD
```

## Usage
The project is using Swagger2 for documentation. So for more info you can check the doc under `your-host.com/swagger-ui.html`


