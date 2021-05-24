# Car Renting Website

# Two types of users 
Secretary and Admin, Customer operations are done through the secretary.

# Admin: 
CRUD on users, gets a websocket notification when a car was returned.

# Secretary:
CRUD on cars, customers and rentals.
A photo of the car will also be stored in the database as a binary string.

# Use Case:
A customer comes to rent a car, if he doesn't have an account, the secretary will create one. 
He chooses a car, and the period of the rental. If in that period the car is available, a contract in pdf format will be downloaded in the browser, containing all the relevant information,
and an email with the confirmation of the booking will be sent.
When a customer returns with the car, the secretary notifies the admin through websocket communication.
