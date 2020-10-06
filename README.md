# melomania
melomania is a project to learn and practice Java and Jakarta EE.

A very simple CMS to manage your music albums. Contain a REST API (not fully implemented, yet) that allow to search and manage albums.

![Index screenshot](https://i.imgur.com/6uIMZeD.png)
## Technologies

* Java 11
* JavaScript
* HTML5
* CSS3 - Google fonts - Font Awesome
* Bootstrap
 * MySQL 
#### Maven dependencies
* MySQL Connector/J 8.0.21	
* Apache Log4j Core - API 2.13.3
* Hibernate Validator 6.1.6 final
* Gson 2.8.6

## Run melomania

Tested with Apache Tomcat 9.0.38 and MariaDB 10.5.5 (check included DB dump).

Database config done into 
>src/main/java/model/connectionManagers/DbCredentials.java

    protected DbCredentials() {
    		this.dbUsername = "<DB_USERNAME>";
    		this.dbPassword = "<DB_PASSWORD";
    		this.dbLocation = "jdbc:mysql://<DB_ADDRESS>";
    	}



#### Default credentials (password in DB hashed with SHA256)
| Username | Password | Notes
|--|--|--|
| listener | demoListen | Users with no privileges
| admin | demoAdmin |
