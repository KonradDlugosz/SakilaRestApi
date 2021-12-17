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

The main objective of this project is to deploy REST API for Sakila Database that provides CRUD operations on the database. This is achieved by using variety of HTTP requests. The project is split into two sub projects in order to run API and be able to tests it. Here is the division of the project: 

### Server

Server hosts the developed REST API which includes all the CRUD operations and awaits HTTP requests from client in order to get data or alter database and send back response over http protocol to client which can be inspected for correct behavior.   

### Client

The purpose of client in this project is to send a http request to the server and receive JSON response which than is dieselized into java object, this is done using Jackson. Afterwards, these objects are used in Junit testing to check that the server response is as expected. Another way of testing the server can be done by browser, however it only supports GET request. Alternatively, Postman supports all types of HTTP requests however this requires manually test for each requests whereas the client tests all requests automatically. 



## Getting Started

This section will walk you through on how to run this project on your machine.

Make sure that you have JDK installed on your machine in order to compile the code and run the app. 

You will also need MySQL installed and Sakila database: https://dev.mysql.com/downloads/

1. Clone the repository

   ```
   git clone https://github.com/KonradDlugosz/SakilaRestApi
   ```

2. Once you clone the project you will need to include `application.properties` file to connect to the database. Example of MySQL connection file:

   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/sakila
   spring.datasource.username=root
   spring.datasource.password=root
   ```

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

   

## HTTP Requests

HTTP defines a set of **request methods** to indicate the desired action to performed in Sakila database. 

### GET Requests

This section contains list of GET requests for Sakila database: 

##### GET all staff requests:

`GET /staff/all` 



### POST Requests

### PUT Requests

### PATCH Requests

HTTP ```PATCH``` Requests are supported by all tables except the Staff table, which should instead use ```PUT``` requests only.
Endpoints follow the pattern of ```table_name/update```, where the request body should consist of JSON that represents the new state.
For all tables except the Store table, this should include the last_update element, but for the Store table this should *NOT* be given, as it will be added automatically.

An example endpoint for performing a ```PATCH``` request would be ```/actor/update```

Endpoints will return a ```200``` if the request was good, with a message to indicate the success or failure, except for the Store endpoint, which will return ```202``` for a successful request, ```400``` if the request provided invalid JSON but for an existing store, and ```404``` for stores that do not exist. 

### DELETE Requests

HTTP ```DELETE``` Requests are supported for all tables included as part of the API. Endpoints follow the pattern of ```table_name/delete/id```, where the supplied ID is the ID that uniquely identifies the record in the DB.

An example endpoint to ```DELETE``` the Store with ID 1 would be ```/store/delete/1```

Depending on the endpoint, a message will be displayed announcing the success or failure of the operation. The status code will be ```200``` for all except the Stores, which will return ```200``` if successful or ```404``` if the ID was not found.


<p align="right">(<a href="#top">back to top</a>)</p>