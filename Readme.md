# 🏨 Hotel Reservation System

A console-based **Hotel Reservation System** developed in Java for managing rooms and hotel bookings. The project demonstrates Object-Oriented Programming, Java Collections, file handling, and basic service-layer design.

## 🚀 Features

- 🔍 Search available rooms by category
- 🛏️ View all hotel rooms
- 📅 Book a room with check-in and check-out dates
- ❌ Cancel reservations
- 🧾 View reservation details using Booking ID
- 💳 Simulated payment processing
- 💾 Persistent data storage using text files
- 🆔 Automatic reservation ID generation

## 🛠️ Technologies Used

- **Java**
- Object-Oriented Programming (OOP)
- Collections / ArrayList
- Java Time API
- File I/O
- Streams and Lambda Expressions

## 🏨 Room Categories

| Category | Price / Night |
|----------|---------------|
| Standard | ₹100 |
| Deluxe | ₹200 |
| Suite | ₹350 |

The system initializes **15 rooms** across the three categories.

## 📂 Project Structure

```text
HotelReservationSystem/
├── HotelReservationApp.java
├── model/
│   ├── Category.java
│   ├── Room.java
│   └── Reservation.java
├── service/
│   ├── HotelService.java
│   └── FileStorageService.java
├── rooms.txt
└── reservations.txt
```

## ▶️ How to Run

Open a terminal inside the project folder and compile:

```bash
javac HotelReservationApp.java model/*.java service/*.java
```

Run the application:

```bash
java HotelReservationApp
```

## 📋 Main Menu

```text
1. Search Available Rooms
2. Book a Room
3. Cancel Reservation
4. View Reservation Details
5. View All Rooms
6. Exit
```

## 🖥️ Sample Output

```text
========================================
       HOTEL RESERVATION SYSTEM
========================================

1. Search Available Rooms
2. Book a Room
3. Cancel Reservation
4. View Reservation Details
5. View All Rooms
6. Exit

Enter your choice: 1

Enter room category (STANDARD/DELUXE/SUITE): DELUXE

Available Rooms:
Room 6  - DELUXE  - ₹200/night
Room 7  - DELUXE  - ₹200/night
Room 8  - DELUXE  - ₹200/night

Enter your choice: 2

Enter Room Number: 6
Enter Guest Name: Chidananda
Enter Check-in Date (YYYY-MM-DD): 2026-10-10
Enter Check-out Date (YYYY-MM-DD): 2026-10-12

Reservation created successfully!
Booking ID: RES1001
Total Amount: ₹400

========================================
Reservation Details
========================================
Booking ID   : RES1001
Guest Name   : Chidananda
Room Number  : 6
Category     : DELUXE
Check-in     : 2026-10-10
Check-out    : 2026-10-12
Total Amount : ₹400
Status       : CONFIRMED
========================================
```

## 💾 Data Storage

The application stores data locally in:

- `rooms.txt` — room availability and category
- `reservations.txt` — reservation information

No external database is required.

## 🧠 Concepts Demonstrated

- Classes and Objects
- Encapsulation
- Enum
- ArrayList and Collections
- Streams and Lambda Expressions
- File Handling
- Exception Handling
- Date and Time API
- Service-layer separation

## 🔮 Future Enhancements

- GUI/Web interface
- Database integration
- User authentication
- Real payment gateway
- Multiple guest management
- Improved booking availability based on dates
- Admin dashboard

## 👨‍💻 Author

**Chidananda P**  
Computer Science & Engineering Student

## 📌 Disclaimer

This project is created for **educational purposes**. Payment processing is simulated and no real financial transactions are performed.
