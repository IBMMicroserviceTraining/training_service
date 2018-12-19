This is the parent project for Training service. It has 2 modules
1) API module - Contains response models and interfaces
2) APP module - contains the main class and business logic


The version is - 0.0.4-SNAPSHOT

Following features are implemented in the module
1) Has two endpoints - to fetch trainers info
2) Depicts usage of Properties in spring boot and their precedence in spring boot
3) Configurations have been externalized to config server.Which means now config server will load the properties from the personalized git repo for training service and refresh them in the app
4) Connects with eureka server and registers itself as a service.Connection to the eureka server is defined in the properties file, with the property set in the git repo taking precedence.

new feature
- Connects to a different microservice over feign client.