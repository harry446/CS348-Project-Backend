# CS348-Project-Backend

## Description

This backend service is used to interact with the MySQL database.

## Prerequisites

- MySQL Database Server running on local device
- Java Development Kit (JDK)
- IntelliJ IDEA or any other Java IDE
- Maven or Maven Wrapper installed on local device
- Ensure that port 8080 isn't in use

## Usage
If maven is installed on local device 
1. ```mvn clean install```
2. ```mvn spring-boot:run``` or run the application directly in IDE

### Verification
To verify that the server is up and running. Open `http://localhost:8080/healthCheck` on a browser. Should be able to see something like "CS348 service is up and running! 2024-05-31 23:29:13 UTC"

### Interaction with Database
Ensure Maven is rebuild after clean install.
<br> Configure the file application.properties based on individual database settings, update the following field:
1. ```spring.datasource.url``` : add the name of database in MySQLWorkbench after "3306/", i.e ```jdbc:mysql://localhost:3306/my_database_name```
2. ```spring.datasource.username``` : username, i.e ```root```
3. ```spring.datasource.password ```: personal password
<br> Internal server error implies wrong configuration on this file

---

## Generating the Production Dataset

This section provides instructions on how to generate the production dataset and load it into the database.

1. Download `requirements.txt`, `campuslots.xlsx`, `UW_lots_spots_gen.py`, and `Users_PH_CW_lots_spots_gen.ipynb`.
2. Run `pip install -r requirements.txt` to ensure all dependencies are installed.
3. Run the two Python scripts.
4. After getting 4 text files: `users.txt`, `p_holders.txt`, `lots.txt`, `allspots.txt` (which can also be found in the repository), download `Exp_Booking_Gen.ipynb` and `production_populate.sql`.
5. Run `Exp_Booking_Gen.ipynb` to get `expired_booking.txt` (which can also be found in the repository).
6. Replace the directories in the `production_populate.sql` to where you saved your text files, and run the SQL script.

---

## API Documentation
This section provides details on the supported API endpoints and features, including making bookings, cancelling bookings, and retrieving booking history with optional sorting functionalities.

These APIs can be tested using tools like `Postman` or `Curl`.

After launching the application, documentations can also be found here: `http://localhost:8080/swagger-ui.html
`

### 1. Making a Booking
Description: Create a new booking and update the `booking_num` in the users table to reflect the new booking count.

- Endpoint: http://localhost:8080/makeBooking
- Method: POST
- Request Payload Example (JSON):
```
{
    "uid": 2,
    "lid": 1,
    "sid": 3,
    "startYear": "2024",
    "startMonth": "06",
    "startDate": "14",
    "startHour": "10",
    "startMinute": "30",
    "endYear": "2024",
    "endMonth": "06",
    "endDate": "14",
    "endHour": "14",
    "endMinute": "00"
}
```
- Success Response:
```
Code: 200 OK
Content: "Booking created for user 2 at lot 1 and spot 3. From 2024-06-14 10:30:00 to 2024-06-14 14:00:00"
```

### 2. Cancelling a Booking
- Endpoint: http://localhost:8080/cancelBooking
- Method: DELETE
- Description: Cancel an existing booking and adjust the booking_num in the users table. This operation decreases the booking count.
- Query Parameters:
```
uid: Id of the user to cancel the booking for.
bid: Id of the booking to be cancelled.
```
- Request Example:
```
http://localhost:8080/cancelBooking?uid=2&bid=8
```
- Success Response:
```
Code: 200 OK
Content: "Booking canceled for user 2, with bid: 8
```
   Notes:
   Be aware that multiple calls to this endpoint might cause the booking_num to decrement below zero. Logic in backend prevents this scenario, but directly calling this endpoint bypasses the logic checks.

### 3. Retrieving Booking History
- Endpoint: /bookingHistory
- Method: POST
- Description: Retrieve the booking history for a user, with options to sort the results by price in ascending or descending order.
- Request Payload Example (without sorting):
```
{
    "uid": 4
}
```

[//]: # (- Request Payload Example &#40;with only current/upcoming bookings&#41;:)

[//]: # (```)

[//]: # ({)

[//]: # (    "uid": 4,)

[//]: # (    "upcomingOnly": true)

[//]: # (})

[//]: # (```)
- Request Payload Example (with sorting based on price in DESC order):
```
{
    "uid": 4,
    "priceDesc": true
}
```
- Success Response (sorting based on price in descending order):
```
Code: 200 OK
Content:
[
    {
        "area": "UW",
        "address": "XXX",
        "lotName": "DC",
        "parkingType": "pay",
        "createTime": "2024-06-20T04:28:57.000+00:00",
        "startTime": "2024-06-15T19:30:00.000+00:00",
        "endTime": "2024-06-15T22:30:00.000+00:00",
        "price": 15.0,
        "status": "expired"
    },
    {
        "area": "UW",
        "address": "XXX",
        "lotName": "SCH",
        "parkingType": "pay",
        "createTime": "2024-06-20T04:28:57.000+00:00",
        "startTime": "2024-06-15T19:30:00.000+00:00",
        "endTime": "2024-06-15T20:30:00.000+00:00",
        "price": 3.0,
        "status": "cancelled"
    },
    {
        "area": "UW",
        "address": "XXX",
        "lotName": "DWE",
        "parkingType": "free",
        "createTime": "2024-06-20T04:28:57.000+00:00",
        "startTime": "2024-06-15T19:30:00.000+00:00",
        "endTime": "2024-06-15T20:30:00.000+00:00",
        "price": 0.0,
        "status": "expired"
    }
]
```

### 4. List All Available Spot
- Endpoint: http://localhost:8080//listAvailableSpots
- Method: POST
- Description: List all the available parking spot given the required input field
- Request Payload Example (JSON):
```
{
  "uid": 12345,
  "location": "MC",
  "freeOnly": false,
  "startYear": "2024",
  "startMonth": "06",
  "startDate": "14",
  "startHour": "11",
  "startMinute": "30",
  "endYear": "2024",
  "endMonth": "06",
  "endDate": "14",
  "endHour": "14",
  "endMinute": "00"
}
```
- Success Response:
```
Code: 200 OK
Content:
{
  "lots": {
    {
      "lid": "0",
      "likeNum": "20",
      "spots": {
        {
          "sid": "0",
          "price": "1.50",    (this is the unit price per 30 mins)
          "parkingType": "visitor",
          "isAvailable": true
        },
        {
          "sid": "1",
          "price": "1.50",
          "parkingType": "visitor",
          "isAvailable": true
        },
        {
          "sid": "2",
          "price": "1.50",
          "parkingType": "accessible",
          "isAvailable": false
        },
        {
          "sid": "3",
          "price": "0",
          "parkingType": "permit",
          "isAvailable": true
        }
      }
    },
    {
      "lid": "37",
      "likeNum": "30",
      "spots": {
        {
          "sid": "0",
          "price": "2.50",
          "parkingType": "visitor",
          "isAvailable": false
        },
        {
          "sid": "1",
          "price": "2.50",
          "parkingType": "visitor",
          "isAvailable": false
        },
        {
          "sid": "2",
          "price": "2.50",
          "parkingType": "visitor",
          "isAvailable": true
        },
        {
          "sid": "3",
          "price": "2.50",
          "parkingType": "visitor",
          "isAvailable": true
        }
      }
    }
  }
}

```
### 5. Like a Parking Lot
- Endpoint: http://localhost:8080/likeLot
- Method: POST
- Description: Users may choose to like the parking spot after they have visited the parking lot, this operation will cause the likenum of a spot to increase by 1 
- Query Parameters 
```
lid: parking lot id that the user is intended to like for
bid: id of the booking that the user intended to like for
uid: id of the user that is going to like a spot
```
- Request Example
```
http://localhost:8080/likeLot?lid=2&bid=8&uid=4
```

- Request Response
```
Code: 200 OK
Content: Successfully increased like count of lot: 2, for booking: 8
```

### 6. User Logging Verifications 
- Endpoint: http://localhost:8080/login
- Method: GET
- Description: User logs into their account by providing username and password
- Query Parameters
```
username: user's login username
password: user's login password
```
- Request Example
```
[http://localhost:8080/login?username=USERNAME&password=PASSWORD](http://localhost:8080/login?username=admin01&password=iamadmin)
```
- Request Response
```
Code: 200 OK
Content: password is correct
```
Notice that different content maybe displayed based on the input field for username and password, this including
```
Username not found: username1
Wrong username or password
```

