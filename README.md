
# Payment Initiation API with Reactive Spring Boot

This repository contains a payment initiation API implemented using reactive programming with Spring Boot, Spring WebFlux, and Spring Data MongoDB. The API allows users to initiate payment transactions by specifying the transaction amount, currency, description, and order ID. It generates a unique transaction ID for each transaction and checks for existing payments with the same transaction type and order ID.

## Getting Started

To run the payment initiation API locally, you'll need to have the following tools installed on your machine:

* Java 11 or higher
* Maven
* MongoDB

Once you have these tools installed, you can follow these steps to run the API:

1. Clone the repository to your local machine:

```
git clone https://github.com/ethicalvats/chatgpt-payment
```

2. Navigate to the project directory:

```
cd chatgpt-payment
```

3. Start the MongoDB server:

```
mongod
```

4. Start the API using Maven:

```
mvn spring-boot:run
```

The API should now be running at `http://localhost:8080`. You can test it using a tool like `curl` or a web browser.

## API Endpoints

The payment initiation API provides the following endpoints:

### Initiate Payment

This endpoint allows users to initiate a payment transaction by specifying the transaction amount, currency, description, and order ID.

* URL: `POST /payments`
* Request Body: JSON object with the following fields:
    * `amount` (number): The transaction amount
    * `currency` (string): The currency code (e.g. "USD", "EUR")
    * `transactionType` (string): A type of the payment transaction, must be unique for each orderId
    * `orderId` (string): The order ID for the payment transaction
* Response: A JSON object with the following fields:
    * `transactionId` (string): The unique transaction ID generated by the API

### Success Redirect

This endpoint handles success redirects after a payment transaction is completed.

* URL: `GET /success`
* Response: The success view or template

### Failure Redirect

This endpoint handles failure redirects after a payment transaction fails.

* URL: `GET /failure`
* Response: The failure view or template

## Technology Stack

The payment initiation API is built using the following technologies:

* Spring Boot: A framework for building production-grade Spring applications
* Spring WebFlux: A module of Spring Framework for building reactive web applications
* Spring Data MongoDB: A module of Spring Data for MongoDB integration
* Maven: A build automation tool for Java projects
* MongoDB: A document-based NoSQL database

## Contributing

Contributions to the payment initiation API are welcome! If you find a bug or would like to add a new feature, please open an issue or submit a pull request.

## License

This payment initiation API is licensed under the GNU General Public License v3.0. See the `LICENSE` file for details.
