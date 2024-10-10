
# BlueHarvest Banking Application

## Overview

This project is a full-stack web application that allows customers to open new bank accounts and manage transactions. 
It is built using Java (Spring Boot) for the backend and React.js for the frontend.

The project follows best practices in API design, service separation, and CI/CD integration.

## Features

- **Customer Management**: Create new Customer .
- **Customer Account Management**: Open new bank accounts for the customers.
- **Transaction Management**: view transactions for accounts.
- **React Frontend**: A clean and user-friendly interface for managing customer accounts.
- **CI/CD Pipeline**: Automated testing, building, and deployment using GitHub Actions.

---

## Technologies

### Backend:
- Java 21
- Spring Boot
- Spring Data JPA
- Maven
- Lombok
- CheckStyle for cleanCode
- SpringDoc Swagger-ui (http://localhost:8080/swagger-ui/index.html#/)
- Actuator (http://localhost:8080/actuator)

### Frontend:
- React.js
- Axios

### CI/CD:
- GitHub Actions
- Maven Wrapper
- Docker

---

## Setup Instructions

### Prerequisites

- **Java 21** installed on your machine.
- **Node.js** (v14 or higher) and **npm**.

### Backend Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/pghasemian/blueharvest.git
   ```

2. **Build the backend:**
   Use Maven Wrapper to install dependencies and build the project:
   ```bash
   ./mvnw clean install
   ```

3. **Run the backend:**
   ```bash
   ./mvnw spring-boot:run
   ```

   The backend will start at `http://localhost:8080`.


### Frontend Setup

1. **Navigate to the frontend folder:**
   ```bash
   cd ../frontend
   ```

2. **Install dependencies:**
   ```bash
   npm install
   ```

3. **Start the React development server:**
   ```bash
   npm run buid 
   npm start
   ```

   The frontend will start at `http://localhost:3000`.
---

## Usage

- Access the frontend at `http://localhost:3000`.
- The backend API is available at `http://localhost:8080`.

### API Endpoints:

- **Create New Customer**: `POST /api/customers/create`
- **Open New Account**: `POST /api/accounts/create`
- **Get Customer Details**: `GET /api/customers/{id}`

---

## Testing

### Unit and Integration Tests:

- Backend tests run automatically as part of the build process:
   ```bash
   ./mvnw test
   ```

---

## CI/CD Pipeline

This project uses GitHub Actions for CI/CD, with the following pipeline:

1. **Trigger**: On each push to the `develop` branch.
2. **Steps**:
   - Build and test the backend using Maven.
   - Build and test the frontend using npm.

### GitHub Actions Configuration

The GitHub Actions workflow (`.github/workflows/ci-cd.yml`) includes:
- Building the backend and running tests.
- Building the frontend and running tests.
- Deployment to a local server.(todo)

---


## Contact

For any inquiries, feel free to reach out:

- **GitHub**: [pghasemian](https://github.com/pghasemian)
- **Email**: ghasemianparisa1995@gmail.com
