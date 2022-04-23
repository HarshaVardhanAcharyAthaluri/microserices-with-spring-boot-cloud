# microserices-with-spring-boot-cloud

# for oauthJwtService first create roles then insert users because to create user role is manditory.

* insert into roles values(1,'ROLE_USER');
* insert into roles values(2,'ROLE_ADMIN');

# Docker Image Build And Run

* Image Build: docker build -f Dockerfile -t dockerdemo .
* Run Image: docker run -p 8082:8082 dockerdemo