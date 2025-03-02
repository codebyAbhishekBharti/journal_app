# Journal App

## Overview
Journal App is a web application that allows users to manage their journal entries. It includes features such as user authentication, role-based access control (RBAC), scheduled tasks, weather information retrieval, email notifications, integration with Redis for caching, and code quality analysis using SonarQube.

## Technologies Used
- Java
- Spring Boot
- Spring Security (RBAC)
- Spring Scheduler
- Spring Mail
- SonarQube
- Maven
- Redis
- MongoDB

## Prerequisites
- Java 11 or higher
- Maven
- MongoDB
- Redis
- SonarQube

## Running the Application
1. Clone the repository:  
   ```bash
   git clone https://github.com/codebyAbhishekBharti/journal-app.git
   cd journal-app
   ```
2. Build the project using Maven:  
   ```bash
   mvn clean install
   ```
3. Run the application:  
   ```bash
   mvn spring-boot:run
   ```
4. The application will be accessible at [http://localhost:8080/journal](http://localhost:8080/journal).  

## API Endpoints
### User Endpoints
- `GET /user` - Retrieve all users.
- `PUT /user` - Update the authenticated user's information.
- `DELETE /user` - Delete the authenticated user's account.
- `GET /user/greetings` - Get a greeting message along with the current weather in Phagwara.

### Journal Entry Endpoints
- `GET /journal` - Retrieve all journal entries of the authenticated user.
- `POST /journal` - Create a new journal entry.
- `GET /journal/id/{journalId}` - Retrieve a specific journal entry.
- `DELETE /journal/id/{journalId}` - Delete a specific journal entry.
- `PUT /journal/id/{journalId}` - Update a specific journal entry.

### Admin Endpoints
- `GET /admin/all-users` - Retrieve all users (Admin only).
- `POST /admin/create-admin-user` - Create an admin user.
- `GET /admin/clear-app-cache` - Clear the application cache.

## Role-Based Access Control (RBAC)
- **Admin**: Full access to manage users and journal entries.
- **User**: Can create, update, and delete their own journal entries.
- **Guest**: Limited access to public journal entries (if any).

## Services
### WeatherService
Retrieves weather information for a specified city and caches the result in Redis.  

### RedisService
Handles interactions with Redis for caching purposes.  

### EmailService
Sends email notifications to users.

### Scheduled Tasks
The application includes scheduled tasks that run at predefined intervals to perform maintenance tasks such as clearing expired cache entries and updating journal statistics.

## Code Quality Analysis with SonarQube
The application integrates with SonarQube to analyze code quality and ensure best practices in coding standards, security, and maintainability.

## Contributing
1. Fork the repository.
2. Create a new feature branch.
3. Commit your changes.
4. Push to your fork and submit a pull request.

## License
This project is licensed under the MIT License.

## Contact
For queries, reach out to [Abhishek Kumar Bharti](mailto:bhartiabhishek310@gmail.com).
