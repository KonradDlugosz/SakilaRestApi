<div id="top"></div>
<div align="center">
   <a href="https://github.com/KonradDlugosz/SakilaRestApi">
    <img src="images/api.png" alt="Logo" width="150" height="150">
  </a>
    <h1 align= "center">Sakila REST API</h1>
</div>
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#server">Server</a></li>
          <li><a href="#client">Client</a></li>
      </ul>
    </li>
    <li><a href="#getting-started">Getting Started</a></li>
    <li>
      <a href="#http-requests">HTTP Requests</a>
      <ul>
        <li><a href="#get-requests">GET Requests</a></li>
          <li><a href="#post-requests">POST Requests</a></li>
          <li><a href="#put-requests">PUT Requests</a></li>
          <li><a href="#delete-requests">DELETE Requests</a></li>
      </ul>
    </li>
      <li><a href="#tools">Tools and Frameworks</a></li>
  </ol>
</details>



## About The Project

The main objective of this project is to deploy REST API for Sakila Database that provides CRUD operations on the database. This is achieved by using HTTP requests with a variety of different HTTP verbs. The project is split into two sub-projects in order to run the API and be able to test it. Here is the division of the project: 

### Server

Server hosts the developed REST API which includes all the CRUD operations and awaits HTTP requests from the client in order to get data or alter the database and send back a response over HTTP to the client which can be inspected for correct behavior.   

### Client

The purpose of the client in this project is to send a HTTP request to the server and receive a JSON response which than is deserialized into a Java object using Jackson. Afterwards, these objects are used in JUnit testing to check that the server response is as expected. Another way of testing the server can be a web browser, however it only supports GET requests. Alternatively, Postman supports all types of HTTP requests, however this requires manual testing for each request whereas the client tests all requests automatically. 

<p align="right">(<a href="#top">back to top</a>)</p>

## Getting Started

This section will walk you through on how to run this project on your machine.

Make sure that you have JDK installed on your machine in order to compile the code and run the app. 

You will also need MySQL installed and Sakila database: https://dev.mysql.com/downloads/

1. Clone the repository

   ```
   git clone https://github.com/KonradDlugosz/SakilaRestApi
   ```

2. Once you clone the project you will need to include `application.properties` file to connect to the database. This should be located under ```src/main/resources```. Example of MySQL connection file:

   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/sakila
   spring.datasource.username=root
   spring.datasource.password=root
   ```
   Other databases can be used by replacing the MySQL part of the URL with the database technology that you prefer, though you will need the corresponding driver regardless of which database that you use.


3. Open command prompt (Windows) or terminal (Mac/Linux) and navigate to cloned repository. 

4. First navigate to server project and locate file `RunServer.java`.

5. Compile the code

   ```
   javac RunServer.java
   ```

6. Run the server

   ```
   java RunServer
   ```

   <p align="right">(<a href="#top">back to top</a>)</p>

## HTTP Requests

HTTP defines a set of **request methods** to indicate the desired action to performed in database. 

### GET Requests

HTTP GET request are supported across all the tables in the database. 

Endpoint follows the pattern of `/table_name/id` which id is optional if return only specified element by id. 

An example endpoint for staff table would be as follows `/staff/1`. This would return all details about staff member with id of one. 

### POST Requests

HTTP ```POST``` Requests are supported by all tables. Endpoints follow the pattern of ```table_name/create```, where the request body should consist of JSON that represents the new state to be created. When inserting new element in most cases you don't have to provide ID as it will be auto generated. 

An example endpoint for posting new staff would include request `staff/create`. This would need include the JSON body that will be inserted into the staff table. If the JSON body is correct it will return a response with status code of `200`.

### PATCH Requests

HTTP ```PATCH``` Requests are supported by all tables except the Staff table, which should instead use ```PUT``` requests only.
Endpoints follow the pattern of ```table_name/update```, where the request body should consist of JSON that represents the new state.
For all tables except the Store table, this should include the last_update element, but for the Store table this should *NOT* be given, as it will be added automatically.

An example endpoint for performing a ```PATCH``` request would be ```/actor/update```

Endpoints will return a ```200``` if the request was good, with a message to indicate the success or failure, except for the Store endpoint, which will return ```202``` for a successful request, ```400``` if the request provided invalid JSON but for an existing store, and ```404``` for stores that do not exist. 

### PUT Requests 

HTTP ```PUT``` requests are supported by all tables except the Store table, which should use only ```PATCH``` requests. Endpoints and status codes for the ```PUT``` requests are the same as those for the corresponding ```PATCH``` requests, so further details are omitted from this section to prevent redundancy. 

### DELETE Requests

HTTP ```DELETE``` Requests are supported for all tables included as part of the API. Endpoints follow the pattern of ```table_name/delete/id```, where the supplied ID is the ID that uniquely identifies the record in the DB.

An example endpoint to ```DELETE``` the Store with ID 1 would be ```/store/delete/1```

Depending on the endpoint, a message will be displayed announcing the success or failure of the operation. The status code will be ```200``` for all except the Stores, which will return ```200``` if successful or ```404``` if the ID was not found.


<p align="right">(<a href="#top">back to top</a>)</p>



## Tools and Frameworks

This section contains all the tools and frameworks that were used in this project. 

### Spring Boot

Spring boot was used to develop RESTful web service as it makes it easy to build API. It provides an automatically configured production-grade spring application. This allows to get started quickly without wasting time on configuring a traditional Spring application. 

### Hibernate

Hibernate is Object-Relational Mapping (ORM) tool for Java which provides a framework for mapping the object-oriented model of application to a database system. This project uses the Sakila sample database, which runs on MySQL, though as long as the schema is the same, it should work with any database backend. 

### Jackson 

Jackson is a high-performance JSON processor for Java. It allows the creation of JSON from Java objects and also to deserialize JSON into a Java object by the use of the ```ObjectMapper``` which are used in the JUnit tests.   

<p align="right">(<a href="#top">back to top</a>)</p>