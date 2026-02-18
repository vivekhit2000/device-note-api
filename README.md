# Device Note API

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
