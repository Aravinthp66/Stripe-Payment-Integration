Developer README:

Project Overview:

This project demonstrates the creation of a Stripe Integration using Java 8, MongoDB for data storage. Follow these steps to set up and run the project for development and testing.

Prerequisites:

1. Java 8: Ensure you have Java 8 installed on your development machine.

2. IntelliJ IDEA: Use IntelliJ IDEA as your preferred IDE for this project.

3. Stripe Dashboard: Sign up for a Stripe Dashboard account to acsess Stripe services.

4. MongoDB 

Getting Started:

1. Import Project:
   - Open IntelliJ IDEA.
   - Import the project and ensure you set the project SDK to Java 8.

2. Stripe APIKEY Configuration:
   - Locate the `application.properties` file.
   - Add your APIKEY credentials. 

3. Database Configuration and Stripe APIKEY Configuration:
   - Open the `application.properties` file.
   - Add your APIKEY credentials
   - Configure your MongoDB database connection details (e.g., URL, username, password).
   - Create database name as mydb 

4. Build the Project:
   - Open a terminal in the project directory.
   - Run the following command to build the project:
     ```
     mvn clean install
     ```

5. Start the Server:
   - Run the project in IntelliJ IDEA. The server will start.

6. API Testing with Postman:
   - Use Postman or a similar tool to interact with the RESTful APIs.   

Note: Ensure you have properly set up your Stripe Dashboard account.

Feel free to customize and expand this README to provide more details about your project's architecture, usage, and any other relevant information for developers working on the project.


CURD API:

1)Create Customer
HTTP Method: Post
http://localhost:8000/stripe/createCustomer
Params:-
name
phone
email

2)Make Payment
HTTP Method: Post
http://localhost:8000/stripe/makePayment
Params:
name
fee
creditCardNo
cvv
cardExpiryMonth
cardExpiryYear
email
phone

3)Create Product
HTTP Method: Post
http://localhost:8000/stripe/createProduct
Params:
name
fee
description
period

4)CreatePayment Link
HTTP Method: Post
http://localhost:8000/stripe/createPaymentLink
Params:
name
fee
description
period

5)Retrieve All Product
HTTP Method: Get
http://localhost:8000/stripe/retrieveAllProduct

6)Retrieve Latest Transaction
HTTP Method: Get
http://localhost:8000/stripe/retrieveLatestTransaction







