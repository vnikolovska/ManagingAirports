# Project documentation

## Setup
### Cloning the project
1. Clone the project by running the command git clone https://github.com/vnikolovska/ManagingAirports.git.

### Building and Running the project with Docker
1. Open the project directory in terminal.
2. Run ./mvnw install -DskipTests to install the necessary dependencies.
3. Build a Docker image by running docker build -t blog-api-docker.jar ..
4. Start the Docker container by running docker-compose up -d.
## Endpoints


### The Managing Airports application has the following endpoints for Airport.

1. POST /api/airport/add: Add a new airport.
2. DELETE /api/airport/delete/[code]: Delete an airport with the given code.
3. POST /api/airport/filter/[country]: Return the airport with the most passengers by given country.

### The Managing Airports application has the following endpoints for Flight.

1. POST /api/flights/add: Add a new flight.
2. PUT /api/flights/edit/[id]: Edit the flight with the given id.
3. POST /api/flights/filter/[code]: Search for a flight by code.
4. POST /api/flights/find/[code]: Search for direct flights by given code.
5. POST /api/flights/findDirectFlights/[startingCode]/[destinationCode]: Search for flights by starting and destination code.

### User Authentication
1. POST /api/login: User login.
2. POST /api/register: User registration.