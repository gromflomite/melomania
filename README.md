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

Database config done into  ``` src/main/java/model/connectionManagers/DbCredentials.java ```

    protected DbCredentials() {
    		this.dbUsername = "<DB_USERNAME>";
    		this.dbPassword = "<DB_PASSWORD";
    		this.dbLocation = "jdbc:mysql://<DB_ADDRESS>";
    	}
    	
#### Default credentials (password in DB hashed with SHA256)
| Username | Password | Notes
|--|--|--|
| listener | demoListen | User with no privileges
| admin | demoAdmin |

## To do

#### BackOffice (admin actions)
- [ ]  Approve / remove albums
- [ ]  Create / delete / update users

#### REST API
- [ ] Authentication (API key) - Be careful: Now, the API calls are not authenticated and every user is able to execute any of them against the DB.

## Known issues
* Password validation:

	Password is client-side hashed using JS (SHA256). I do not know how to validate a min and/or max password length in backend once the password arrives hashed. 
