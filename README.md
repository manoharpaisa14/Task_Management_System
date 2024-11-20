How to Run the Application
Follow these steps to set up and run the application:

1. Clone the Repository
Clone the project repository to your local machine using Git:
run the command: git clone https://github.com/manoharpaisa14/Task_Management_System.git
2. Build the Application
Navigate into the project directory:
command:cd operations-tm
Build the application using Maven:
command:mvn clean install
3. Run the Application
Running from Eclipse:
Right-click on the project folder in Eclipse.
Select Run As > Spring Boot App to start the application.
Running via Maven:
Open a terminal in the project directory and run:
command:mvn spring-boot:run
The application will start on http://localhost:8080.

How to Test the Application
Once the application is running, you can test it using Postman, Browser, or run JUnit tests.

Testing with Postman
Open Postman.
Choose the HTTP Method (GET, POST, PUT, PATCH, DELETE) depending on the operation you want to test.
Use the API URL:
POST: http://localhost:8080/task (for creating a task)
GET: http://localhost:8080/task/{taskId} (for fetching a specific task)
PUT: http://localhost:8080/task/{taskId} (for updating a task)
PATCH: http://localhost:8080/task/{taskId} (for partial updates)
DELETE: http://localhost:8080/task/{taskId} (for deleting a task)

Set Authorization to Basic Auth:
Username: admin
Password: admin123

Set Content-Type to application/json for POST, PUT, and PATCH requests.
Example POST request body for creating a new task:
json
{
    "title": "New Task",
    "description": "This is a task description",
    "due_date": "2024-12-31T00:00:00",
    "status": "PENDING"
}
Testing with Browser
For GET requests, you can directly test the API in a browser by navigating to the following URL:

GET Task: http://localhost:8080/task/{taskId}
Make sure to replace {taskId} with a valid task ID. You will be prompted to enter the Basic Authentication credentials (username: admin, password: admin123).

Running JUnit Tests
The project includes unit tests in the file src/test/java/com/task_management/operations_tm/controller/ControllerTest.java. To ensure the API is working correctly, run the tests.

Running Tests in Eclipse:
Right-click on ControllerTest.java file.
Select Run As > JUnit Test.
The results will appear in the JUnit view in Eclipse.
Running Tests with Maven:
Open a terminal in the project directory.
Run the following command:
command:mvn test

Alternatively, you can run all the tests with Maven by executing the following command:

This will execute the tests, including those in ControllerTest.java.

Git Setup Instructions
If you are working with Git for version control, follow these steps:

1. Initialize a Git Repository (if not already done)
In the root of your project, run:
command:git init
2. Add a Remote Repository (Optional)
If you're pushing to a remote repository like GitHub:
git remote add origin https://github.com/manoharpaisa14/Task_Management_System.git
3. Committing Changes
After making changes or adding files, commit them to your local repository:
command:
git add .
git commit -m "Your commit message"
4. Pushing Changes to Remote Repository
To push changes to your GitHub (or other remote):
command:
git push origin main
Make sure to replace main with the correct branch name (if you're using a different branch).

5. Pulling Changes from Remote Repository
To pull the latest changes from the remote repository:
command:
git pull origin main
Conclusion
This Task Management Application provides a simple and functional Spring Boot-based API for managing tasks with basic authentication and RESTful principles. By following the instructions in this README, you should be able to:

Run the application locally using Maven or Eclipse.
Test the API using Postman or a browser.
Verify the application with JUnit tests.
