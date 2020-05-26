# Project-search-api
![Docker Image CI](https://github.com/ashishcyn/project-search-api-updated/workflows/Docker%20Image%20CI/badge.svg)


## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [API access](#accessing-the-api)
* [Unit Tests](#unit-tests )
* [Continuous integration](#continuous-integration-with-git)



## General info
This is a spring boot project.It has two REST endpoints to fetch data from GitHub by consuming public GitHub Apis.  
First one is to get a GitHub user’s name and a list of projects based on his username and the second is to fetch the details of a particular project.
	
## Technologies
Project is created with:
* Springboot 2.2.7.RELEASE
* Java 1.8
* Apache-maven-3.6.3
* Docker
	
## Setup

### Using the image published to docker hub
Dockerhub repo: c2nashish/project-search-api

Pull the lastest image:
```
docker pull c2nashish/project-search-api:latest
```
Run the pulled image:
```
docker run --publish 80:80 --detach c2nashish/project-search-api:latest
```


### Building and running the project locally   
1.Clone the git repo to local machine.   
2.Navigate to the location using terminal 
```
cd project-search-api
```
3.Maven Build 
```
mvn clean install     
```
4.Run the project
```
java -jar target/project-search-api-0.0.1-SNAPSHOT.jar
```


 

### Accessing the API

Using the below reference apis we can test the data   
API1: http://localhost/project-search-api/projects/username   
API2: http://localhost/project-search-api/projects/username/projectid 

eg:    
http://localhost/project-search-api/projects/ashishcyn   
http://localhost/project-search-api/projects/ashishcyn/project-search-api  

http://localhost/project-search-api/projects/jonlaokan  
http://localhost/project-search-api/projects/iluwatar/30-seconds-of-java   



Content negotiation is also implemened so we can pass headers as application/xml or application/json and will get the corresponding outputs(postman will be a good choice for tesing it) 

### Unit Tests
Test case results will be availbe in surefire-reports under target directory

### Continuous Integration with Git
Workflow build file is configured for Continuous Integration with GitHub. It runs the tests and build a docker image and publish to dockerub for every pull request or commit to master branch.  


