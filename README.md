# Warehouse Management System API

A comprehensive warehouse management system built with Spring Boot that provides APIs for managing warehouses, inventory, products, and shipments with role-based access control.

## 🚀 Features

- **User Management**: Role-based authentication with Admin, Staff, and Client roles
- **Warehouse Management**: Create and manage multiple warehouses
- **Inventory Management**: Track products, batches, and inventory locations
- **Inbound Shipments**: Receive and process incoming products
- **Room & Rack Management**: Organize warehouse storage with rooms and racks
- **QR Code Generation**: Generate QR codes for racks and inventory tracking
- **Location Updates**: Update inventory locations within the warehouse
- **Security**: Spring Security with role-based access control

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.4.6
- **Language**: Java 21
- **Database**: MySQL 8
- **ORM**: Spring Data JPA with Hibernate
- **Security**: Spring Security
- **Build Tool**: Maven
- **Additional Libraries**:
  - Lombok for boilerplate code reduction
  - ZXing for QR code generation
  - Spring Boot Validation

## 📋 Prerequisites

- Java 21 or higher
- Maven 3.6+
- MySQL 8.0+
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

## 🔧 Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd WareHouseProject
```

### 2. Database Setup
1. Install MySQL and create a database:
```sql
CREATE DATABASE warehouse_db;
```

2. Update database configuration in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/warehouse_db?createDatabaseIfNotExist=true
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Build and Run
```bash
# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## 📚 API Documentation

### User Management
- `POST /users` - Register a new user
- `GET /find?id={userId}` - Find user by ID

### Warehouse Management
- `POST /warehouses/{userId}` - Create warehouse (Admin only)

### Room Management
- `POST /rooms/{warehouseId}` - Create room in warehouse

### Rack Management
- `POST /rack/{userId}` - Create rack
- `GET /rack/barcode/{rackId}` - Generate QR code for rack

### Inbound Shipments
- `POST /receive/shipment/{wareHouseId}` - Receive products (Client only)

### Batch Management
- `POST /batch/{inboundshipmentId}` - Create product batch

### Inventory Management
- `PUT /update-location` - Update inventory location

### Client Management
- `POST /client` - Register client

## 🏗️ Project Structure

```
src/
├── main/
│   ├── java/com/example/warehouse/
│   │   ├── controller/          # REST Controllers
│   │   ├── dto/                 # Data Transfer Objects
│   │   │   ├── request/         # Request DTOs
│   │   │   └── response/        # Response DTOs
│   │   ├── entity/              # JPA Entities
│   │   ├── enums/               # Enumerations
│   │   ├── exception/           # Custom Exceptions
│   │   ├── mapper/              # Entity-DTO Mappers
│   │   ├── repository/          # JPA Repositories
│   │   ├── security/            # Security Configuration
│   │   ├── service/             # Service Interfaces
│   │   ├── serviceimpl/         # Service Implementations
│   │   ├── shared/              # Shared Components
│   │   └── utility/             # Utility Classes
│   └── resources/
│       └── application.properties
└── test/                        # Test Classes
```

## 🔐 Security & Roles

The system implements role-based access control with three main roles:

- **ADMIN**: Can create warehouses and manage system-wide settings
- **STAFF**: Can manage warehouse operations
- **CLIENT**: Can receive shipments and manage inventory

## 🗄️ Database Schema

### Core Entities
- **User**: Base user entity with role-based inheritance
- **Admin**: Extends User, can manage warehouses
- **Staff**: Extends User, works in specific warehouses
- **Client**: External clients who can ship products
- **WareHouse**: Physical warehouse locations
- **Room**: Storage rooms within warehouses
- **Rack**: Storage racks within rooms
- **Product**: Product catalog
- **InboundShipment**: Incoming product shipments
- **ProductUnit**: Individual product instances
- **InBoundBatch**: Batches of products

## 🚦 Getting Started

1. **Register an Admin User**:
```bash
POST /users
{
  "username": "admin",
  "email": "admin@example.com",
  "password": "password",
  "userRole": "ADMIN"
}
```

2. **Create a Warehouse**:
```bash
POST /warehouses/{adminUserId}
{
  "name": "Main Warehouse",
  "location": "City Center"
}
```

3. **Add Rooms and Racks**:
```bash
POST /rooms/{warehouseId}
{
  "roomName": "Storage Room A"
}
```

## 🧪 Testing

Run the test suite:
```bash
./mvnw test
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📞 Support

For support and questions, please open an issue in the GitHub repository.

---

**Built with ❤️ using Spring Boot**
