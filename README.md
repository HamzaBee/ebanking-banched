# E-Banking Backend Application

A Spring Boot-based backend system for e-banking operations, supporting customer management, current and savings bank accounts, and transactional account operations (debit, credit, and transfers).

---

## Authors & Supervision
- **Author:** Hamza Benbrahim
- **Supervised by:** Professeur Mohamed YOUSSFI

---

## Technical Stack
- **Backend Framework:** Spring Boot 4.1.0 (Java 21)
- **Data Persistence:** Spring Data JPA with MySQL Database
- **API Documentation:** Springdoc OpenAPI / Swagger UI
- **Mappers & Utilities:** MapStruct 1.5.5.Final, Lombok
- **Infrastructure:** Docker (MySQL & phpMyAdmin)
- **API Testing:** Postman

---

## Project Structure & Architecture

### 1. Infrastructure (Docker Setup)
The application utilizes Docker to spin up the required database and database administration environments.
- **MySQL Database:** Exposes database services on port `3306`.
- **phpMyAdmin:** Exposes web database interface on port `8090`.

![Docker Dashboard Configuration](../JavaScreenshots/Screen5.png)

---

### 2. Database Structure (phpMyAdmin)
Hibernate ORM is configured to automatically create the schema in the `ebanking_db` database. 

![Database Tables Overview](../JavaScreenshots/Screen2.png)

The schema comprises three core tables:
- **`customer`**: Stores customer profiles (ID, name, and email).
  ![Customer Table](../JavaScreenshots/Screen1.png)

- **`bank_account`**: Stores current accounts (with `overDraft` limits) and savings accounts (with `interestRate`s) using a single-table inheritance strategy.
  ![BankAccount Table](../JavaScreenshots/Screen3.png)

- **`account_operation`**: Logs all credit and debit transactions, linked to their respective bank accounts.
  ![AccountOperation Table](../JavaScreenshots/Screen4.png)

---

## API Testing & Verification

Below are the Postman testing results for the `CustomerRestController` endpoints.

### Customer CRUD Operations

#### 1. Retrieve All Customers (`GET /customers`)
Fetches the list of all registered customers.
![GET All Customers](../JavaScreenshots/Screen6.png)

#### 2. Create a New Customer (`POST /customers`)
Adds a new customer profile. In the sample, a customer named `Hamza` is created with ID `4`.
![POST Create Customer](../JavaScreenshots/Screen7.png)

#### 3. Fetch Customer Details by ID (`GET /customers/{id}`)
Fetches specific customer details. Here, details of customer `3` (Mohamed) are retrieved.
![GET Customer by ID](../JavaScreenshots/Screen8.png)

#### 4. Update Customer Details (`PUT /customers/{customerId}`)
Updates the customer record. Customer `4` is updated to `Hamza Updated`.
![PUT Update Customer](../JavaScreenshots/Screen9.png)

#### 5. Delete a Customer (`DELETE /customers/{customerId}`)
Deletes the customer record by ID.
![DELETE Customer](../JavaScreenshots/Screen10.png)

---

## Interactive API Documentation (Swagger UI)

With Springdoc OpenAPI integrated, Swagger UI is available at `http://localhost:8085/swagger-ui/index.html` to visually explore and execute the REST endpoints.

![Swagger UI Interface](../JavaScreenshots/Screenshot%202026-07-08%20135851.png)
