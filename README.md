# Car Dealership Backend

A backend application for managing car brands and models. Built with **Spring Boot** using **H2 Database** for persistence and following a modular and clean architecture by Martha Quintana.

---

## **Project Structure**

The project follows the **MVC architecture** and is divided into packages for better organization:

```
car-dealership-backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com.example.cardealership/
│   │   │   │   ├── controllers/        # Controllers for handling HTTP requests
│   │   │   │   ├── models/             # Domain models and entity classes
│   │   │   │   ├── repositories/       # Data access layer
│   │   │   │   ├── services/           # Business logic layer
│   │   ├── resources/                  # Configuration files
│   │   │   └── application.properties  # Database and application config
│   ├── test/
│       └── java/
│           └── com.example.cardealership/ # Unit and integration tests
```

---

## **Endpoints**

### **Brands**
| Method | Endpoint               | Description                              |
|--------|------------------------|------------------------------------------|
| GET    | `/brands`              | List all brands                         |
| GET    | `/brands/{id}/models`  | List all models of a specific brand      |
| POST   | `/brands`              | Add a new brand                         |

### **Models**
| Method | Endpoint                  | Description                                         |
|--------|---------------------------|-----------------------------------------------------|
| GET    | `/models`                 | List all models with optional price filters        |
| POST   | `/brands/{id}/models`     | Add a new model to a specific brand                |
| PUT    | `/models/{id}`            | Update the average price of an existing model      |

---

## **Setup**

### **Prerequisites**
- Java 17 or higher
- Maven 3.8 or higher

### **Steps**
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/car-dealership-backend.git
   cd car-dealership-backend
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Access the application at:
   - API: `http://localhost:8080`
   - H2 Database Console: `http://localhost:8080/h2-console`
     - **JDBC URL**: `jdbc:h2:mem:car_dealership`
     - **Username**: `sa`
     - **Password**: (leave blank)

---

## **Technologies Used**
- **Spring Boot**: Backend framework
- **Spring Data JPA**: For database interaction
- **H2 Database**: In-memory database for development
- **JUnit 5**: For unit and integration testing
- **Maven**: Build tool

---

## **Key Components**

### **1. Models**
Defines the domain entities for the application:
- **Brand**:
  - `id`: Long
  - `name`: String (unique)
  - `models`: List<Model>
- **Model**:
  - `id`: Long
  - `name`: String
  - `averagePrice`: Double
  - `brand`: Brand

---

### **2. Repositories**
Handles database interactions using **Spring Data JPA**.
- **BrandRepository**: Provides CRUD operations for brands.
- **ModelRepository**: Provides operations for filtering models by price.

---

### **3. Services**
Encapsulates business logic and interacts with repositories:
- **BrandService**:
  - Create a brand.
  - Retrieve all brands or by ID.
- **ModelService**:
  - Add a model to a brand.
  - Update model price.
  - Filter models by price range.

---

### **4. Controllers**
Handles HTTP requests and maps them to service methods:
- **BrandController**:
  - Routes for managing brands.
- **ModelController**:
  - Routes for managing models.

---

## **Testing**

### **Unit Tests**
Tests individual components (services and methods):
- **BrandServiceTest**: Tests for creating, retrieving, and validating brands.
- **ModelServiceTest**: Tests for adding and updating models.

### **Integration Tests**
Tests the full application flow using **MockMvc**:
- **BrandControllerTest**: Tests endpoints like `GET /brands`.
- **ModelControllerTest**: Tests endpoints like `POST /brands/{id}/models`.

### **Running Tests**
```bash
mvn test
```

---

## **Usage**

### **Example Requests**

1. **Create a Brand**
   - **Request**:
     ```bash
     POST /brands
     Content-Type: application/json
     Body: {"name": "Toyota"}
     ```
   - **Response**:
     ```json
     {
       "id": 1,
       "name": "Toyota"
     }
     ```

2. **Add a Model**
   - **Request**:
     ```bash
     POST /brands/1/models
     Content-Type: application/json
     Body: {"name": "Corolla", "average_price": 250000}
     ```
   - **Response**:
     ```json
     {
       "id": 1,
       "name": "Corolla",
       "average_price": 250000
     }
     ```

3. **Update Model Price**
   - **Request**:
     ```bash
     PUT /models/1
     Content-Type: application/json
     Body: {"average_price": 300000}
     ```
   - **Response**:
     ```json
     {
       "id": 1,
       "name": "Corolla",
       "average_price": 300000
     }
     ```

---

## **Future Improvements**
- Implement authentication and authorization.
- Add DTOs and Custom Error Handling 
- Introduce caching for frequently accessed endpoints.
- Add Swagger for API documentation.

---

## **License**
This project is licensed under the MIT License. Feel free to use and modify it as needed.
