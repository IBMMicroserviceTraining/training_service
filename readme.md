This is the parent project for Training service. It has 2 modules
1) API module - Contains response models and interfaces
2) APP module - contains the main class and business logic


The version is - 0.0.8-SNAPSHOT

Following features are implemented in the module
1) Has two endpoints - to fetch trainers info
2) Depicts usage of Properties in spring boot and their precedence in spring boot
3) Configurations have been externalized to config server.Which means now config server will load the properties from the personalized git repo for training service and refresh them in the app
4) Connects with eureka server and registers itself as a service.Connection to the eureka server is defined in the properties file, with the property set in the git repo taking precedence.

5) Hystrix circuit breaker
  5.1) Circuit breaker implemented using Hystrix . Enable Hystrix dashboard - http://localhost:8900/hystrix
  5.2) provide the hystrix.stream actuator endpoint to the dashboard to have a hystrix view
    - localhost:8900/actuator/hystrix.stream
6) We will look at a unified hystrix dashboard with turbine in the next release.     
7) Swagger doc has been included in this release - unified hystrix dashboard will be introduced later
8) Interceptors added to the code, example of logging using logback-spring.xml and how log files can be rolling files added.


Note
-Notice the TrainerServiceFeignClient.Java in App. we have just added the dependency of trainer service api in our pom.xml and we extend the feign client interface with the API interface of the trainer service.
- We do not need to write or remember the methods and exact end points this way. Reduces code and human error
- Spring multi module project is of such a great help here. It lets us only get the API jar of trainer service.The api jar of the trainer service only has the interfaces and no implementations.
- So the provider would not have any problem in providing the API jar. This maintains the encapsulation.
- Size of the log files can be changed in logback-spring.xml. The file patterns can also be changed.

