# Device Note API

## Part A – Architecture Warm-up
1. Responsibilities of Controller, Service, and Repository layers
->   So controller is just for handling HTTP requests and responses. Service is where all the business logic and validation happens. Repository is only for talking to the database, like fetching or saving data.

2. What is a database transaction? When to use @Transactional
->  A transaction means a set of DB operations either all succeed or all fail. We use @Transactional when we want to make sure data stays consistent, like when multiple inserts or updates need to happen together. For simple reads, we don’t need it.

3. Why use DTOs instead of returning JPA entities directly from APIs
->   DTOs are basically a way to control what we send in API response. We don’t want to expose our database entities directly. It keeps the response clean and safe, and we can format data the way we want without touching the DB objects.

4. What problem does Liquibase solve in multi-developer / multi-environment setups
->   Liquibase is used for managing database changes in a version-controlled way. When multiple devs are working or we have multiple environments, it keeps the DB schema consistent and avoids conflicts.


## Overview
Device Note API is a Spring Boot service that allows storing and retrieving notes for devices.  
It can be used for audit logs, maintenance remarks, or operational comments.

The application follows a layered structure:
- Controller → Handles HTTP requests
- Service → Business logic and validation
- Repository → Database operations
- Liquibase → Database schema management

---

## Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Liquibase
- Maven

---

## Project Structure
controller   → REST endpoints  
service      → Business logic  
repository   → Data access  
entity       → JPA entities  
dto          → Request/response models  
exception    → Error handling  

---



##create db

```sql
CREATE DATABASE device_db;
