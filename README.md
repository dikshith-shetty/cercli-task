# Cercli Assessment

### System Environment Requirements
- Window 11 OS
- Docker desktop on windows
- Terraform 
- Maven
- Java 17
- PostgreSQL docker image

### System setup:
1. Install docker desktop on windows system (You can you non windows system, for that terraform code needs to be updated). Make sure docker desktop is running.
2. Download terraform binaries from [here](https://developer.hashicorp.com/terraform/install) for windows. Add the file path to the USER/SYSTEM PATH variable.
3. Install Maven
4. Install Java 17 from official site.
5. Navigate to the Cercli-task dir and run followings
   ```cmd
   terraform init
   terrafrom apply
   ``` 

## 1. Employee Database Management System

Employee Database Management System will manage the employee data sets. This will have follwing api's to manage the requests

### Build and Run: 
1. Navigate to the `employee-manage-core` directory:
    ```cmd
    cd employee-manage-core
    ```

2. Build the project using Maven:
    ```cmd
    mvn clean install
    ```

3. Run the service:
    ```cmd
    mvn spring-boot:run
    ```

4. Access the service:
    - The service will be available at `http://localhost:8080/swagger-ui/index.html)`
      
### API Details:
- PUT /api/v1/employees/{id} - To update the employee details
- GET /api/v1/employees/{id} - To get the details of an employee
- POST /api/v1/employees - To create new Employee Object
- GET /api/v1/employees - To get all the Employees in the DB which uses pagination. By default we get 1st 20 employees details.


## 2. Time Off Management System
