
# BlueHarvest Banking Application

## Overview

This project is a full-stack web application that allows customers to open new bank accounts and manage transactions. 
It is built using Java (Spring Boot) for the backend and React.js for the frontend.

The project follows best practices in API design, service separation, and CI/CD integration.

## Features

- **Customer Account Management**: Open new bank accounts for existing customers.
- **Transaction Management**: Manage and view transactions for accounts.
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

4. **Run tests (optional):**
   ```bash
   ./mvnw test
   ```

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
   npm start
   ```

   The frontend will start at `http://localhost:3000`.


---

## Usage

- Access the frontend at `http://localhost:3000`.
- The backend API is available at `http://localhost:8080`.

### API Endpoints:

- **Open New Account**: `POST /api/accounts`
- **Get Customer Details**: `GET /api/customers/{id}`
- **Make a Transaction**: `POST /api/accounts/{id}/transactions`

---

## Testing

### Unit and Integration Tests:

- Backend tests run automatically as part of the build process:
   ```bash
   ./mvnw test
   ```

- To run the frontend tests:
   ```bash
   npm test
   ```

---

## CI/CD Pipeline

This project uses GitHub Actions for CI/CD, with the following pipeline:

1. **Trigger**: On each push to the `develop` branch.
2. **Steps**:
   - Build and test the backend using Maven.
   - Build and test the frontend using npm.
   - Deploy the application (on the main branch) to a local or cloud environment.

### GitHub Actions Configuration

The GitHub Actions workflow (`.github/workflows/ci-cd.yml`) includes:
- Building the backend and running tests.
- Building the frontend and running tests.
- Deployment to a local server.

---

## Deployment

### Local Deployment:

1. Run the CI/CD script to build and deploy locally:
   ```bash
   ./ci-cd.sh
   ```

### Remote Deployment (Optional):

- For cloud deployment, you can integrate services like **Netlify** or **Vercel** for the frontend, and **Heroku** or **AWS** for the backend.

---

## Contribution Guidelines

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature/my-new-feature`).
3. Commit your changes (`git commit -am 'Add some feature'`).
4. Push to the branch (`git push origin feature/my-new-feature`).
5. Create a new Pull Request.

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## Contact

For any inquiries, feel free to reach out:

- **GitHub**: [pghasemian](https://github.com/pghasemian)
- **Email**: ghasemianparisa1995@gmail.com
