import model.Category;
import model.Reservation;
import model.Room;
import service.HotelService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class HotelReservationApp {
    private static final HotelService hotelService = new HotelService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   WELCOME TO CODEALPHA HOTEL RESERVATION SYSTEM  ");
        System.out.println("==================================================");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readIntInput("Select an option (1-6): ");

            switch (choice) {
                case 1 -> searchRooms();
                case 2 -> makeReservation();
                case 3 -> cancelReservation();
                case 4 -> viewBookingDetails();
                case 5 -> listAllRooms();
                case 6 -> {
                    running = false;
                    System.out.println("Thank you for using CodeAlpha Hotel Management System. Goodbye!");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Search Available Rooms");
        System.out.println("2. Book a Room");
        System.out.println("3. Cancel Reservation");
        System.out.println("4. View Reservation Details");
        System.out.println("5. View All Rooms");
        System.out.println("6. Exit");
    }

    private static void searchRooms() {
        System.out.println("\n--- Search Rooms ---");
        System.out.println("Filter by Category:");
        System.out.println("0. All Categories");
        System.out.println("1. Standard (Rs.100/night)");
        System.out.println("2. Deluxe (Rs.200/night)");
        System.out.println("3. Suite (Rs.350/night)");

        int catChoice = readIntInput("Choice: ");
        Category category = switch (catChoice) {
            case 1 -> Category.STANDARD;
            case 2 -> Category.DELUXE;
            case 3 -> Category.SUITE;
            default -> null;
        };

        List<Room> availableRooms = hotelService.searchAvailableRooms(category);
        if (availableRooms.isEmpty()) {
            System.out.println("No available rooms match your criteria.");
        } else {
            System.out.println("\nAvailable Rooms:");
            availableRooms.forEach(room -> System.out.println(room));
        }
    }

    private static void makeReservation() {
        System.out.println("\n--- New Reservation ---");
        System.out.print("Enter Guest Full Name: ");
        String name = scanner.nextLine().trim();

        int roomNumber = readIntInput("Enter Room Number to book: ");

        LocalDate checkIn = readDateInput("Enter Check-in Date (YYYY-MM-DD): ");
        LocalDate checkOut = readDateInput("Enter Check-out Date (YYYY-MM-DD): ");

        try {
            Reservation reservation = hotelService.bookRoom(name, roomNumber, checkIn, checkOut);
            if (reservation != null) {
                System.out.println("\n✅ Reservation Successful!");
                System.out.println(reservation);
            } else {
                System.out.println("❌ Booking failed: Room unavailable or invalid selection.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void cancelReservation() {
        System.out.println("\n--- Cancel Reservation ---");
        System.out.print("Enter Booking ID: ");
        String bookingId = scanner.nextLine().trim();

        boolean success = hotelService.cancelReservation(bookingId);
        if (success) {
            System.out.println("✅ Reservation " + bookingId + " successfully canceled.");
        } else {
            System.out.println("❌ Booking ID not found.");
        }
    }

    private static void viewBookingDetails() {
        System.out.println("\n--- Reservation Details ---");
        System.out.print("Enter Booking ID: ");
        String bookingId = scanner.nextLine().trim();

        Reservation reservation = hotelService.getReservationDetails(bookingId);
        if (reservation != null) {
            System.out.println(reservation);
        } else {
            System.out.println("❌ Reservation not found.");
        }
    }

    private static void listAllRooms() {
        System.out.println("\n--- Room Inventory ---");
        hotelService.getAllRooms().forEach(room -> System.out.println(room));
    }

    private static int readIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private static LocalDate readDateInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return LocalDate.parse(scanner.nextLine().trim());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }
    }
}