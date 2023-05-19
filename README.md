# TheWinners - Ecommerce

TheWinners is an ecommerce platform developed with HTML, Spring Boot, Tailwind CSS, and MySQL. It was created by a collaborative effort from Elton, Matheus, Keity, and Vinicius. This repository contains all the code and resources required to set up and run TheWinners on your own server.

## Features

- User registration and authentication
- Product browsing and search functionality
- Product categorization
- Shopping cart and checkout process
- Order tracking
- Payment integration
- User reviews and ratings
- Admin dashboard for managing products, orders, and users

## Installation

1. Clone the repository: `git clone https://github.com/milenahochheim/TheWinners---Ecommerce.git`
2. Navigate to the project directory: `cd TheWinners---Ecommerce`
3. Set up the database:
   - Create a MySQL database for TheWinners.
   - Import the provided SQL file located in the `database` directory to create the necessary tables and seed initial data.
4. Configure the Spring Boot application:
   - Open the `src/main/resources/application.properties` file.
   - Update the MySQL database credentials and connection details.
5. Build and run the Spring Boot application:
   - Open a terminal and navigate to the project directory.
   - Run the command: `./mvnw spring-boot:run`
6. Access the application in your browser at `http://localhost:8080`

## Usage

- Visit the homepage to browse and search for products.
- Sign up or log in to your account to add items to your shopping cart.
- Proceed to the checkout process to review your order and enter shipping information.
- Choose a payment method and complete the payment.
- Track your order status in the user dashboard.
- Leave reviews and ratings for products you have purchased.
- Access the admin dashboard to manage products, orders, and users.

## Technologies Used

- HTML
- Spring Boot
- Tailwind CSS
- MySQL

## Developers

- Milena
- Elton
- Matheus
- Keity
- Vinicius

## Contributing

Contributions are welcome! If you want to contribute to TheWinners, please follow these steps:

1. Fork the repository
2. Create a new branch: `git checkout -b my-new-branch`
3. Make your changes and commit them: `git commit -m 'Add some feature'`
4. Push the branch to your forked repository: `git push origin my-new-branch`
5. Open a pull request on the main repository

Please make sure your code adheres to the existing coding style and includes appropriate documentation.

Enjoy using TheWinners for your ecommerce needs!
