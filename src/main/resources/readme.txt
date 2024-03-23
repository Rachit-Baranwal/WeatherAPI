Weather API Integration using Spring Boot
This project demonstrates how to build a server using Spring Boot in Java to integrate with the Weather API from Rapid API. It exposes RESTful APIs to retrieve weather forecast information for any city.

Authentication Method
The authentication method used for accessing the Weather API is Header-based authentication with a random client ID and client secret.

APIs Exposed
1. Get Weather Forecast Summary by City
Endpoint: /api/forecastByName/{cityName}

Description: This API retrieves the weather forecast summary for a specified city.

2. Get Hourly Weather Forecast Details by City
Endpoint: /gateway/forecastByNameHourly/{cityName}

Description: This API retrieves hourly weather forecast details for a specified city.

How to Use

Clone the repository: git clone https://github.com/your_username/your_repository.git
Open the project in your preferred Java IDE.
Configure your Rapid API client ID and client secret in application.properties.
Run the application.
Use the provided API endpoints to retrieve weather forecast information for desired cities.
Endpoints Example
Get Weather Forecast Summary: GET /gateway/forecastByName/NewYork
Get Hourly Weather Forecast Details: GET /gateway/forecastByNameHourly/NewYork
Prerequisites
Java JDK 8 or higher
Maven
Rapid API account and subscription to the Weather API
Contact
For any queries or issues, please feel free to contact Rachit Baranwal.