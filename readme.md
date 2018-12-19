This is the parent project for Training service. It has 2 modules
1) API module - Contains response models and interfaces
2) APP module - contains the main class and business logic


The version is - 0.0.5-SNAPSHOT

Following features are implemented in the module
1) Has two endpoints - to fetch trainers info
2) Depicts usage of Properties in spring boot and their precedence in spring boot
3) Configurations have been externalized to config server.Which means now config server will load the properties from the personalized git repo for training service and refresh them in the app
4) Connects with eureka server and registers itself as a service.Connection to the eureka server is defined in the properties file, with the property set in the git repo taking precedence.


Note
-Notice the TrainerServiceFeignClient.Java in App. we have just added the dependency of trainer service api in our pom.xml and we extend the feign client interface with the API interface of the trainer service.
- We do not need to write or remember the methods and exact end points this way. Reduces code and human error
- Spring multi module project is of such a great help here. It lets us only get the API jar of trainer service.The api jar of the trainer service only has the interfaces and no implementations.
- So the provider would not have any problem in providing the API jar. This maintains the encapsulation.

