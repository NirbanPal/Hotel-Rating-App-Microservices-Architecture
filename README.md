# 🏨 Hotel Rating App — Microservices Architecture

A production-grade microservices-based Hotel Rating Application built with Spring Boot and Spring Cloud, designed to demonstrate distributed architecture, inter-service communication, centralized configuration, fault tolerance, and enterprise-level security using OKTA OAuth2.

## 🚀 Overview

The Hotel Rating App enables users to explore hotels, submit ratings, and view aggregated reviews — all powered by independent microservices that communicate through a Service Registry (Eureka) and are secured via API Gateway Authentication.

This project goes beyond simple CRUD — it’s a deep-dive into real-world microservice architecture, including:
- Service Discovery (Eureka)
- Config Server (GitHub-hosted)
- API Gateway (Spring Cloud Gateway)
- Fault Tolerance with Resilience4j
- Rate Limiting
- Centralized Security with OKTA
- FeignClient & RestTemplate communication
- Load Balancing with Ribbon
- Distributed Configuration via Spring Cloud Config
- Circuit Breaker, Retry, and Fallback Mechanisms

## 🧩 Architecture Diagram
![HotelRatingAppArchitecture_Diagram](https://github.com/NirbanPal/Hotel-Rating-App-Microservices-Architecture/blob/main/HotelRatingAppArchitecture.png)

## 🏗️ Microservices Overview

| Service            | Port   | Description                                                                                |
| ------------------ | ------ | ------------------------------------------------------------------------------------------ |
| **Eureka Server**  | `8761` | Central service registry where all microservices register.                                 |
| **API Gateway**    | `8084` | Entry point for all external requests, handles routing, authentication, and authorization. |
| **Config Server**  | `8085` | Centralized configuration using GitHub as external config repo.                            |
| **User Service**   | `8081` | Handles user profiles, consumes Hotel and Rating services.                                 |
| **Hotel Service**  | `8082` | Manages hotel data and details.                                                            |
| **Rating Service** | `8083` | Handles ratings and reviews, consumed by User Service.                                     |

## ⚙️ Tech Stack

- Java
- Spring Boot 
- Spring Cloud
- Spring Cloud Netflix (Eureka, Feign, Ribbon)
- Spring Cloud Gateway
- Spring Cloud Config Server
- Spring Security + OKTA OAuth2
- Resilience4j (Circuit Breaker, Rate Limiter, Retry)
- Spring Boot Actuator & AOP
- Maven
- GitHub for distributed config
- Postman / JMeter for API testing

## 🔐 Security Architecture

Security is implemented using OKTA OAuth 2.0 for authentication and authorization.
- API Gateway enforces token validation and scopes.

- User Service acts as an internal client and communicates securely with Rating and Hotel Services.

- Internal Scopes ensure only authorized microservices can call protected endpoints (e.g., rating-service only accessible by user-service).

## ✅ OKTA Integration:

- Authorization Grant Type: client_credentials

- Scopes: openid, profile, email, offline, internal

- Configuration provided via application.yml

#### Provide your issuer, client-id, client-secret in ApiGateway/src/main/resources/application.yml
```bash
  okta:
    oauth2:
      issuer: your_issuer
      audience: api://default
      client-id: your_client_id
      client-secret: your_client_secret
      scopes: openid, profile, email, offline_access
```

## ⚡ Fault Tolerance & Rate Limiting

Implemented using Resilience4j:

| Feature              | Description                                                              |
| -------------------- | ------------------------------------------------------------------------ |
| **Circuit Breaker**  | Automatically stops repeated failed calls to downed services.            |
| **Retry Mechanism**  | Reattempts failed calls with controlled delay.                           |
| **Rate Limiter**     | Controls request frequency to prevent overloading services.              |
| **Fallback Methods** | Graceful degradation for better user experience during service downtime. |

Example:
```bash
  @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "fallbackMethod")
  @Retry(name = "ratingHotelService", fallbackMethod = "fallbackMethod")
  public ResponseEntity<?> getUserDetails(String userId) { ... }
```

## 🧰 Inter-Service Communication

| Method            | Description                                                  |
| ----------------- | ------------------------------------------------------------ |
| **RestTemplate**  | Used by User Service to fetch data from Rating Service.      |
| **FeignClient**   | Declarative HTTP client used to interact with Hotel Service. |
| **@LoadBalanced** | Enables dynamic service instance resolution from Eureka.     |


## 🌐 Config Server Setup

Configuration is externalized and version-controlled using GitHub.
```bash
  spring:
    cloud:
      config:
        server:
          git:
            uri: https://github.com/NirbanPal/Rating-App-Config.git
            clone-on-start: true
```

Client microservices import this config via:
```bash
spring:
  config:
    import: optional:configserver:http://localhost:8085
```

## 📡 Service Registry (Eureka)

Each microservice registers with Eureka:
```bash
eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:8761/eureka
```

Eureka dashboard accessible at:
👉 http://localhost:8761
![eurekaDashboard](https://github.com/NirbanPal/Hotel-Rating-App-Microservices-Architecture/blob/main/ScreenShorts/ServiceRegistry.png)

## 🧪 Testing

- Postman for API testing

- JMeter for load testing and rate limiter verification

- Actuator endpoints for health monitoring

### Download Jmeter here: <a href="https://dlcdn.apache.org//jmeter/binaries/apache-jmeter-5.6.3.zip"> Jmeter</a>

### Test Rate Limiter with Jmeter:
![PostmanApis](https://github.com/NirbanPal/Hotel-Rating-App-Microservices-Architecture/blob/main/ScreenShorts/Junit1.png)
![PostmanApis](https://github.com/NirbanPal/Hotel-Rating-App-Microservices-Architecture/blob/main/ScreenShorts/Junit2.png)
![PostmanApis](https://github.com/NirbanPal/Hotel-Rating-App-Microservices-Architecture/blob/main/ScreenShorts/Junit3.png)
![PostmanApis](https://github.com/NirbanPal/Hotel-Rating-App-Microservices-Architecture/blob/main/ScreenShorts/Junit4.png)
![PostmanApis](https://github.com/NirbanPal/Hotel-Rating-App-Microservices-Architecture/blob/main/ScreenShorts/Junit5.png)
![PostmanApis](https://github.com/NirbanPal/Hotel-Rating-App-Microservices-Architecture/blob/main/ScreenShorts/Junit6.png)


### Postman APIs
![PostmanApis](https://github.com/NirbanPal/Hotel-Rating-App-Microservices-Architecture/blob/main/Postman.png)

### Postman Collection accessible at: 👉 
[Postman Collection](https://github.com/NirbanPal/Hotel-Rating-App-Microservices-Architecture/blob/main/RatingApp%20-%20Microservices.postman_collection.json)


## 🧱 Project Highlights

- ✅ Complete microservices ecosystem — Eureka, Config Server, API Gateway, OKTA
- ✅ Real-time inter-service communication via Feign & RestTemplate
- ✅ Centralized config management using GitHub
- ✅ Circuit breaker, retry, and rate limiter with Resilience4j
- ✅ Secure authentication and internal service-level authorization
- ✅ Well-structured modular codebase for scalability and maintainability
- ✅ Ready for Dockerization and cloud deployment

## 🧩 Prerequisites

- Java 17+

- Maven 3+

- Internet access (for GitHub Config Repo)

- OKTA Developer Account

- Postman / JMeter for testing

## 🏁 Running the Project

### Clone the repo and change dir:
```bash
git clone https://github.com/NirbanPal/Hotel-Rating-App-Microservices.git
cd Hotel-Rating-App-Microservices
```

### Start Eureka Server
```bash
mvn spring-boot:run -pl eureka-server
```

### Start Config Server
```bash
mvn spring-boot:run -pl config-server
```

### Start API Gateway & other services
```bash
mvn spring-boot:run -pl api-gateway,user-service,hotel-service,rating-service
```

### Access Eureka Dashboard → http://localhost:8761
![PostmanApis](https://github.com/NirbanPal/Hotel-Rating-App-Microservices-Architecture/blob/main/Postman.png)

## 🧠 Learnings & Key Takeaways

- Deep understanding of Spring Cloud microservices ecosystem

- Implementing secure, fault-tolerant, scalable distributed systems

- Building enterprise-grade authentication and authorization using OKTA

- Managing externalized configurations across environments

- Handling resiliency patterns for real-world microservice failures
