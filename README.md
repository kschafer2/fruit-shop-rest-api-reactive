[![CircleCI](https://circleci.com/gh/kschafer2/sfg-fruit-shop-rest-reactive.svg?style=svg)](https://circleci.com/gh/kschafer2/sfg-fruit-shop-rest-reactive)
# Spring Fruit Shop RESTful API

Spring Webflux Fruit Shop REST API created with help from John Thompson's 
[Spring Framework 5: Beginner to Guru](https://github.com/springframeworkguru)

## How to Run:
Clone this repository to your local machine. 
Create a new project in your favorite IDE, then build and run the project with Gradle. Access the JSON endpoints on 
any web browser at the address of your local machine with the port number Netty is running on (default 8080) 
Ex: localhost:8080/api/v1/customers

# Features
## Categories

GET a list of all categories (/api/v1/categories) or an individual category (/api/v1/{categoryName}). 

POST a new category (/api/v1/categories)

Update a category with PUT (/api/v1/category/{categoryName})

DELETE a category (/api/v1/category/{categoryName})

## Customers

GET a list of all customers (/api/v1/customers) or an individual customer (/api/v1/{customerId}). 

POST a new customer (/api/v1/customers)

Update a customer with PUT (/api/v1/customers/{customerId})

DELETE a customer (/api/v1/customers/{customerID})

